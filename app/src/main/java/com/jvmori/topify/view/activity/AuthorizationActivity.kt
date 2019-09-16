package com.jvmori.topify.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jvmori.topify.R
import com.jvmori.topify.Utils.SessionManager

import com.jvmori.topify.view.viewmodel.AuthViewModel
import com.jvmori.topify.view.viewmodel.DiscoverViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject


class AuthorizationActivity : DaggerAppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var viewModelProvider : ViewModelProvider.Factory
    @Inject
    lateinit var sessionManager : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        AndroidInjection.inject(this)

        loginBtn.visibility = View.GONE

        val discoverViewModel = ViewModelProviders.of(this, viewModelProvider).get(DiscoverViewModel::class.java)
        discoverViewModel.currentUser()
        sessionManager.authenticateWithId(discoverViewModel.user())

        sessionManager.getUser().observe(this, Observer {
            Log.i("Main", it.status.toString())
            when (it.status) {
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                AuthResource.AuthStatus.NOT_AUTHENTICATED, AuthResource.AuthStatus.ERROR -> {
                    loginBtn.visibility = View.VISIBLE
                }
                else -> {
                    loginBtn.visibility = View.GONE
                    Log.i("Main", it.status.toString())
                }
            }
        })

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
