package com.example.myapplication.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.ListMenu

class DetailViewModel : ViewModel() {
    private val _selectedListMenu:MutableLiveData<ListMenu> = MutableLiveData()
    val selectedListMenu:LiveData<ListMenu>
        get() =  _selectedListMenu

    fun setSelectedListMenu(listMenu: ListMenu){
        _selectedListMenu.value = listMenu
    }
}