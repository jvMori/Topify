package com.jvmori.topify.di.module.app

import android.app.Application
import androidx.room.Room
import com.jvmori.topify.data.db.TopifyDatabase
import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(context : Application) : TopifyDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            TopifyDatabase::class.java,
            "topifyAppDb.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}