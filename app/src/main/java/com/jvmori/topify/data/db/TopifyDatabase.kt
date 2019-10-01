package com.jvmori.topify.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jvmori.topify.Utils.Converters
import com.jvmori.topify.data.db.dao.AuthDao
import com.jvmori.topify.data.db.dao.TopArtistsDao
import com.jvmori.topify.data.db.dao.TopTracksDao
import com.jvmori.topify.data.db.entity.AuthKey
import com.jvmori.topify.data.db.entity.TopArtistsResponse
import com.jvmori.topify.data.db.entity.TopTracksResponse

@Database(
    entities = [
        AuthKey::class,
        TopTracksResponse::class,
        TopArtistsResponse::class
    ], version = 6, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TopifyDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
    abstract fun topTracksDao(): TopTracksDao
    abstract fun topArtistsDao() : TopArtistsDao
}