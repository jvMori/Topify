package com.jvmori.topify.data.response.playlist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Tracks(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: Int,
    val offset: Int,
    val previous: Int,
    val total: Int
) : Parcelable