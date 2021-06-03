package com.lebahakatsuki.menuapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lebahakatsuki.menuapp.data.model.Menu

class FoodViewodel: ViewModel() {
    private val mutableSelectedFood = MutableLiveData<Menu>()
    val selectedFood: LiveData<Menu> get() = mutableSelectedFood

    fun selectFood(menu: Menu) {
        mutableSelectedFood.value = menu
    }
}