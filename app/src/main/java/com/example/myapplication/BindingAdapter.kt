package com.example.myapplication

import android.graphics.Color
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.detail.DetailUiEvent
import com.example.myapplication.intro.IntroUiEvent
import com.example.myapplication.list.ListUiEvent
import com.example.myapplication.list.adapter.ListMenuAdapter
import com.example.myapplication.list.adapter.MenuGroupAdapter
import com.example.myapplication.list.adapter.MenuGroupDecoration
import com.example.myapplication.model.ListMenu
import com.example.myapplication.model.MenuGroup
import com.example.myapplication.model.OrderMenu
import com.example.myapplication.order.OrderUiEvent
import com.example.myapplication.util.toKRWString

@BindingAdapter("bind:price")
fun TextView.setPrice(price: Int) {
    text = price.toKRWString()
}

@BindingAdapter("bind:isVisible")
fun View.isVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("bind:groupList")
fun RecyclerView.setGroupList(groupList: List<MenuGroup>?) {
    val list = groupList ?: emptyList()
    addItemDecoration(MenuGroupDecoration(Color.parseColor("#EDF1F4"), 50F))
    (adapter as? MenuGroupAdapter)?.setGroupList(list)
}

@BindingAdapter("bind:onClickChildItem")
fun RecyclerView.setOnClickChildItem(listUiEvent: ListUiEvent?) {
    (adapter as? MenuGroupAdapter)?.setChildUiEvent(listUiEvent)
}

@BindingAdapter("bind:menuList")
fun RecyclerView.setMenuList(menuList: List<ListMenu>?) {
    val list = menuList ?: emptyList()
    (adapter as? ListMenuAdapter)?.setMenuList(list)
}

@BindingAdapter("bind:onClickItem")
fun RecyclerView.setOnClickItem(listUiEvent: ListUiEvent?) {
    (adapter as? ListMenuAdapter)?.setOnClickMenu {
        listUiEvent?.onClickMenu(it)
    }
}

@BindingAdapter("bind:onClickNext")
fun View.setOnClickNext(uiEvent: IntroUiEvent?) {
    setOnClickListener {
        uiEvent?.onClickNext()
    }
}

@BindingAdapter("bind:onClickOrder", "bind:orderMenu")
fun View.setOnClickOrder(
    uiEvent: DetailUiEvent?,
    orderMenu: OrderMenu?,
) {
    setOnClickListener {
        orderMenu?.let { orderMenu ->
            uiEvent?.onClickOrder(orderMenu)
        }
    }
}

@BindingAdapter("bind:onClickClose")
fun View.setOnClickClose(orderUiEvent: OrderUiEvent?) {
    setOnClickListener {
        orderUiEvent?.onClickClose()
    }
}

@BindingAdapter("bind:onClickBack")
fun Toolbar.setOnClickClose(onClickClose: (() -> Unit)?) {
    setNavigationOnClickListener {
        onClickClose?.invoke()
    }
}

@BindingAdapter("bind:checked")
fun RadioButton.setSelected(checked: Boolean?) {
    checked?.let { checked ->
        val drawableId = if (checked)
            R.drawable.bg_radio_button_selected
        else
            R.drawable.bg_radio_button_unselected
        val backgroundDrawable = ContextCompat.getDrawable(context, drawableId)
        val colorId = if (checked)
            R.color.radio_selected
        else
            R.color.radio_unselected
        val textColor = ContextCompat.getColor(context, colorId)

        background = backgroundDrawable
        setTextColor(textColor)
        isChecked = checked
    }
}
