package com.jvmori.topify.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.data.IRepository
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.response.top.TopTracksResponse
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CreateTopViewModel @Inject constructor(
    private val repository : IRepository
)
    : ViewModel()
{
    private val disposable = CompositeDisposable()
    private val _topTracks = MutableLiveData<Resource<TopTracksResponse>>()
    fun topTracks() : LiveData<Resource<TopTracksResponse>> = _topTracks

    fun fetchTopTracks(topParam: TopParam){
        Resource.loading(null)
        disposable.add(
            repository.getTopTracks(topParam)
                .subscribe(
                    {
                        success -> _topTracks.value = Resource.success(success)
                    }, {
                        error -> _topTracks.value = Resource.error("Error $error.message", null)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}