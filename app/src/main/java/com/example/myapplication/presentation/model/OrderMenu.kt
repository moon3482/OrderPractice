package com.example.myapplication.presentation.model


data class OrderMenu(
    val name: String,
    val price: Int,
    val isHot: Boolean?,
    val isCaffeine: Boolean?,
    val icePortion: IcePortion?,
)
