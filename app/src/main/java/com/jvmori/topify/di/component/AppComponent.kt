package com.jvmori.topify.di.component

import android.app.Application
import com.jvmori.topify.Utils.SessionManager
import com.jvmori.topify.application.BaseApplication
import com.jvmori.topify.di.module.app.*
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityBuildersModule::class,
        ViewModelsModule::class,
        DataSourceModule::class,
        ImageLoaderModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    val sessionManager: SessionManager

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}