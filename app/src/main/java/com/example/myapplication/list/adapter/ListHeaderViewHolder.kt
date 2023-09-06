package com.example.myapplication.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ViewholderHeaderBinding
import com.example.myapplication.model.ListMenu

class ListHeaderViewHolder(
    private val binding: ViewholderHeaderBinding,
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(listMenu: ListMenu) {
        binding.listMenu = listMenu
    }

    companion object {
        operator fun invoke(parent: ViewGroup): ListHeaderViewHolder = ListHeaderViewHolder(
            ViewholderHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}