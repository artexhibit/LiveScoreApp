package ru.igorcodes.livescoreapp.util

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Access token put here")
            .build()
        return chain.proceed(request)
    }
}