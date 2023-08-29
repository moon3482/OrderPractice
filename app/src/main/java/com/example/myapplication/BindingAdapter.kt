package com.example.myapplication

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.myapplication.util.toKRWString

@BindingAdapter("bind:price")
fun TextView.setPrice(price:Int){
    this.text = price.toKRWString()
}