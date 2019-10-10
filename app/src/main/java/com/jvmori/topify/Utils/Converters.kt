package com.jvmori.topify.Utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jvmori.topify.data.response.top.Artist
import com.jvmori.topify.data.response.top.ArtistItem
import com.jvmori.topify.data.response.top.TopCategory
import com.jvmori.topify.data.response.top.Track

class Converters {
    companion object {
        @JvmStatic
        var gson = Gson()
        var gsonForGenres = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToList(data: String?): List<Track> {
            if (data == null) {
                mutableListOf<Track>()
            }

            val listType = object : TypeToken<List<Track>>() {

            }.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun listToString(movie: List<Track>): String {
            return gson.toJson(movie)
        }

        @TypeConverter
        @JvmStatic
        fun topCategoryToString(topCategory: TopCategory): String {
            return topCategory.toString()
        }

        @TypeConverter
        @JvmStatic
        fun topCategoryToString(topCategory: String): TopCategory {
            return when (topCategory) {
                TopCategory.TRACKS.toString() -> TopCategory.TRACKS
                TopCategory.ARTISTS.toString() -> TopCategory.ARTISTS
                else -> TopCategory.ARTISTS
            }
        }

        @TypeConverter
        @JvmStatic
        fun stringToListArtist(data: String?): List<ArtistItem> {
            if (data == null) {
                mutableListOf<ArtistItem>()
            }

            val listType = object : TypeToken<List<ArtistItem>>() {

            }.type
            return gsonForGenres.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun listArtistsToString(movie: List<ArtistItem>): String {
            return gsonForGenres.toJson(movie)
        }
    }
}