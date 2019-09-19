package com.jvmori.topify.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jvmori.topify.Utils.Converters
import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.db.dao.TopTracksDao
import com.jvmori.topify.data.db.entity.AuthKey
import com.jvmori.topify.data.db.entity.TopTracksResponse

@Database(
    entities = [
        AuthKey::class,
        TopTracksResponse::class
    ], version = 2, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TopifyDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun topTracksDao(): TopTracksDao
}