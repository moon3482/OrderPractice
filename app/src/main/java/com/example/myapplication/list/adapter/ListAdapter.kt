package com.example.myapplication.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ListMenu

class ListAdapter(
    private val menuList: List<ListMenu?>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onClickMenu: ((ListMenu?) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            1 -> ListMenuViewHolder(parent)
            else -> DividerViewHolder(parent)
        }

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listMenu = menuList[position]
        when (holder) {
            is ListMenuViewHolder -> {
                holder.bind(listMenu)
                holder.itemView.setOnClickListener {
                    onClickMenu?.invoke(listMenu)
                }
            }

            else -> Unit
        }
    }

    override fun getItemViewType(position: Int): Int = when (menuList[position]) {
        is ListMenu -> 1
        else -> 2
    }

    fun setOnClickMenu(onClickMenu: ((ListMenu?) -> Unit)) {
        this.onClickMenu = onClickMenu
    }
}