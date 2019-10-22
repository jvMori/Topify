package com.jvmori.topify.Utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.jvmori.topify.data.response.user.User
import com.jvmori.topify.di.scope.ApplicationScope
import com.jvmori.topify.view.activity.AuthResource
import io.reactivex.Observable
import javax.inject.Inject

@ApplicationScope
class SessionManager @Inject constructor() {
    private val cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun getUser(): LiveData<AuthResource<User>> = cachedUser

    lateinit var user : Observable<AuthResource<User>>

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source, Observer {
            cachedUser.value = it
            Observable.just(it)
            cachedUser.removeSource(source)
        })
    }

    fun logout() {
        cachedUser.value = AuthResource.logout()
    }

}