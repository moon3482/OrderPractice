package com.example.myapplication.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ListMenu
import com.example.myapplication.model.ViewHolderType

class ListMenuAdapter(
    private val menuList: MutableList<ListMenu> = mutableListOf(),
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onClickMenu: ((ListMenu) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ViewHolderType.HEADER -> ListHeaderViewHolder(parent)
            else -> ListMenuViewHolder(parent)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listMenu = menuList[position]
        when (holder) {
            is ListHeaderViewHolder -> holder.bind(listMenu)
            is ListMenuViewHolder -> {
                holder.bind(listMenu)
                holder.itemView.setOnClickListener {
                    onClickMenu?.invoke(listMenu)
                }
            }
        }


    }

    override fun getItemCount(): Int = menuList.size

    override fun getItemViewType(position: Int): Int {
        return when (menuList[position]) {
            is ListMenu.Header -> ViewHolderType.HEADER
            is ListMenu.Menu -> ViewHolderType.ITEM
        }
    }

    fun setMenuList(menuList: List<ListMenu>) {
        this.menuList.addAll(menuList)
        notifyDataSetChanged()
    }

    fun setOnClickMenu(onClickMenu: ((ListMenu) -> Unit)?) {
        this.onClickMenu = onClickMenu
    }
}
