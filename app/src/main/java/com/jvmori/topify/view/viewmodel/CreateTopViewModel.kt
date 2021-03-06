package com.jvmori.topify.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.Utils.SessionManager
import com.jvmori.topify.Utils.TOP_ARTISTS
import com.jvmori.topify.Utils.TOP_PARAMS
import com.jvmori.topify.Utils.TOP_TRACKS
import com.jvmori.topify.data.repository.IRepository
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.response.playlist.AddTracks
import com.jvmori.topify.data.response.playlist.AddTracksResponse
import com.jvmori.topify.data.response.playlist.PlaylistResponse
import com.jvmori.topify.data.response.top.TopParam
import com.jvmori.topify.data.db.entity.TopTracksResponse
import com.jvmori.topify.data.repository.top.TopArtistsRepository
import com.jvmori.topify.data.repository.top.TopParamsRepository
import com.jvmori.topify.data.repository.top.TopRepository
import com.jvmori.topify.data.response.top.Image
import com.jvmori.topify.data.response.top.TimeRange
import com.jvmori.topify.data.response.top.TopCategory
import com.jvmori.topify.di.scope.MainActivityScope
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named

@MainActivityScope
class CreateTopViewModel @Inject constructor() : ViewModel() {

    @field:[Inject Named(TOP_TRACKS)]
    lateinit var topTracksRepository: TopRepository<TopTracksResponse>

    @field:[Inject Named(TOP_ARTISTS)]
    lateinit var topArtistsRepository: TopRepository<TopArtistsResponse>

    @Inject
    lateinit var topParamsRepository: TopParamsRepository

    @Inject
    lateinit var repository: IRepository

    private val disposable = CompositeDisposable()

    private val _topArtists = MutableLiveData<Resource<TopArtistsResponse>>()
    fun topArtists(): LiveData<Resource<TopArtistsResponse>> = _topArtists

    private val _topTracks = MutableLiveData<Resource<TopTracksResponse>>()
    fun topTracks(): LiveData<Resource<TopTracksResponse>> = _topTracks

    private val _playlistCoverImage = MutableLiveData<Resource<List<Image>>>()
    fun getPlaylistCoverImage(): LiveData<Resource<List<Image>>> = _playlistCoverImage

    private val _topParam = MutableLiveData<TopParam>()
    fun getTopParam(): LiveData<TopParam> = _topParam

    fun fetchTopParams() {
        disposable.add(
            topParamsRepository.getTop()
                .doOnComplete {
                    _topParam.value = TopParam(index=50, timeRange =  TimeRange().shortTerm,topCategory = TopCategory.TRACKS)
                }
                .subscribe {
                   _topParam.value = it
                }
        )
    }

    fun setTopParams(topParam: TopParam) {
        topParamsRepository.insert(topParam)
    }

    fun fetchTopTracks(topParam: TopParam) {
        Resource.loading(null)
        disposable.add(
            topTracksRepository.getTop(topParam)
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

    fun fetchTopArtists(topParam: TopParam) {
        _topArtists.value = Resource.loading(null)
        disposable.add(
            topArtistsRepository.getTop(topParam)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { success ->
                        _topArtists.value = Resource.success(success)
                    }, { error ->
                        _topArtists.value = Resource.error("Error $error.message", null)
                    }
                ))
    }

    fun createPlaylistAndAddTracks(playlistName: String, playlistDescription : String, data: TopTracksResponse?){
        _playlistCoverImage.value = Resource.loading(null)
        var playlistId = ""
        disposable.add(
            repository.getCurrentUser().flatMap {
                repository.createPlaylist(it.id, playlistName, playlistDescription)
            }.flatMap { playlist ->
                playlistId = playlist.id
                repository.addTracksToPlaylist(playlist.id, AddTracks(createUris(data)))
            }.flatMap {
                repository.getPlaylistCoverImage(playlistId)
            }.subscribe(
                { success ->
                    _playlistCoverImage.value = Resource.success(success)
                }, { error ->
                    _playlistCoverImage.value = Resource.error(error.message!!, null)
                }
            )
        )
    }

    private fun createUris(data: TopTracksResponse?) : MutableList<String>{
        val tracksUris = mutableListOf<String>()
        data?.tracks?.forEach { item ->
            tracksUris.add(item.uri)
        }
        return tracksUris
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}