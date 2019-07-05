package com.jvmori.topify.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.data.IRepository
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.response.playlist.PlaylistResponse
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.response.top.TopTracksResponse
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CreateTopViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var repository: IRepository

    private val disposable = CompositeDisposable()

    private val _topTracks = MutableLiveData<Resource<TopTracksResponse>>()
    fun topTracks(): LiveData<Resource<TopTracksResponse>> = _topTracks

    private val _topTracksPlaylist = MutableLiveData<Resource<PlaylistResponse>>()
    fun topTracksPlaylist(): LiveData<Resource<PlaylistResponse>> = _topTracksPlaylist

    fun fetchTopTracks(topParam: TopParam) {
        Resource.loading(null)
        disposable.add(
            repository.getTopTracks(topParam)
                .subscribe(
                    { success ->
                        _topTracks.value = Resource.success(success)
                    }, { error ->
                        _topTracks.value = Resource.error("Error $error.message", null)
                    }
                )
        )
    }

    fun createTopTracksPlaylist(userId : String, playlistName: String) {
        _topTracksPlaylist.value = Resource.loading(null)
        disposable.add(
            repository.createPlaylist(userId, playlistName)
                .subscribe(
                    {
                        success -> _topTracksPlaylist.value = Resource.success(success)
                    }, {
                        error -> _topTracksPlaylist.value = Resource.error("ERROR ${error?.message}", null)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}