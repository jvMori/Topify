package com.jvmori.topify.view.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.Utils.artistDetailsKey
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.repository.artists.ArtistsRepository
import com.jvmori.topify.data.response.top.Album
import com.jvmori.topify.data.response.top.ArtistItem
import com.jvmori.topify.di.scope.MainActivityScope
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@MainActivityScope
class ArtistsDetailsViewModel @Inject constructor(
    var repository: ArtistsRepository
) : ViewModel() {

    private val disposable = CompositeDisposable()

    var currentArtist: ArtistItem? = null
    var popularity: String? = null

    private val _albums = MutableLiveData<Resource<List<Album>>>()
    fun getAlbums(): LiveData<Resource<List<Album>>> = _albums

    fun fetchCurrentArtist(arguments: Bundle?) {
        arguments?.let {
            currentArtist = it.getParcelable(artistDetailsKey)
        }
    }

    fun fetchPopularity(currentArtist : ArtistItem?) {
        popularity = "${currentArtist?.popularity} / 100"
    }

    fun fetchAlbums(id: String?) {
        _albums.value = Resource.loading(null)
        disposable.add(
            repository.fetchArtistsAlbums(id ?: "")
                .flatMap {
                    return@flatMap Observable.just(it.items)
                }.subscribe({
                    _albums.value = Resource.success(it)
                }, {
                    _albums.value = Resource.error(it?.message ?: "Error", null)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}