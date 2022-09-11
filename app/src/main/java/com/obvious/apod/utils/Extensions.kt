package com.obvious.apod.utils

import android.content.res.Resources.getSystem
import android.webkit.URLUtil

val Int.dp: Int get() = (this / getSystem().displayMetrics.density).toInt()

val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()

fun String.getFileName(): String {
    return URLUtil.guessFileName(this, null, null)
}