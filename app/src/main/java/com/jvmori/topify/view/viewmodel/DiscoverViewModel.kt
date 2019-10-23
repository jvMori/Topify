package com.jvmori.topify.view.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jvmori.topify.Utils.RecommendationsParams
import com.jvmori.topify.data.Resource
import com.jvmori.topify.data.repository.IRepository
import com.jvmori.topify.data.repository.recommendations.RecommendationsRepository
import com.jvmori.topify.data.response.recommendations.RecommendationsResponse
import com.jvmori.topify.data.response.search.Artists
import com.jvmori.topify.data.response.user.User
import com.jvmori.topify.view.activity.AuthResource
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DiscoverViewModel @Inject constructor() : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _artists = MutableLiveData<Resource<Artists>>()
    fun artists(): LiveData<Resource<Artists>> = _artists

    private val _recommendations = MutableLiveData<Resource<RecommendationsResponse>>()
    fun recommendations() : LiveData<Resource<RecommendationsResponse>> = _recommendations

    private val _currentUser = MutableLiveData<AuthResource<User>>()
    fun user(): LiveData<AuthResource<User>> = _currentUser

    @Inject
    lateinit var repository: IRepository
    @Inject
    lateinit var recommendationsRepository: RecommendationsRepository

    fun search(query: String) {
        disposable.add(
            repository.searchArtist(query)
//                .flatMap {
//                    recommendationsRepository.getRecommendations(
//                        RecommendationsParams(
//                            seedArtists = listOf(it[0].items[0])
//                        )
//                    )
//                }
               .subscribe(
                    { success ->
                        _artists.value = Resource.success(success)
                    }, { error ->
                        _artists.value = Resource.error(error.message ?: "Something went wrong!", null)
                    }
                )
        )
    }

    fun fetchRecommendations(params: RecommendationsParams){
        _recommendations.value  = Resource.loading(null)
        disposable.add(
            recommendationsRepository.getRecommendations(params)
                .subscribe(
                    { success ->
                        _recommendations.value = Resource.success(success)
                    }, { error ->
                        _recommendations.value = Resource.error(error.message ?: "Something went wrong!", null)
                    }
                )
        )
    }

    fun currentUser() {
        disposable.add(
            repository.getCurrentUser()
                .subscribe(
                    { success ->
                        _currentUser.value = AuthResource.authenticated(success)
                    }, { error ->
                        Log.i("TOPIFY", error.message)
                        _currentUser.value = AuthResource.error(error.message!!, null)
                    }
                )
        )
    }
}