package com.obvious.apod.screens.transformers

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class ViewPagerTranslationTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        val absPos = abs(position)
        page.apply {
            translationY = absPos * 500f
        }
    }
}