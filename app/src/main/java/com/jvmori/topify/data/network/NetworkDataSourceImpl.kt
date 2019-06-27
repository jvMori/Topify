package com.jvmori.topify.data.network

import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    val api: SpotifyApi
) : NetworkDataSource {
    override fun authorize() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}