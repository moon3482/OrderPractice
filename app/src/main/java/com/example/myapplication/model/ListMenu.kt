package com.example.myapplication.model

import java.io.Serializable

sealed class ListMenu : Serializable {
    abstract val name: String
    abstract val price: Int?
    abstract val isChangTemp: Boolean?
    abstract val isChangCaffeine: Boolean?
    abstract val menuType: MenuType?

    data class Header(
        override val name: String
    ) : ListMenu() {
        override val isChangTemp: Boolean?
            get() = null
        override val isChangCaffeine: Boolean?
            get() = null
        override val price: Int?
            get() = null
        override val menuType: MenuType?
            get() = null
    }

    data class Menu(
        override val name: String,
        override val price: Int,
        override val isChangTemp: Boolean,
        override val isChangCaffeine: Boolean,
        override val menuType: MenuType
    ) : ListMenu()
}
