package cz.jannejezchleba.appliftingspacex.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cz.jannejezchleba.appliftingspacex.BuildConfig
import cz.jannejezchleba.appliftingspacex.data.model.FilterQuery
import cz.jannejezchleba.appliftingspacex.data.network.CachingInterceptor
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
    fun provideNetworkCachingInterceptor(): CachingInterceptor {
        return CachingInterceptor()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        appCache: Cache,
        loggingInterceptor: HttpLoggingInterceptor,
        cachingInterceptor: CachingInterceptor
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


