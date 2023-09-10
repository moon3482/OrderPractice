package com.example.myapplication.presentation.ui.list

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.GetMenuListUsecase
import com.example.myapplication.presentation.model.ListMenu

class ListViewModel(
    private val getMenuListUsecase: GetMenuListUsecase,
) : ViewModel() {
    private val _menuMenuList: MutableList<ListMenu> = mutableListOf()
    val menuMenuList: List<ListMenu>
        get() = _menuMenuList

    init {
        setListMenu()
    }

    private fun setListMenu() {
        val menuList = getMenuListUsecase()
        _menuMenuList.addAll(menuList)
    }
}
