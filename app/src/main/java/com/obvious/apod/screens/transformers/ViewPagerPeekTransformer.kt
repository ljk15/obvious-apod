package com.obvious.apod.screens.transformers

import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.obvious.apod.utils.px

class ViewPagerPeekTransformer : ViewPager2.PageTransformer {

    private val margin = 4
    private val offset = 8

    override fun transformPage(page: View, position: Float) {
        val pageMarginPx = margin.px
        val offsetPx = offset.px
        val viewPager = page.parent.parent as ViewPager2
        val offset = position * -(2 * offsetPx + pageMarginPx)
        if (viewPager.orientation == ORIENTATION_HORIZONTAL) {
            if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                page.translationX = -offset
            } else {
                page.translationX = offset
            }
        } else {
            page.translationY = offset
        }
    }
}