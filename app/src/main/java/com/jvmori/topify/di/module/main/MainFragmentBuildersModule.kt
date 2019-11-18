package com.jvmori.topify.di.module.main

import com.jvmori.topify.view.dialog.ConfirmPlaylistCreationDialog
import com.jvmori.topify.view.fragment.*
import com.jvmori.topify.view.fragment.artistDetails.FragmentArtistDetails
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contibuteHomeFragment() : FragmentHome

    @ContributesAndroidInjector
    abstract fun contributeCreateTopFrament() : FragmentCreateTop

    @ContributesAndroidInjector
    abstract fun contributeTopDetailsFrament() : FragmentTopDetails

    @ContributesAndroidInjector
    abstract fun contributeTopSettingsFrament() : FragmentTopSettings

    @ContributesAndroidInjector
    abstract fun contributeConfirmationFrament() : ConfirmPlaylistCreationDialog

    @ContributesAndroidInjector
    abstract fun contributeDiscoverFrament() : FragmentDiscover

    @ContributesAndroidInjector
    abstract fun contributeArtistDetailsFragment() : FragmentArtistDetails
}