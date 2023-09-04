package com.example.myapplication.list.adapter

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView

class MenuGroupDecoration(
    @ColorInt
    private val color: Int,
    private val height: Float = 0F,
    private val paddingHorizontal: Float = 0F,
) : RecyclerView.ItemDecoration() {
    private var paint: Paint = Paint().apply { color = this@MenuGroupDecoration.color }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (position > 0)
            outRect.top = height.toInt()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val left = parent.paddingStart + paddingHorizontal
        val right = parent.width - (parent.paddingEnd + paddingHorizontal)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val layoutParams = view.layoutParams as RecyclerView.LayoutParams
            val top = view.bottom + layoutParams.bottomMargin
            val bottom = top + height
            c.drawRect(left, top.toFloat(), right, bottom, paint)
        }
    }
}
