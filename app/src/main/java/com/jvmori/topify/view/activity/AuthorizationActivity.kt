package com.jvmori.topify.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import com.jvmori.topify.R
import com.jvmori.topify.view.viewmodel.AuthViewModel


class AuthorizationActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        authViewModel.authorize(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            authViewModel.checkResponse(it)
        }
    }
}
