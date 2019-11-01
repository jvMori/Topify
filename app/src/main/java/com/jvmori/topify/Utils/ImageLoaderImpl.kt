package com.jvmori.topify.Utils

import android.widget.ImageView
import com.jvmori.topify.R
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor(
    private val picasso: Picasso
): ImageLoader {
    override fun loadImage(url: String?, imageView: ImageView) {
        picasso
            .load(url)
            .placeholder(R.drawable.placeholder_img)
            .error(R.drawable.placeholder_img)
            .into(imageView)
    }



    override fun loadImageWithRoundedCorners(url: String?, imageView: ImageView, imageParams: ImageParams) {
        picasso
            .load(url)
            .resize(imageParams.width, imageParams.height)
            .centerCrop()
            .transform(RoundedTransformation(imageParams.radius, imageParams.margin))
            .error(R.drawable.placeholder_img)
            .into(imageView)
    }
}

data class ImageParams(
    var radius : Float,
    var margin : Float,
    var width : Int,
    var height: Int
)