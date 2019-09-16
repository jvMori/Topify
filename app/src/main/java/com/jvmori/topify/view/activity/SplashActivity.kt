package com.jvmori.topify.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.jvmori.topify.R
import com.jvmori.topify.Utils.SessionManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        if (sessionManager.getUser().value == null)
//            openAuthActivity()

        sessionManager.getUser().observe(this, Observer {
            Log.i("Main", it.status.toString())
            when(it.status){
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    val intent = Intent(this, MainActivity::class.java )
                    startActivity(intent)
                    finish()
                }
                else -> {
                    openAuthActivity()
                }
            }
        })
    }

    private fun openAuthActivity() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)
        finish()
    }

}
