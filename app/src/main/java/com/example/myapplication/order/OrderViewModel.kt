package com.example.myapplication.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
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

    init {
        setOrderedMenu()
    }

    private fun setOrderedMenu() {
        val name: String = savedStateHandle["menuName"] ?: ""
        val price: Int = savedStateHandle["menuPrice"] ?: 0
        val option: String = savedStateHandle["menuOption"] ?: ""

        _orderedMenuName.value = name
        _orderedMenuPrice.value = price
        _orderedMenuOption.value = option
    }
}