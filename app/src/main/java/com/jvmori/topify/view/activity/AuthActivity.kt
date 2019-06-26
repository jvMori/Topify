package com.jvmori.topify.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jvmori.topify.R
import com.jvmori.topify.Utils.CLIENT_ID
import com.jvmori.topify.view.TopifyActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse


class AuthActivity : AppCompatActivity() {

    val REDIRECT_URI = "topifyprotocol://redirect"
    val REQUEST_CODE = 1337

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.auth_activity)
        authenticate()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.let{
            AuthenticationResponse.fromUri(it).let{response ->
                if (response.type == AuthenticationResponse.Type.TOKEN){
                    startActivity(Intent(this, TopifyActivity::class.java))
                }else if (response.type == AuthenticationResponse.Type.ERROR){
                    Log.i("TOPIFY", "error")
                }
            }
        }
    }

    private fun authenticate(){
        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()
        AuthenticationClient.openLoginInBrowser(this, request)
    }

}