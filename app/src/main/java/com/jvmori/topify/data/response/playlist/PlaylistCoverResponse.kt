package com.jvmori.topify.data.response.playlist

import android.os.Parcelable
import com.jvmori.topify.data.response.top.Image
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaylistCoverResponse (
    var images : List<Image>
) : Parcelable
