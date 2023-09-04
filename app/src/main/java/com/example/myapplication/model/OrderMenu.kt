package com.example.myapplication.model


sealed class OrderMenu {
    abstract val name: String
    abstract val price: Int
    abstract val isHot: Boolean?
    abstract val isCaffeine: Boolean?
    abstract val icePortion: IcePortion?

    data class Coffee(
        override val name: String,
        override val price: Int,
        override val isHot: Boolean,
        override val isCaffeine: Boolean,
        override val icePortion: IcePortion?,
    ) : OrderMenu()

    data class Ade(
        override val name: String,
        override val price: Int,
        override val icePortion: IcePortion,
    ) : OrderMenu() {
        override val isHot: Boolean? = null
        override val isCaffeine: Boolean? = null
    }

    data class Tea(
        override val name: String,
        override val price: Int,
        override val isCaffeine: Boolean,
    ) : OrderMenu() {
        override val isHot: Boolean? = null
        override val icePortion: IcePortion? = null

    }

    data class Desert(
        override val name: String,
        override val price: Int,
    ) : OrderMenu() {
        override val isHot: Boolean? = null
        override val isCaffeine: Boolean? = null
        override val icePortion: IcePortion? = null
    }
}
