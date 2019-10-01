package com.jvmori.topify.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import com.jvmori.topify.data.response.top.ArtistItem
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "topify_top_artists", primaryKeys = ["timeRange", "countLimit"])
data class TopArtistsResponse(
    val href: String,
    @SerializedName("items")
    val artists: List<ArtistItem>,
    var timeRange: String,
    var countLimit : Int,
    var timestamp : Long
) : Parcelable