package com.example.myapplication.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Event
import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.ListMenu

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _selectedListMenu: MutableLiveData<ListMenu.Menu> = MutableLiveData()
    val selectedListMenu: LiveData<ListMenu.Menu>
        get() = _selectedListMenu
    private val _isShowIce: MutableLiveData<Boolean> = MutableLiveData()
    val isShowIce
        get() = _isShowIce
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
        val listMenu = savedStateHandle.get<ListMenu.Menu>("menu")
        if (listMenu == null) {
            _event.value = Event.ERROR
            return
        }
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

    fun setIsShowIce(isShow: Boolean) {
        _isShowIce.value = isShow
    }
}
