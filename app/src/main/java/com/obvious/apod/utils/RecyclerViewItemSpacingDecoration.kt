package com.obvious.apod.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemSpacingDecoration(
    spaceLeft: Int,
    spaceRight: Int,
    spaceTop: Int,
    spaceBottom: Int,
) : RecyclerView.ItemDecoration() {

    private var spaceLeft = 0
    private var spaceRight = 0
    private var spaceTop = 0
    private var spaceBottom = 0

    init {
        this.spaceLeft = spaceLeft
        this.spaceRight = spaceRight
        this.spaceTop = spaceTop
        this.spaceBottom = spaceBottom
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        outRect.left = spaceLeft.px
        outRect.right = spaceRight.px
        outRect.top = spaceTop.px
        outRect.bottom = spaceBottom.px
    }
}