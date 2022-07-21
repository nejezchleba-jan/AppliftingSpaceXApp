package cz.jannejezchleba.appliftingspacex.data.network

import okhttp3.CacheControl
import okhttp3.Interceptor
import java.util.concurrent.TimeUnit

class CachingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val shouldUseCache = request.header("Cache-Control") != "no-cache"
        if (!shouldUseCache) return response

        val cacheControl = CacheControl.Builder()
            .onlyIfCached()
            .maxAge(6, TimeUnit.HOURS)
            .build()

        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}