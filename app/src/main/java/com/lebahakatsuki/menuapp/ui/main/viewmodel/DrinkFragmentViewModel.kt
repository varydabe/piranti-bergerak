package com.lebahakatsuki.menuapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import com.lebahakatsuki.menuapp.data.resource.DrinksData

class DrinkFragmentViewModel: ViewModel() {
    var arrayListMutableLiveData = MutableLiveData<ArrayList<FoodDrink>>()

    var arrayList = ArrayList<FoodDrink>()

    fun getArrayList(): MutableLiveData<ArrayList<FoodDrink>> {

        arrayList.addAll(DrinksData.listData)

        arrayListMutableLiveData.value = arrayList

        return arrayListMutableLiveData
    }
}