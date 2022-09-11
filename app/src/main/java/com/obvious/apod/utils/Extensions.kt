package com.obvious.apod.utils

import android.content.res.Resources.getSystem
import android.webkit.URLUtil
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