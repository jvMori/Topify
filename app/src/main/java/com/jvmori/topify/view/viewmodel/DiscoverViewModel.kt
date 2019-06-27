package com.jvmori.topify.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.data.Repository
import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.user.User
import io.reactivex.disposables.CompositeDisposable


class DiscoverViewModel  : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _artists = MutableLiveData<List<Artists>>()
    fun artists(): LiveData<List<Artists>> = _artists

    private val _currentUser = MutableLiveData<User>()
    fun user(): LiveData<User> = _currentUser

    lateinit var  repository: Repository

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

    fun currentUser() {
        disposable.add(
            repository.getCurrentUser()
                .subscribe (
                    {
                            success -> _currentUser.value = success
                    }, {
                            error -> Log.i("TOPIFY", error.message)
                    }
                )
        )
    }
}