package com.jvmori.topify.view.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.jvmori.topify.R
import com.jvmori.topify.application.BaseApplication
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_toolbar.*


class MainActivity : DaggerAppCompatActivity() {
    lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)
        setupNavController()
        controller.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.fragmentHome, R.id.fragmentTopDetails, R.id.fragmentArtistDetails -> my_toolbar.visibility =
                    View.GONE
                R.id.fragmentCreateTop, R.id.fragmentDiscover -> my_toolbar.visibility = View.VISIBLE
            }
        }
    }

    private fun setupNavController() {
        controller = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottom_navigation, controller)

        val appBarConfiguration =
            AppBarConfiguration.Builder(
                mutableSetOf(
                    R.id.fragmentHome,
                    R.id.fragmentCreateTop,
                    R.id.fragmentDiscover
                )
            )
                .build()
        NavigationUI.setupActionBarWithNavController(this, controller, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        controller.navigateUp()
        return super.onSupportNavigateUp()
    }
}
