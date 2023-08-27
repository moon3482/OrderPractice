package com.example.myapplication.model

sealed class ListMenu(
    open val name: String,
    open val price: Int,
) {
    data class Coffee(
        override val name: String,
        override val price: Int,
    ) : ListMenu(name = name, price = price)

    data class Ade(
        override val name: String,
        override val price: Int,
    ) : ListMenu(name = name, price = price)

    data class Tea(
        override val name: String,
        override val price: Int,
    ) : ListMenu(name = name, price = price)

    data class Desert(
        override val name: String,
        override val price: Int,
    ) : ListMenu(name = name, price = price)
}