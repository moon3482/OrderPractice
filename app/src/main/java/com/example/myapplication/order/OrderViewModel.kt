package com.example.myapplication.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Event

class OrderViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private var _orderedMenuName: String? = null
    val orderedMenuName: String?
        get() = _orderedMenuName
    private var _orderedMenuPrice: Int? = null
    val orderedMenuPrice: Int?
        get() = _orderedMenuPrice
    private var _orderedMenuOption: String? = null
    val orderedMenuOption: String?
        get() = _orderedMenuOption
    private val _event: MutableLiveData<Event?> = MutableLiveData()
    val event: LiveData<Event?>
        get() = _event

    fun setOrderedMenu() {
        val name = savedStateHandle.get<String>("menuName")
        val price = savedStateHandle.get<Int>("menuPrice")
        val option = savedStateHandle.get<String>("menuOption")
        if (name == null || price == null || option == null) {
            _event.value = Event.ERROR
            return
        }
        _orderedMenuName = name
        _orderedMenuPrice = price
        _orderedMenuOption = option
    }

    fun clearEvent(){
        _event.value = null
    }
}