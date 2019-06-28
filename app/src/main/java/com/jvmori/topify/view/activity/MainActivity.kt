package com.jvmori.topify.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jvmori.topify.R
import dagger.android.AndroidInjection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
    }
}
