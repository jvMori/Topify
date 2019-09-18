package com.jvmori.topify.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.db.entity.AuthKey

@Database(entities = [AuthKey::class], version = 1, exportSchema = false)
abstract class TopifyDatabase : RoomDatabase() {
    abstract fun authDao() : AuthDao
}