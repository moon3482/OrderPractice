package com.example.myapplication.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.list.ListUiEvent
import com.example.myapplication.model.MenuGroup

class MenuGroupAdapter(
    private val menuGroupList: MutableList<MenuGroup> = mutableListOf(),
) : RecyclerView.Adapter<MenuGroupViewHolder>() {
    private var listUiEvent: ListUiEvent? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuGroupViewHolder =
        MenuGroupViewHolder(parent)

    override fun onBindViewHolder(holder: MenuGroupViewHolder, position: Int) {
        val menuGroup = menuGroupList[position]
        holder.bind(menuGroup, listUiEvent)
    }

    override fun getItemCount(): Int = menuGroupList.size


    fun setGroupList(menuGroupList: List<MenuGroup>) {
        this.menuGroupList.addAll(menuGroupList)
        notifyDataSetChanged()
    }

    fun setChildUiEvent(listUiEvent: ListUiEvent?) {
        this.listUiEvent = listUiEvent
    }
}
