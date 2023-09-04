package com.example.myapplication.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.MenuType
import com.example.myapplication.model.OrderMenu

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _selectedOrderMenu: MutableLiveData<OrderMenu> = MutableLiveData()
    val selectedOrderMenu: LiveData<OrderMenu>
        get() = _selectedOrderMenu

    init {
        setSelectedOrderMenu()
    }

    private fun setSelectedOrderMenu() {
        val name = savedStateHandle.get<String>("menuName")
        val price = savedStateHandle.get<Int>("menuPrice")
        val menuType = savedStateHandle.get<MenuType>("menuType")
        if (menuType == null || name == null || price == null) {
            return
        }
        val orderMenu = when (menuType) {
            MenuType.COFFEE -> OrderMenu.Coffee(
                name = name,
                price = price,
                isHot = true,
                isCaffeine = true,
                icePortion = null,
            )

            MenuType.ADE -> OrderMenu.Ade(
                name = name,
                price = price,
                icePortion = IcePortion.MEDIUM,
            )

            MenuType.TEA -> OrderMenu.Tea(
                name = name,
                price = price,
                isCaffeine = true,
            )

            MenuType.DESERT -> OrderMenu.Desert(
                name = name,
                price = price,
            )
        }
        _selectedOrderMenu.value = orderMenu
    }

    fun setTemp(isHot: Boolean) {
        _selectedOrderMenu.value?.let { orderMenu ->
            when (orderMenu) {
                is OrderMenu.Coffee -> {
                    _selectedOrderMenu.value = if (isHot)
                        orderMenu.copy(isHot = true, icePortion = null)
                    else
                        orderMenu.copy(isHot = false, icePortion = IcePortion.MEDIUM)
                }

                is OrderMenu.Ade,
                is OrderMenu.Tea,
                is OrderMenu.Desert -> Unit
            }
        }
    }


    fun setCaffeine(isCaffeine: Boolean) {
        _selectedOrderMenu.value?.let { orderMenu ->
            when (orderMenu) {
                is OrderMenu.Coffee -> {
                    _selectedOrderMenu.value = orderMenu.copy(isCaffeine = isCaffeine)
                }

                is OrderMenu.Tea -> {
                    _selectedOrderMenu.value = orderMenu.copy(isCaffeine = isCaffeine)
                }

                is OrderMenu.Ade,
                is OrderMenu.Desert,
                -> Unit
            }
        }
    }

    fun setIcePortion(icePortion: IcePortion) {
        _selectedOrderMenu.value?.let { orderMenu ->
            when (orderMenu) {
                is OrderMenu.Coffee -> {
                    _selectedOrderMenu.value = orderMenu.copy(icePortion = icePortion)
                }

                is OrderMenu.Ade -> {
                    _selectedOrderMenu.value = orderMenu.copy(icePortion = icePortion)
                }

                is OrderMenu.Desert,
                is OrderMenu.Tea -> Unit
            }
        }
    }
}
