package com.example.myapplication

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.list.adapter.ListDecorator
import com.example.myapplication.list.adapter.ListMenuAdapter
import com.example.myapplication.model.ListMenu
import com.example.myapplication.util.toKRWString

@BindingAdapter("bind:price")
fun TextView.setPrice(price: Int) {
    text = price.toKRWString()
}

@BindingAdapter("bind:isVisible")
fun View.isVisible(isVisible: Boolean?) {
    isVisible?.let { isVisible ->
        this.isVisible = isVisible
    }
}

@BindingAdapter("bind:menuList")
fun RecyclerView.setMenuList(menuList: List<ListMenu>?) {
    val list = menuList ?: emptyList()
    addItemDecoration(ListDecorator(Color.parseColor("#EDF1F4"), 50F))
    (adapter as? ListMenuAdapter)?.setMenuList(list)
}