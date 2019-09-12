package com.jvmori.topify.data.response.playlist

data class NewPlaylist (
    var name  : String = "New Topify Playlist",
    var description : String = "New playlist description",
    var public : Boolean = true
)