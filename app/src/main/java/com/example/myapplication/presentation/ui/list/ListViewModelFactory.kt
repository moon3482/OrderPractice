package com.example.myapplication.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.domain.GetMenuListUsecase

class ListViewModelFactory(private val getMenuListUsecase: GetMenuListUsecase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            ListViewModel(getMenuListUsecase) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}