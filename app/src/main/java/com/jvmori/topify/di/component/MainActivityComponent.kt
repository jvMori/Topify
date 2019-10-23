package com.jvmori.topify.di.component

import com.jvmori.topify.di.module.main.RecommendationsModule
import com.jvmori.topify.di.module.main.TopItemsModule
import com.jvmori.topify.di.scope.MainActivityScope
import dagger.Component

@MainActivityScope
@Component(
    modules = [
        RecommendationsModule::class,
        TopItemsModule::class
    ]
)
interface MainActivityComponent {
}