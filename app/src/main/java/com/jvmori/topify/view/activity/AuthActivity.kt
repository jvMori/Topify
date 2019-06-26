package com.jvmori.topify.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jvmori.topify.Utils.CLIENT_ID
import com.jvmori.topify.view.MainActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse


class AuthActivity : AppCompatActivity() {

    val REDIRECT_URI = "topifyprotocol://redirect"
    val REQUEST_CODE = 1337

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        authenticate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == REQUEST_CODE){
            val response = AuthenticationClient.getResponse(resultCode, intent)
           if (response.type == AuthenticationResponse.Type.TOKEN){
               val intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
           }else if (response.type == AuthenticationResponse.Type.ERROR){
               Log.i("TOPIFY", "error")
           }
        }
    }

    private fun authenticate(){
        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()
        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

}