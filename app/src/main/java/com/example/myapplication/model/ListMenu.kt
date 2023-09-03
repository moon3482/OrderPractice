package com.example.myapplication.model

import java.io.Serializable

data class ListMenu(
    val name:String,
    val price:Int,
    val menuType: MenuType,
)