package com.obvious.apod.screens.transformers

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class ViewPagerParallaxTransformer(viewId: Int) : ViewPager2.PageTransformer {
    private var viewId = 0

    init {
        this.viewId = viewId
    }

    override fun transformPage(page: View, position: Float) {
        val viewToParallax = page.findViewById<View>(viewId)
        if (viewToParallax != null) {
            if (position > -1 && position < 1) {
                val viewWidth = viewToParallax.width.toFloat()
                val viewSpeed = 0.7f
                viewToParallax.translationX = -(position * viewWidth * viewSpeed)
            }
        }
    }
}