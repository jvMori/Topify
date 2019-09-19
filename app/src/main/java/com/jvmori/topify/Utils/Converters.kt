package com.jvmori.topify.Utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jvmori.topify.data.response.top.Artist
import com.jvmori.topify.data.response.top.Track

class Converters {
    companion  object {
        @JvmStatic
        var gson = Gson()
        var gsonForGenres = Gson()


        @TypeConverter
        @JvmStatic
        fun stringToList(data: String?): List<Track> {
            if (data == null) {
                mutableListOf<Int>()
            }

            val listType = object : TypeToken<List<Int>>() {

            }.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun listToString(movie: List<Track>): String {
            return gsonForGenres.toJson(movie)
        }

        @TypeConverter
        @JvmStatic
        fun stringToListArtist(data: String?): List<Artist> {
            if (data == null) {
                mutableListOf<Int>()
            }

            val listType = object : TypeToken<List<Int>>() {

            }.type
            return gson.fromJson(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun listArtistsToString(movie: List<Artist>): String {
            return gsonForGenres.toJson(movie)
        }
    }
}