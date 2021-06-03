package com.lebahakatsuki.menuapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lebahakatsuki.menuapp.data.model.Menu

class DrinkViewModel: ViewModel() {
    private val mutableSelectedDrink = MutableLiveData<Menu>()
    val selectedDrink: LiveData<Menu> get() = mutableSelectedDrink

    fun selectDrink(menu: Menu) {
        mutableSelectedDrink.value = menu
    }
}