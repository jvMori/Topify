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
import com.jvmori.topify.data.response.top.TimeRange
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.response.top.TopTracksResponse
import com.jvmori.topify.view.viewmodel.CreateTopViewModel
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
class FragmentCreateTop : DaggerFragment() {

    @Inject
    lateinit var factory : ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topViewModel = ViewModelProviders.of(this, factory)[CreateTopViewModel::class.java]
        val params = TopParam(50, TimeRange().shortTerm) //TODO: user can change it in settings
        topViewModel.fetchTopTracks(params)
        topViewModel.topTracks().observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> showLoading()
                Resource.Status.SUCCESS -> success(it.data)
                Resource.Status.ERROR -> error(it.message)
            }
        })
    }

    private fun showLoading(){

    }

    private fun success(data : TopTracksResponse?){
        Log.i("TOPIFY", data?.tracks.toString() )
    }

    private fun error(message : String?){
        Log.i("TOPIFY", message )
    }
}
