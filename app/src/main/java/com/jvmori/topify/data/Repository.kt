package com.jvmori.topify.data

import com.jvmori.topify.data.network.NetworkDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    val networkDataSource: NetworkDataSource
){

}