package com.example.myapplication.domain

import com.example.myapplication.presentation.model.ListMenu
import com.example.myapplication.presentation.model.MenuType

class GetMenuListUsecase {
    operator fun invoke(): List<ListMenu> {
        return mutableListOf(
            ListMenu.Header("Coffee"),
            ListMenu.Menu(
                name = "아메리카노",
                price = 1000,
                isChangTemp = true,
                isChangCaffeine = true,
                menuType = MenuType.COFFEE,
            ),
            ListMenu.Menu(
                name = "카페라떼",
                price = 1500,
                isChangTemp = true,
                isChangCaffeine = true,
                menuType = MenuType.COFFEE,
            ),
            ListMenu.Menu(
                name = "카푸치노",
                price = 2000,
                isChangTemp = true,
                isChangCaffeine = true,
                menuType = MenuType.COFFEE,
            ),
            ListMenu.Header("Beverage"),
            ListMenu.Menu(
                name = "오렌지에이드",
                price = 2500,
                isChangTemp = false,
                isChangCaffeine = false,
                menuType = MenuType.ADE,
            ),
            ListMenu.Menu(
                name = "망고에이드",
                price = 2500,
                isChangTemp = false,
                isChangCaffeine = false,
                menuType = MenuType.ADE,
            ),
            ListMenu.Header("Tea"),
            ListMenu.Menu(
                name = "얼그레이티",
                price = 1000,
                isChangTemp = false,
                isChangCaffeine = true,
                menuType = MenuType.TEA,
            ),
            ListMenu.Menu(
                name = "페퍼민트티",
                price = 1000,
                isChangTemp = false,
                isChangCaffeine = true,
                menuType = MenuType.TEA,
            ),
            ListMenu.Header("Desert"),
            ListMenu.Menu(
                name = "초코케이크",
                price = 3000,
                isChangTemp = false,
                isChangCaffeine = false,
                menuType = MenuType.DESERT,
            ),
            ListMenu.Menu(
                name = "치즈케이크",
                price = 3000,
                isChangTemp = false,
                isChangCaffeine = false,
                menuType = MenuType.DESERT,
            ),
            ListMenu.Menu(
                name = "마들렌",
                price = 1000,
                isChangTemp = false,
                isChangCaffeine = false,
                menuType = MenuType.DESERT,
            ),
            ListMenu.Menu(
                name = "휘낭시에",
                price = 1500,
                isChangTemp = false,
                isChangCaffeine = false,
                menuType = MenuType.DESERT,
            ),
        )
    }
}