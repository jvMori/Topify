package com.jvmori.topify.data.response.top


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistItem(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers,
    val genres: List<String>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val popularity: Int,
    val type: String,
    val uri: String
) : Parcelable {

    fun genresToString(): String {
        var genresTxt = ""
        genres.forEach {
            genresTxt += "$it, "
        }
        return genresTxt
    }

    fun followersToString() : String {
        return "Followers: ${followers.total}"
    }

    fun getImageUrl() : String {
        return images[0].url
    }
}