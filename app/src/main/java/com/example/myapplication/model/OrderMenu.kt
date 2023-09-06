package com.example.myapplication.model


data class OrderMenu(
    val name: String,
    val price: Int,
    val isHot: Boolean?,
    val isCaffeine: Boolean?,
    val icePortion: IcePortion?,
)
