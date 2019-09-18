package com.jvmori.topify.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.data.repository.IRepository
import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.user.User
import com.jvmori.topify.view.activity.AuthResource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DiscoverViewModel @Inject constructor()  : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _artists = MutableLiveData<List<Artists>>()
    fun artists(): LiveData<List<Artists>> = _artists

    private val _currentUser = MutableLiveData<AuthResource<User>>()
    fun user(): LiveData<AuthResource<User>> = _currentUser

    @Inject lateinit var  repository: IRepository

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
                            success -> _currentUser.value = AuthResource.authenticated(success)
                    }, {
                            error -> Log.i("TOPIFY", error.message)
                        _currentUser.value = AuthResource.error( error.message!!, null)
                    }
                )
        )
    }
}