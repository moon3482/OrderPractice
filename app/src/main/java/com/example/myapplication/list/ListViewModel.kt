package com.example.myapplication.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.ListMenu
import com.example.myapplication.model.MenuType

class ListViewModel : ViewModel() {
    private val _menuList: MutableLiveData<List<ListMenu>> = MutableLiveData()
    val menuList: LiveData<List<ListMenu>>
        get() = _menuList

    init {
        setListMenu()
    }

    private fun setListMenu() {
        _menuList.value = mutableListOf(
            ListMenu("아메리카노", 1000, MenuType.COFFEE),
            ListMenu("카페라떼", 1500, MenuType.COFFEE),
            ListMenu("카푸치노", 2000, MenuType.COFFEE),
            ListMenu("오렌지에이드", 2500, MenuType.ADE),
            ListMenu("망고에이드", 2500, MenuType.ADE),
            ListMenu("얼그레이티", 1000, MenuType.TEA),
            ListMenu("페퍼민트티", 1000, MenuType.TEA),
            ListMenu("치즈케이크", 3000, MenuType.DESERT),
            ListMenu("초코케이크", 3000, MenuType.DESERT),
            ListMenu("마들렌", 1000, MenuType.DESERT),
            ListMenu("휘낭시에", 1500, MenuType.DESERT),
        )
    }
}