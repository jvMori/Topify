package com.jvmori.topify.di.module.main

import androidx.lifecycle.ViewModel
import com.jvmori.topify.di.ViewModelKey
import com.jvmori.topify.view.viewmodel.ArtistsDetailsViewModel
import com.jvmori.topify.view.viewmodel.CreateTopViewModel
import com.jvmori.topify.view.viewmodel.DiscoverViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindDiscoverViewModel(discoverViewModel: DiscoverViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArtistsDetailsViewModel::class)
    abstract fun bindArtistsDetailsViewModel(discoverViewModel: ArtistsDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateTopViewModel::class)
    abstract fun bindCreateTopViewModel(createTopViewModel: CreateTopViewModel): ViewModel

}