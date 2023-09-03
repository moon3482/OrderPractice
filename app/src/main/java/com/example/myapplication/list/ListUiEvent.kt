package com.example.myapplication.list

import com.example.myapplication.model.ListMenu

interface ListUiEvent {
    fun onClickMenu(listMenu: ListMenu)
    fun onClickBack()
}