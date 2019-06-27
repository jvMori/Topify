package com.jvmori.topify.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.data.Repository
import com.jvmori.topify.data.response.Artists
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class DiscoverViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _artists = MutableLiveData<List<Artists>>()
    fun artists(): LiveData<List<Artists>> = _artists

    fun search(query: String) {
        disposable.add(
            repository.searchArtist(query)
                .subscribe (
                    {
                        success -> _artists.value = success
                    }, {
                        error -> Log.i("TOPIFY", error.message)
                    }
                )
        )
    }
}