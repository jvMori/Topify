package com.jvmori.topify.di.module.main

import com.jvmori.topify.di.scope.MainActivityScope
import com.jvmori.topify.view.dialog.ConfirmPlaylistCreationDialog
import com.jvmori.topify.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contibuteHomeFragment() : FragmentHome

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contributeCreateTopFrament() : FragmentCreateTop

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contributeTopDetailsFrament() : FragmentTopDetails

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contributeTopSettingsFrament() : FragmentTopSettings

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contributeConfirmationFrament() : ConfirmPlaylistCreationDialog

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contributeDiscoverFrament() : FragmentDiscover

    @MainActivityScope
    @ContributesAndroidInjector
    abstract fun contributeArtistDetailsFragment() : FragmentArtistDetails
}