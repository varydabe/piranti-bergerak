package com.lebahakatsuki.menuapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import com.lebahakatsuki.menuapp.data.resource.DrinksData
import com.lebahakatsuki.menuapp.data.resource.FoodsData

class MenuActivityViewModel: ViewModel() {
    var arrayFoodListMutableLiveData = MutableLiveData<ArrayList<FoodDrink>>()
    var arrayDrinkListMutableLiveData = MutableLiveData<ArrayList<FoodDrink>>()

    var arrayFoodList = ArrayList<FoodDrink>()
    var arrayDrinkList = ArrayList<FoodDrink>()

    fun getFoodArrayList(): MutableLiveData<ArrayList<FoodDrink>>{

        arrayFoodList.addAll(FoodsData.listData)

        arrayFoodListMutableLiveData.value = arrayFoodList

        return arrayFoodListMutableLiveData
    }

    fun getDrinkArrayList(): MutableLiveData<ArrayList<FoodDrink>> {

        arrayDrinkList.addAll(DrinksData.listData)

        arrayDrinkListMutableLiveData.value = arrayDrinkList

        return arrayDrinkListMutableLiveData
    }
}