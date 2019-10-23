package com.jvmori.topify.Utils

import com.jvmori.topify.data.response.top.ArtistItem
import com.jvmori.topify.data.response.top.Track

data class RecommendationsParams(
    var limit: Int = 30,
    var market: String = "US",
    val seedArtists: List<ArtistItem> = mutableListOf(),
    val seedGenres: List<String> = mutableListOf(),
    var seedTracks: List<Track> = mutableListOf(),
    var acoustics : String = "",
    var danceability : String = ""
) {
    fun getArtistsSeed(): String {
        var seed = ""
        seedArtists.forEachIndexed { index, value ->
            seed += value.id
            if (index != seedArtists.lastIndex)
                seed += ","
        }
        return seed
    }

    fun getGenresSeed() : String{
        var seed = ""
        seedGenres.forEachIndexed { index, value ->
            seed += value
            if (index != seedGenres.lastIndex)
                seed += ","
        }
        return seed
    }

    fun getTracksSeed() : String {
        var seed = ""
        seedTracks.forEachIndexed { index, value ->
            seed += value.id
            if (index != seedTracks.lastIndex)
                seed += ","
        }
        return seed
    }
}