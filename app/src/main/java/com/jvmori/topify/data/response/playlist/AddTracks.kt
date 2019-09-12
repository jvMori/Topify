package com.jvmori.topify.data.response.playlist

data class AddTracks (
    val uris : MutableList<String> = mutableListOf(),
    var position : Int = 0
)

