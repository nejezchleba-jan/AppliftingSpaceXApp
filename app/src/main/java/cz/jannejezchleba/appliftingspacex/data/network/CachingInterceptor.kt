package cz.jannejezchleba.appliftingspacex.data.network

import okhttp3.CacheControl
import okhttp3.Interceptor
import java.util.concurrent.TimeUnit

class CachingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .onlyIfCached()
            .maxAge(6, TimeUnit.HOURS)
            .build()

        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}