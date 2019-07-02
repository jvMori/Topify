package com.jvmori.topify.view.viewmodel

import androidx.lifecycle.ViewModel
import com.jvmori.topify.data.IRepository
import com.jvmori.topify.data.response.top.TopParam
import javax.inject.Inject

class CreateTopViewModel @Inject constructor(
    private val repository : IRepository
)
    : ViewModel()
{
    fun fetchTopTracks(topParam: TopParam){

    }
}