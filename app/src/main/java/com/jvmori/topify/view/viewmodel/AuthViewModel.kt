package com.jvmori.topify.view.viewmodel

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import com.jvmori.topify.Utils.CLIENT_ID
import com.jvmori.topify.data.network.MyServiceInterceptor
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import javax.inject.Inject
import javax.inject.Named


class AuthViewModel @Inject constructor(
    @Named("Access_Token") var accessToken: String,
    @Named("REDIRECT_URI") var redirectUri : String,
    private var myServiceInterceptor: MyServiceInterceptor
) : ViewModel() {

    fun authorize(activity: Activity) {
        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, redirectUri)
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
                    accessToken = response.accessToken
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
