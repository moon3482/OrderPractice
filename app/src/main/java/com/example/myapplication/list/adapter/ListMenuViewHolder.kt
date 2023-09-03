package com.example.myapplication.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ViewholderListMenuBinding
import com.example.myapplication.model.ListMenu

class ListMenuViewHolder(
    private val binding: ViewholderListMenuBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(listMenu: ListMenu?) {
        binding.listMenu = listMenu
    }

    companion object {
        operator fun invoke(
            parent: ViewGroup,
        ) = ListMenuViewHolder(
            ViewholderListMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}