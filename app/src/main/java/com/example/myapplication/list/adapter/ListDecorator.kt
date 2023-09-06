package com.example.myapplication.list.adapter

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ViewHolderType

class ListDecorator(
    @ColorInt
    private val color: Int,
    private val height: Float = 0F,
    private val paddingHorizontal: Float = 0F,
) : RecyclerView.ItemDecoration() {
    private var paint: Paint = Paint().apply { color = this@ListDecorator.color }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val viewHolder = parent.getChildViewHolder(view)
        when (viewHolder.itemViewType) {
            ViewHolderType.HEADER -> {
                Log.e("TAG", "getItemOffsets: $position")
                if (position > 0)
                    outRect.top = height.toInt()
            }

            else -> Unit
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        val childCount = parent.childCount
        val left = parent.paddingStart + paddingHorizontal
        val right = parent.width - (parent.paddingEnd + paddingHorizontal)
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val viewHolder = parent.getChildViewHolder(view)
            val layoutParams = view.layoutParams as RecyclerView.LayoutParams
            when (viewHolder.itemViewType) {
                ViewHolderType.ITEM -> {
                    if (i > 0 && i < childCount - 2) {
                        val nextView = parent.getChildAt(i + 1)
                        val nextViewHolder = parent.getChildViewHolder(nextView)
                        if (nextViewHolder.itemViewType == ViewHolderType.HEADER) {
                            val top = view.bottom + layoutParams.bottomMargin
                            val bottom = top + height
                            c.drawRect(left, top.toFloat(), right, bottom, paint)
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}