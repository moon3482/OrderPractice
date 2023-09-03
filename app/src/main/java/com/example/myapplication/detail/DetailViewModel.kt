package com.example.myapplication.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.ListMenu
import com.example.myapplication.model.OrderMenu

class DetailViewModel(): ViewModel() {
    private val _selectedListMenu: MutableLiveData<OrderMenu> = MutableLiveData()
    val selectedListMenu: LiveData<OrderMenu>
        get() = _selectedListMenu

    fun setSelectedListMenu(listMenu: ListMenu) {
        _selectedListMenu.value = OrderMenu(listMenu)
    }

    fun setTemp(isHot: Boolean) {
        when (_selectedListMenu.value) {
            is OrderMenu.Coffee -> {
                if (isHot) {
                    _selectedListMenu.value =
                        (_selectedListMenu.value as OrderMenu.Coffee).copy(
                            isHot = true,
                            icePortion = null,
                        )
                } else {
                    _selectedListMenu.value =
                        (_selectedListMenu.value as OrderMenu.Coffee).copy(isHot = false)
                }
            }

            else -> Unit
        }
    }

    fun setCaffeine(isCaffeine: Boolean) {
        when (_selectedListMenu.value) {
            is OrderMenu.Coffee -> {
                _selectedListMenu.value =
                    (_selectedListMenu.value as OrderMenu.Coffee).copy(isCaffeine = isCaffeine)
            }

            is OrderMenu.Tea -> {
                _selectedListMenu.value =
                    (_selectedListMenu.value as OrderMenu.Tea).copy(isCaffeine = isCaffeine)
            }

            else -> Unit
        }
    }

    fun setIcePortion(icePortion: IcePortion?) {
        when (_selectedListMenu.value) {
            is OrderMenu.Ade -> {
                _selectedListMenu.value =
                    (_selectedListMenu.value as OrderMenu.Ade).copy(icePortion = icePortion)
            }

            is OrderMenu.Coffee -> {
                _selectedListMenu.value =
                    (_selectedListMenu.value as OrderMenu.Coffee).copy(icePortion = icePortion)
            }

            else -> Unit
        }
    }
}