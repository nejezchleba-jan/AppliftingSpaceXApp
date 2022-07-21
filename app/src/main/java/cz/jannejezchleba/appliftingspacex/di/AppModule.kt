package cz.jannejezchleba.appliftingspacex.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cz.jannejezchleba.appliftingspacex.BuildConfig
import cz.jannejezchleba.appliftingspacex.data.model.FilterQuery
import cz.jannejezchleba.appliftingspacex.data.network.CustomFilterQuerySerializer
import cz.jannejezchleba.appliftingspacex.data.repository.SpaceXRepository
import cz.jannejezchleba.appliftingspacex.data.repository.SpaceXRepositoryImpl
import cz.jannejezchleba.appliftingspacex.data.service.SpaceXApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .serializeNulls()
        .registerTypeAdapter(
            FilterQuery::class.java,
            CustomFilterQuerySerializer()
        ).create()

    @Singleton
    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(
            directory = File(context.cacheDir, "http_cache"),
            maxSize = 20L * 1024L * 1024L // 20 MiB,
        )
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideNetworkCachingInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            //Cache all POST requests for shorter duration (Launches queries)
            //Launches have a greater chance to change in shorter time frame
            val cacheControl = if (chain.request().method == "POST") {
                CacheControl.Builder()
                    .onlyIfCached()
                    .maxAge(15, TimeUnit.MINUTES)
                    .build()
            } else {
                CacheControl.Builder()
                    .onlyIfCached()
                    .maxAge(1, TimeUnit.DAYS)
                    .build()
            }

            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        appCache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        cachingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(appCache)
            .addNetworkInterceptor(cachingInterceptor)
            .addInterceptor(loggingInterceptor)
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Singleton
    @Provides
    fun provideSpaceXApiService(okHttpClient: OkHttpClient, gson: Gson): SpaceXApiService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(SpaceXApiService::class.java)


    @Singleton
    @Provides
    fun provideSpaceXRepository(
        api: SpaceXApiService,
    ): SpaceXRepository {
        return SpaceXRepositoryImpl(api)
    }
}


