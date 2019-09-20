package com.jvmori.topify.data.response.playlist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ExternalIds(
    val isrc: String
) : Parcelable