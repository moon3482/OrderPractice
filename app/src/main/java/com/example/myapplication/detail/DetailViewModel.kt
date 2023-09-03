package com.example.myapplication.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.ListMenu
import com.example.myapplication.model.MenuType
import com.example.myapplication.model.OrderMenu
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _selectedListMenu: MutableLiveData<OrderMenu> = MutableLiveData()
    val selectedListMenu: LiveData<OrderMenu>
        get() = _selectedListMenu

    init {
        setSelectedListMenu()
    }

    private fun setSelectedListMenu() {
        val name: String? = savedStateHandle["menuName"]
        val price = savedStateHandle["menuPrice"] ?: 0
        val menuTypeString: String? = savedStateHandle["menuType"]
        val menuType = when (menuTypeString) {
            MenuType.COFFEE.name -> MenuType.COFFEE
            MenuType.ADE.name -> MenuType.ADE
            MenuType.TEA.name -> MenuType.TEA
            MenuType.DESERT.name -> MenuType.DESERT
            else -> null
        }
        if (name != null && menuType != null) {
            val listMenu = ListMenu(name, price, menuType)
            _selectedListMenu.value = OrderMenu(listMenu)
        }
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