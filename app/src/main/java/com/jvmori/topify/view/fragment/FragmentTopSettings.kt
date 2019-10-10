package com.jvmori.topify.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import com.jvmori.topify.R
import com.jvmori.topify.Utils.TOP_ARTISTS
import com.jvmori.topify.data.response.top.TimeRange
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
class FragmentTopSettings : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private var topViewModel: CreateTopViewModel? = null

    private lateinit var topParam: TopParam

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topViewModel = activity?.let {
            ViewModelProviders.of(it, factory).get(CreateTopViewModel::class.java)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.top_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topViewModel?.fetchTopParams()
        topViewModel?.getTopParam()?.observe(this, Observer {
            topParam = it
            setCategory()
            setTimeRange()
            setLimit()
        })

        radioGroupCategory.setOnCheckedChangeListener { _, checkedId ->
            chooseCategory(checkedId)
        }
        radioGroupTime.setOnCheckedChangeListener { _, checkedId ->
            chooseTimeRange(checkedId)
        }

        seekBarAmount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(progress > 0){
                    seekBar?.progress = progress
                    amountText.text = progress.toString()
                    topParam.limit = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }

    private fun chooseTimeRange(checkedId: Int) {
        when (checkedId) {
            R.id.radioButtonShortTime -> topParam.timeRange = TimeRange().shortTerm
            R.id.radioButtonMediumTime -> topParam.timeRange = TimeRange().mediumTerm
            R.id.radioButtonLongTime -> topParam.timeRange = TimeRange().longTerm
        }
    }

    private fun chooseCategory(checkedId: Int) {
        when (checkedId) {
            R.id.radioButtonArtists -> topParam.topCategory = TopCategory.ARTISTS
            R.id.radioButtonTracks -> topParam.topCategory = TopCategory.TRACKS
        }
    }

    private fun setLimit() {
        seekBarAmount.progress = topParam.limit
        amountText.text = topParam.limit.toString()
    }

    private fun setTimeRange() {
        when (topParam.timeRange) {
            TimeRange().shortTerm -> radioGroupTime.check(R.id.radioButtonShortTime)
            TimeRange().mediumTerm -> radioGroupTime.check(R.id.radioButtonMediumTime)
            TimeRange().longTerm -> radioGroupTime.check(R.id.radioButtonLongTime)
        }
    }

    private fun setCategory() {
        when (topParam.topCategory) {
            TopCategory.TRACKS -> radioGroupCategory.check(R.id.radioButtonTracks)
            TopCategory.ARTISTS -> radioGroupCategory.check(R.id.radioButtonArtists)
        }
    }

    override fun onStop() {
        super.onStop()
        topViewModel?.setTopParams(topParam)
    }
}
