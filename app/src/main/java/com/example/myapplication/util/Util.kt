package com.example.myapplication.util

import java.text.DecimalFormat

val decimalFormat = DecimalFormat("#,###")
fun Int.toKRWString(): String = "${decimalFormat.format(this)}원"