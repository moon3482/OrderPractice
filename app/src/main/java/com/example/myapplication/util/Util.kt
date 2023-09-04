package com.example.myapplication.util

import com.example.myapplication.model.IcePortion
import com.example.myapplication.model.OrderMenu
import java.text.DecimalFormat

val decimalFormat = DecimalFormat("#,###")
fun Int.toKRWString(): String = "${decimalFormat.format(this)}원"
fun OrderMenu.toOptionString(): String{
    val optionList = mutableListOf<String>()
    when (isHot) {
        true -> optionList.add("HOT")
        false -> optionList.add("ICE")
        null -> Unit
    }
    when (isCaffeine) {
        true -> optionList.add("카페인")
        false -> optionList.add("디카페인")
        null -> Unit
    }
    when (icePortion) {
        IcePortion.SMALL -> optionList.add("얼음(적게)")
        IcePortion.MEDIUM -> optionList.add("얼음(보통)")
        IcePortion.LARGE -> optionList.add("얼음(많이)")
        null -> Unit
    }
    return optionList.joinToString(separator = "/")
}
