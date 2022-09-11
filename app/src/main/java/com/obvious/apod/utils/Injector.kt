package com.obvious.apod.utils

import android.graphics.Color
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.gson.Gson

object Injector {
    fun provideGson(): Gson = Gson()

    fun provideShimmerPlaceHolder(): ShimmerDrawable {
        val shimmer =
            Shimmer.ColorHighlightBuilder()
                .setDuration(1200)
                .setBaseColor(Color.WHITE)
                .setBaseAlpha(0.2f)
                .setWidthRatio(0.3f)
                .setHighlightColor(Color.WHITE)
                .setHighlightAlpha(0.4f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build()

        return ShimmerDrawable().apply {
            setShimmer(shimmer)
        }
    }
}