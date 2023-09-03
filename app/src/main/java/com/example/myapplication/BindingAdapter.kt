package com.example.myapplication

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.detail.DetailUiEvent
import com.example.myapplication.list.adapter.ListAdapter
import com.example.myapplication.model.ListMenu
import com.example.myapplication.model.OrderMenu
import com.example.myapplication.util.toKRWString

@BindingAdapter("bind:price")
fun TextView.setPrice(price: Int) {
    this.text = price.toKRWString()
}

@BindingAdapter("bind:menuList")
fun RecyclerView.setMenuList(menuList: List<ListMenu>?) {
    val list = menuList ?: emptyList()
    (this.adapter as? ListAdapter)?.setMenuList(list)
}

@BindingAdapter("bind:isVisible")
fun View.isVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("bind:uiEvent", "bind:orderMenu")
fun TextView.setSendOrder(
    uiEvent: DetailUiEvent?,
    orderMenu: OrderMenu?
) {
    this.setOnClickListener {
        if (orderMenu != null)
            uiEvent?.moveToOrder(orderMenu)
    }
}