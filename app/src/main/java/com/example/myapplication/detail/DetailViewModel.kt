package com.example.myapplication.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.myapplication.model.Event
import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.ListMenu
import com.example.myapplication.model.MenuType

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _selectedListMenu: MutableLiveData<ListMenu> = MutableLiveData()
    val selectedListMenu: LiveData<ListMenu>
        get() = _selectedListMenu
    val isShowTemp
        get() = _selectedListMenu.map { listMenu ->
            when (listMenu.menuType) {
                MenuType.COFFEE -> true
                MenuType.ADE,
                MenuType.TEA,
                MenuType.DESERT -> false
            }
        }
    val isShowCaffeine
        get() = _selectedListMenu.map { listMenu ->
            when (listMenu.menuType) {
                MenuType.COFFEE,
                MenuType.TEA -> true

                MenuType.ADE,
                MenuType.DESERT -> false
            }
        }
    val isShowIce
        get() = _selectedListMenu.map { listMenu ->
            when (listMenu.menuType) {
                MenuType.COFFEE -> true

                MenuType.ADE -> true
                MenuType.TEA,
                MenuType.DESERT -> false
            }
        }
    private val _isHot: MutableLiveData<Boolean> = MutableLiveData()
    val isHot: LiveData<Boolean>
        get() = _isHot
    private val _isCaffeine: MutableLiveData<Boolean> = MutableLiveData()
    val isCaffeine: LiveData<Boolean>
        get() = _isCaffeine
    private val _icePortion: MutableLiveData<IcePortion?> = MutableLiveData()
    val icePortion: LiveData<IcePortion?>
        get() = _icePortion
    private val _event: MutableLiveData<Event> = MutableLiveData()
    val event: LiveData<Event>
        get() = _event

    init {
        setSelectedListMenu()
    }

    private fun setSelectedListMenu() {
        val name = savedStateHandle.get<String>("menuName")
        val price = savedStateHandle.get<Int>("menuPrice")
        val menuType = savedStateHandle.get<MenuType>("menuType")
        if (menuType == null || name == null || price == null) {
            _event.value = Event.ERROR
            return
        }
        val listMenu = ListMenu(name, price, menuType)
        _selectedListMenu.value = listMenu
    }

    fun setTemp(isHot: Boolean) {
        _isHot.value = isHot
    }

    fun setCaffeine(isCaffeine: Boolean) {
        _isCaffeine.value = isCaffeine
    }

    fun setIcePortion(icePortion: IcePortion?) {
        _icePortion.value = icePortion
    }
}
