package com.jvmori.topify.data.response.top

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExternalUrls(
    val spotify: String
) : Parcelable