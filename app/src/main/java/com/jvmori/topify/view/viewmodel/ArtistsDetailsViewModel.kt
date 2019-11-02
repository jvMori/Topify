package com.jvmori.topify.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.repository.artists.ArtistsRepository
import com.jvmori.topify.data.response.top.Album
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ArtistsDetailsViewModel @Inject constructor() : ViewModel() {

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var repository : ArtistsRepository

    private val _albums  = MutableLiveData<Resource<List<Album>>>()
    fun getAlbums() : LiveData<Resource<List<Album>>> = _albums

    fun fetchAlbums(id : String?) {
        _albums.value = Resource.loading(null)
        disposable.add(
            repository.fetchArtistsAlbums(id ?: "")
                .flatMap {
                    return@flatMap Observable.just(it.items)
                }.subscribe({
                    _albums.value = Resource.success(it)
                },{
                    _albums.value = Resource.error(it?.message ?: "Error", null)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}