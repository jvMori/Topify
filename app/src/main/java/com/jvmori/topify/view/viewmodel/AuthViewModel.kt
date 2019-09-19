package com.jvmori.topify.view.viewmodel

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.Utils.CLIENT_ID
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.db.entity.AuthKey
import com.jvmori.topify.data.network.MyServiceInterceptor
import com.jvmori.topify.data.repository.AuthRepository
import com.jvmori.topify.view.activity.AuthResource
import com.jvmori.topify.view.activity.MainActivity
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named


class AuthViewModel @Inject constructor(
    private var authRepository: AuthRepository,
    @Named("REDIRECT_URI") var redirectUri: String,
    private var myServiceInterceptor: MyServiceInterceptor
) : ViewModel() {

    private val _response = MutableLiveData<AuthResource<AuthenticationResponse>>()
    fun response(): LiveData<AuthResource<AuthenticationResponse>> = _response

    private val _token = MutableLiveData<Resource<AuthKey>>()
    fun token(): LiveData<Resource<AuthKey>> = _token

    private val disposable = CompositeDisposable()

    fun authorize(activity: Activity) {
        _response.value = AuthResource.loading(null)
        val builder = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, redirectUri)
        builder.setScopes(arrayOf("user-read-private", "user-read-email", "user-top-read", "playlist-modify-public"))
        val request = builder.build()
        AuthenticationClient.openLoginInBrowser(activity, request)
    }

    fun fetchTokenLocal() {
        _token.value = Resource.loading(null)
        disposable.add(
            authRepository.getItemsLocal("")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ success ->
                    _token.value = Resource.success(success)
                    myServiceInterceptor.setSessionToken(success.key)
                }, {
                    _token.value = Resource.error("No key", null)
                }, {
                   _token.value = Resource.error("No such key in db", null)
                })
        )
    }

    fun checkResponse(intent: Intent) {
        val uri = intent.data
        if (uri != null) {
            val response = AuthenticationResponse.fromUri(uri)
            when (response.type) {
                AuthenticationResponse.Type.TOKEN -> {
                    myServiceInterceptor.setSessionToken(response.accessToken)
                    authRepository.insert(response.accessToken)
                    _response.value = AuthResource.authenticated(response)
                }
                AuthenticationResponse.Type.ERROR -> {
                    Log.i("TOPIFY", response.error)
                    _response.value = AuthResource.error(response.error, null)
                }
                else -> {
                    _response.value = AuthResource.logout()
                }
            }
        }
    }
}
