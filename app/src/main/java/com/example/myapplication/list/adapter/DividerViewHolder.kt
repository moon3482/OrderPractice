package com.example.myapplication.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ViewholderDividerBinding

class DividerViewHolder(
    private val binding: ViewholderDividerBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        operator fun invoke(parent: ViewGroup) = DividerViewHolder(
            ViewholderDividerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}