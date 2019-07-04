package com.jvmori.topify.Utils

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url : String, imageView: ImageView)
}