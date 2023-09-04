package com.example.myapplication.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ListMenu

class ListMenuAdapter(
    private val menuList: MutableList<ListMenu> = mutableListOf(),
) : RecyclerView.Adapter<ListMenuViewHolder>() {
    private var onClickMenu: ((ListMenu) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMenuViewHolder =
        ListMenuViewHolder(parent)

    override fun onBindViewHolder(holder: ListMenuViewHolder, position: Int) {
        val listMenu = menuList[position]
        holder.bind(listMenu)
        holder.itemView.setOnClickListener {
            onClickMenu?.invoke(listMenu)
        }

    }

    override fun getItemCount(): Int = menuList.size

    fun setMenuList(menuList: List<ListMenu>) {
        this.menuList.addAll(menuList)
        notifyDataSetChanged()
    }

    fun setOnClickMenu(onClickMenu: ((ListMenu) -> Unit)?) {
        this.onClickMenu = onClickMenu
    }
}
