package com.jvmori.topify.view.viewmodel

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.jvmori.topify.Utils.CLIENT_ID
import com.jvmori.topify.data.network.MyServiceInterceptor
import com.jvmori.topify.data.network.AccessToken
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse


class AuthViewModel : ViewModel() {
    private val REDIRECT_URI = "jvmori://topify"

    //TODO: Inject with dagger
    private lateinit var myServiceInterceptor: MyServiceInterceptor

    fun setInterceptor(myServiceInterceptor: MyServiceInterceptor) {
        this.myServiceInterceptor = myServiceInterceptor
    }

    fun authorize(activity: Activity) {
        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(arrayOf("user-read-private", "user-read-email"))
        val request = builder.build()
        AuthenticationClient.openLoginInBrowser(activity, request)
    }

    fun checkResponse(intent: Intent) {
        val uri = intent.data
        if (uri != null) {
            val response = AuthenticationResponse.fromUri(uri)
            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> {
                    myServiceInterceptor.setSessionToken(response.accessToken)
                    AccessToken.accessToken = response.accessToken
                    //TODO: Open new activity if logged sucessfuly
                }

                AuthenticationResponse.Type.ERROR -> {
                    Log.i("TOPIFY", response.error)
                }
                else -> {
                }
            }
        }
    }
}
