package com.jvmori.topify.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MyServiceInterceptor @Inject constructor(private var accessToken: String) : Interceptor {

    private var sessionToken: String? = null

    fun setSessionToken(sessionToken: String) {
        this.sessionToken = sessionToken
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        val response = chain.proceed(request)

        if (request.header(accessToken) == "") {
            if (sessionToken == null) {
                throw RuntimeException("Session token should be defined for auth apis")
            } else {
                request = builder
                    .addHeader("Authorization", "Bearer $sessionToken")
                    .build()
                if(response.code() == 200)
                    return chain.proceed(request)
            }
        }

        //refresh token
        if (response.code() == 401) {
           request = builder
                .addHeader("Authorization", "Bearer $sessionToken")
                .build()
            
            return chain.proceed(request)
        }

        return response
    }
}