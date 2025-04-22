package com.bruno.horoscopeproject.data.core.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenMaganer: TokenMaganer) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().header("Autorization", tokenMaganer.getToken()).build()
        return chain.proceed(request)
    }


}

class TokenMaganer @Inject constructor() {

    fun getToken(): String = "userToken"
}