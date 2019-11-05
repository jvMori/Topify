package com.jvmori.topify.data.response.top


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    @SerializedName("album_type")
    val albumType: String,
    val artists: List<Artist>,
    @SerializedName("available_markets")
    val availableMarkets: List<String>,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("release_date_precision")
    val releaseDatePrecision: String,
    @SerializedName("total_tracks")
    val totalTracks: Int,
    val type: String,
    val uri: String
) : Parcelable{

    override fun equals(other: Any?): Boolean {
        return (other as Album).name == name && other.totalTracks == totalTracks
    }
}