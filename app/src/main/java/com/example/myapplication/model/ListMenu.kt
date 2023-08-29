package com.example.myapplication.model

import java.io.Serializable

sealed class ListMenu : Serializable {
    abstract val name: String
    abstract val price: Int

    data class Coffee(
        override val name: String,
        override val price: Int,
    ) : ListMenu()

    data class Ade(
        override val name: String,
        override val price: Int,
    ) : ListMenu()

    data class Tea(
        override val name: String,
        override val price: Int,
    ) : ListMenu()

    data class Desert(
        override val name: String,
        override val price: Int,
    ) : ListMenu()
}