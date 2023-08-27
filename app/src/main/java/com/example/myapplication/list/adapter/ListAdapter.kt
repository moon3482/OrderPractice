package com.example.myapplication.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ListMenu

class ListAdapter(
    private val menuList: List<ListMenu>,
) : RecyclerView.Adapter<ListMenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMenuViewHolder =
        ListMenuViewHolder(parent)

    override fun getItemCount(): Int = menuList.size

    override fun onBindViewHolder(holder: ListMenuViewHolder, position: Int) {

    }
}