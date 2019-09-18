package com.jvmori.topify.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.db.entity.AuthKey
import javax.inject.Inject

@Database(entities = [AuthKey::class], version = 1, exportSchema = false)
abstract class TopifyDatabase @Inject constructor(): RoomDatabase() {
    abstract fun authDao() : AuthDao
}