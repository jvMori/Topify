package com.jvmori.topify.di.module.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jvmori.topify.di.ViewModelKey
import com.jvmori.topify.di.module.app.ViewModelBuilder
import com.jvmori.topify.di.scope.MainActivityScope
import com.jvmori.topify.view.viewmodel.ArtistsDetailsViewModel
import com.jvmori.topify.view.viewmodel.CreateTopViewModel
import com.jvmori.topify.view.viewmodel.DiscoverViewModel
import com.jvmori.topify.view.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    @MainActivityScope
    abstract fun bindDiscoverViewModel(discoverViewModel: DiscoverViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArtistsDetailsViewModel::class)
    @MainActivityScope
    abstract fun bindArtistsDetailsViewModel(artistsDetailsViewModel: ArtistsDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateTopViewModel::class)
    @MainActivityScope
    abstract fun bindCreateTopViewModel(createTopViewModel: CreateTopViewModel): ViewModel

}