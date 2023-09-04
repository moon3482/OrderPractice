package com.example.myapplication.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ViewholderMenuGroupBinding
import com.example.myapplication.list.ListUiEvent
import com.example.myapplication.model.MenuGroup

class MenuGroupViewHolder(
    private val binding: ViewholderMenuGroupBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(menuGroup: MenuGroup, listUiEvent: ListUiEvent?) {
        with(binding) {
            this.menuGroup = menuGroup
            this.uiEvent = listUiEvent
            menuListRecyclerView.adapter = ListMenuAdapter()
        }
    }

    companion object {
        operator fun invoke(parent: ViewGroup): MenuGroupViewHolder = MenuGroupViewHolder(
            ViewholderMenuGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }
}
