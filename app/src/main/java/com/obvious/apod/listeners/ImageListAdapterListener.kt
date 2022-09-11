package com.obvious.apod.listeners

import android.view.View

interface ImageListAdapterListener {
    fun onClick(view: View, position: Int)
}