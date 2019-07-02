package com.jvmori.topify.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jvmori.topify.R

import com.jvmori.topify.view.viewmodel.AuthViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject


class AuthorizationActivity : DaggerAppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var viewModelProvider : ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        AndroidInjection.inject(this)

        authViewModel = ViewModelProviders.of(this, viewModelProvider).get(AuthViewModel::class.java)
        loginBtn.setOnClickListener{
            authViewModel.authorize(this)
        }
        authViewModel.response().observe(this, Observer {
           when (it.status){
               AuthResource.AuthStatus.LOADING -> loading()
               AuthResource.AuthStatus.AUTHENTICATED ->  onSuccess()
               AuthResource.AuthStatus.ERROR, AuthResource.AuthStatus.NOT_AUTHENTICATED ->  error()
           }
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            authViewModel.checkResponse(it)
        }
    }

    private fun loading(){

    }

    private fun error(){

    }

    private fun onSuccess(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
