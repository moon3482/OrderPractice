package com.example.myapplication.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Event

class OrderViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _orderedMenuName: MutableLiveData<String> = MutableLiveData()
    val orderedMenuName: LiveData<String>
        get() = _orderedMenuName
    private val _orderedMenuPrice: MutableLiveData<Int> = MutableLiveData()
    val orderedMenuPrice: LiveData<Int>
        get() = _orderedMenuPrice
    private val _orderedMenuOption: MutableLiveData<String> = MutableLiveData()
    val orderedMenuOption: LiveData<String>
        get() = _orderedMenuOption
    private val _event: MutableLiveData<Event> = MutableLiveData()
    val event: LiveData<Event>
        get() = _event

    init {
        setOrderedMenu()
    }

    private fun setOrderedMenu() {
        val name = savedStateHandle.get<String>("menuName")
        val price = savedStateHandle.get<Int>("menuPrice")
        val option = savedStateHandle.get<String>("menuOption")
        if (name == null || price == null || option == null) {
            _event.value = Event.ERROR
            return
        }
        _orderedMenuName.value = name
        _orderedMenuPrice.value = price
        _orderedMenuOption.value = option
    }
}