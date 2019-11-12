package com.jvmori.topify.di.component

import com.jvmori.topify.di.module.main.ArtistsModule
import com.jvmori.topify.di.module.main.MainViewModelsModule
import com.jvmori.topify.di.module.main.RecommendationsModule
import com.jvmori.topify.di.module.main.TopItemsModule
import com.jvmori.topify.di.scope.MainActivityScope
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(
    modules = [
        TopItemsModule::class,
        RecommendationsModule::class,
        ArtistsModule::class,
        MainViewModelsModule::class
    ]
)
interface MainActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainActivityComponent
    }
}