package com.example.myapplication.model


sealed class OrderMenu{
    abstract val name: String
    abstract val price: Int
    abstract val isHot: Boolean?
    abstract val isCaffeine: Boolean?

    data class Coffee(
        override val name: String,
        override val price: Int,
        override val isHot: Boolean,
        override val isCaffeine: Boolean,
        val ice:IcePortion?,
    ) : OrderMenu()

    data class Ade(
        override val name: String,
        override val price: Int,
        val ice:IcePortion,
    ):OrderMenu(){
        override val isHot: Boolean = false
        override val isCaffeine: Boolean = false
    }

    data class Tea(
        override val name: String,
        override val price: Int,
        override val isCaffeine: Boolean,
    ):OrderMenu(){
        override val isHot: Boolean = true

    }

    data class Desert(
        override val name: String,
        override val price: Int,
    ):OrderMenu(){
        override val isHot: Boolean? = null
        override val isCaffeine: Boolean? = null
    }

}
