package com.jvmori.topify.view.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.jvmori.topify.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_toolbar.*


class MainActivity : DaggerAppCompatActivity() {
    lateinit var controller : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)
        setupNavController()
    }

    private fun setupNavController() {
        controller = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottom_navigation, controller)
        NavigationUI.setupActionBarWithNavController(this, controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        controller.navigateUp()
        return super.onSupportNavigateUp()
    }
}
