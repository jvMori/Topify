package com.jvmori.topify.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jvmori.topify.R
import com.jvmori.topify.data.Repository
import com.jvmori.topify.view.viewmodel.AuthViewModel
import com.jvmori.topify.view.viewmodel.DiscoverViewModel
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class AuthorizationActivity : DaggerAppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel
    @Inject
    lateinit var repository : Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        authViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        authViewModel.authorize(this)
        val discoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel::class.java)
        discoverViewModel.repository = repository
        button.setOnClickListener{
           // discoverViewModel.search("Muse")
            discoverViewModel.currentUser()
        }
        discoverViewModel.artists().observe(this, Observer {
            Log.i("TOPIFY", it.toString())
        })
        discoverViewModel.user().observe(this, Observer {
            Log.i("TOPIFY", it.toString())
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            authViewModel.checkResponse(it)
        }
    }
}
