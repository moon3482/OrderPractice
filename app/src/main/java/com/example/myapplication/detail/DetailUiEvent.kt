package com.example.myapplication.detail

import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.OrderMenu

interface DetailUiEvent {
    fun onChangeTemp(isHot: Boolean)
    fun onChangeCaffeine(isCaffeine: Boolean)
    fun onChangeIcePortion(icePortion: IcePortion?)
    fun moveToOrder(orderMenu: OrderMenu)
}