package com.example.myapplication.presentation.ui

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.presentation.ui.list.adapter.ListDecorator
import com.example.myapplication.presentation.ui.list.adapter.ListMenuAdapter
import com.example.myapplication.presentation.model.ListMenu
import com.example.myapplication.util.toKRWString

@BindingAdapter("bind:price")
fun TextView.setPrice(price: Int) {
    text = price.toKRWString()
}

@BindingAdapter("bind:isVisible")
fun View.isVisible(isVisible: Boolean?) {
    this.isVisible = isVisible ?: false
}

@BindingAdapter("bind:menuList")
fun RecyclerView.setMenuList(menuList: List<ListMenu>?) {
    val list = menuList ?: emptyList()
    addItemDecoration(ListDecorator(Color.parseColor("#EDF1F4"), 50F))
    (adapter as? ListMenuAdapter)?.setMenuList(list)
}