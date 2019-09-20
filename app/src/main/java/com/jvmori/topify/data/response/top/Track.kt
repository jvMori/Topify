package com.jvmori.topify.data.response.top


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    val album: Album,
    val artists: List<Artist>,
    @SerializedName("duration_ms")
    val durationMs: Int,
    val explicit: Boolean,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    @SerializedName("is_local")
    val isLocal: Boolean,
    val name: String,
    val popularity: Int,
    @SerializedName("preview_url")
    val previewUrl: String,
    @SerializedName("track_number")
    val trackNumber: Int,
    val type: String,
    val uri: String
) : Parcelable {
    fun getDurationMinutes() : String{
        val seconds = (durationMs / 1000) % 60
        val secondsString: String = if ( seconds < 9)  "0$seconds" else seconds.toString()
        return "${durationMs / 1000 / 60}:$secondsString"
    }

    fun getIndex(adapterPosition : Int) : String{
        return if (adapterPosition < 9) "0${adapterPosition + 1}" else {adapterPosition + 1}.toString()
    }
}