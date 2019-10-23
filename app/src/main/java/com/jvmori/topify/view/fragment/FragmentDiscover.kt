package com.jvmori.topify.view.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.jvmori.topify.R
import com.jvmori.topify.data.Resource
import com.jvmori.topify.view.viewmodel.DiscoverViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentDiscover : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_discover, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val discoverViewModel = ViewModelProviders.of(this, factory).get(DiscoverViewModel::class.java)
        discoverViewModel.search("Muse")
        discoverViewModel.recommendations().observe(this, Observer {
            when(it.status){
                Resource.Status.SUCCESS -> Log.i("TOPIFY", it.data.toString())
                Resource.Status.ERROR -> Log.i("TOPIFY", it.data.toString())
            }
        })
    }
}
