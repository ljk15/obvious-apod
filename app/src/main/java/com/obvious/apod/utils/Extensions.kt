package com.obvious.apod.utils

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.res.Resources.getSystem
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.webkit.URLUtil
import androidx.annotation.ColorInt
import java.text.SimpleDateFormat
import java.util.*

val Int.dp: Int get() = (this / getSystem().displayMetrics.density).toInt()

val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()

fun String.getFileName(): String {
    return URLUtil.guessFileName(this, null, null)
}

fun String.convertDate(): String {
    val sdfFrom = SimpleDateFormat(Constants.SERVER_DATE_FORMAT, Locale.getDefault())
    val date: Date
    try {
        date = sdfFrom.parse(this) as Date
        val sdfTo =
            SimpleDateFormat(Constants.DISPLAY_DATE_FORMAT, Locale.getDefault())
        return sdfTo.format(date as Date)
    } catch (e: Exception) {

    }
    return this
}

fun View.colorTransition(@ColorInt endColor: Int, duration: Long = 250L){
    var colorFrom = Color.TRANSPARENT
    if (background is ColorDrawable)
        colorFrom = (background as ColorDrawable).color

    val colorAnimation: ValueAnimator = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, endColor)
    colorAnimation.duration = duration

    colorAnimation.addUpdateListener {
        if (it.animatedValue is Int) {
            val color=it.animatedValue as Int
            setBackgroundColor(color)
        }
    }
    colorAnimation.start()
}