package com.ballboycorp.anappaday.github.common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by musooff on 2019-07-05.
 */

class HorizontalSpaceDecoration(context: Context, dimenResId: Int) : RecyclerView.ItemDecoration() {
    private val space: Int = context.resources.getDimensionPixelSize(dimenResId)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        when (parent.getChildAdapterPosition(view)) {
            0 -> outRect.right = space / 2
            (parent.adapter?.itemCount ?: 0) - 1 -> outRect.left = space / 2
            else -> {
                outRect.left = space / 2
                outRect.right = space / 2
            }
        }
    }
}