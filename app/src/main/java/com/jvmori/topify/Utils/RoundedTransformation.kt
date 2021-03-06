package com.jvmori.topify.Utils

import android.graphics.*
import com.squareup.picasso.Transformation


class RoundedTransformation(var radius : Float, var margin : Float) : Transformation{

    override fun key(): String {
        return "rounded(radius=$radius, margin=$margin)"
    }

    override fun transform(source: Bitmap?): Bitmap {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(source!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        canvas.drawRoundRect(
            RectF(margin, margin, source.width - margin, source.height - margin),
            radius,
            radius,
            paint
        )

        if (source !== output) {
            source.recycle()
        }

        return output
    }
}