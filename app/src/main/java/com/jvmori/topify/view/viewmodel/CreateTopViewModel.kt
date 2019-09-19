package com.jvmori.topify.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.Utils.TOP_TRACKS
import com.jvmori.topify.data.repository.IRepository
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.response.playlist.AddTracks
import com.jvmori.topify.data.response.playlist.AddTracksResponse
import com.jvmori.topify.data.response.playlist.PlaylistResponse
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.repository.BaseRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

class CreateTopViewModel @Inject constructor() : ViewModel() {

    @field:[Inject Named(TOP_TRACKS)]
    lateinit var topTracksRepository: BaseRepository<TopTracksResponse, TopParam>

    @Inject
    lateinit var repository : IRepository

    private val disposable = CompositeDisposable()

    private val _topTracks = MutableLiveData<Resource<TopTracksResponse>>()
    fun topTracks(): LiveData<Resource<TopTracksResponse>> = _topTracks

    private val _topTracksPlaylist = MutableLiveData<Resource<PlaylistResponse>>()
    fun topTracksPlaylist(): LiveData<Resource<PlaylistResponse>> = _topTracksPlaylist

    private val _addTracksSnapshot = MutableLiveData<Resource<AddTracksResponse>>()
    fun addTracksSnapshot(): LiveData<Resource<AddTracksResponse>> = _addTracksSnapshot

    fun fetchTopTracks(topParam: TopParam) {
        Resource.loading(null)
        disposable.add(
            topTracksRepository.getItems(topParam)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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

    fun addTracksToPlaylist(playlistId : String, tracks: AddTracks) {
        _topTracksPlaylist.value = Resource.loading(null)
        disposable.add(
            repository.addTracksToPlaylist(playlistId, tracks)
                .subscribe(
                    {
                            success -> _addTracksSnapshot.value = Resource.success(success)
                    }, {
                            error -> _addTracksSnapshot.value = Resource.error("ERROR ${error?.message}", null)
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}