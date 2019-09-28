package com.jvmori.topify.view.activity

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.jvmori.topify.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_top_details.*


class MainActivity : DaggerAppCompatActivity() {
    lateinit var controller : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNav()
    }

    private fun setupBottomNav() {
        controller = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottom_navigation, controller)
    }

    override fun onSupportNavigateUp(): Boolean {
        controller.navigateUp()
        return super.onSupportNavigateUp()
    }
}
