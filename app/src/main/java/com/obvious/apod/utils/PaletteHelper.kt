package com.obvious.apod.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.util.concurrent.ConcurrentHashMap

object PaletteHelper {

    private val colorMap = ConcurrentHashMap<String, Int>()

    fun getColorFromImage(url: String, view: View, callback: (Int) -> Unit) {
        val colorKey = url.getFileName()
        if (colorMap.containsKey(colorKey))
            callback(colorMap[colorKey]!!)
        else {
            Glide.with(view).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val color = getColorFromSwatch(generatePalette(resource))
                    colorMap[colorKey] = color
                    callback(color)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })
        }
    }

    private fun getColorFromSwatch(palette: Palette): Int {
        val color: Int = when {
            palette.darkMutedSwatch != null -> palette.darkMutedSwatch!!.rgb
            palette.lightMutedSwatch != null -> palette.lightMutedSwatch!!.rgb
            palette.darkVibrantSwatch != null -> palette.darkVibrantSwatch!!.rgb
            palette.lightVibrantSwatch != null -> palette.lightVibrantSwatch!!.rgb
            else -> Color.BLACK
        }
        return darkenColor(color)
    }

    fun generatePalette(bitmap: Bitmap): Palette {
        return Palette.from(bitmap).generate()
    }

    @ColorInt
    fun darkenColor(@ColorInt color: Int): Int {
        return Color.HSVToColor(FloatArray(3).apply {
            Color.colorToHSV(color, this)
            this[1] *= 0.9f
            if (this[2] > 0.35f)
                this[2] *= 0.35f
        })
    }
}