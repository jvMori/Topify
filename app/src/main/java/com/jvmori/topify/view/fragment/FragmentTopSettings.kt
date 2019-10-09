package com.jvmori.topify.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.jvmori.topify.R
import com.jvmori.topify.data.response.top.TopCategory
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.view.viewmodel.CreateTopViewModel
import dagger.android.support.DaggerDialogFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.top_settings.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentTopSettings : DaggerDialogFragment() {

    @Inject
    lateinit var factory : ViewModelProvider.Factory

    private var topViewModel : CreateTopViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        topViewModel = activity?.let {
            ViewModelProviders.of(it, factory).get(CreateTopViewModel::class.java)
        }
        return inflater.inflate(R.layout.top_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radioButtonArtists.setOnClickListener {
            topViewModel?.setTopParams(TopParam(topCategory = TopCategory.ARTISTS))
        }
        radioButtonTracks.setOnClickListener {
            topViewModel?.setTopParams(TopParam(topCategory = TopCategory.TRACKS))
        }
    }


}
