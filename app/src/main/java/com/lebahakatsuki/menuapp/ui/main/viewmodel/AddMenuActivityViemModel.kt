package com.lebahakatsuki.menuapp.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lebahakatsuki.menuapp.data.database.DrinkDatabase
import com.lebahakatsuki.menuapp.data.database.FoodDatabase
import com.lebahakatsuki.menuapp.data.model.DrinkEntity
import com.lebahakatsuki.menuapp.data.model.FoodEntity
import com.lebahakatsuki.menuapp.data.repository.DrinkRepository
import com.lebahakatsuki.menuapp.data.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddMenuActivityViemModel(application: Application): AndroidViewModel(application) {
    private val getAllFood: LiveData<List<FoodEntity>>
    private val foodRepository: FoodRepository
    private val getAllDrink: LiveData<List<DrinkEntity>>
    private val drinkRepository: DrinkRepository

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        foodRepository = FoodRepository(foodDao)
        getAllFood = foodRepository.getAllFood
    }

    init {
        val drinkDao = DrinkDatabase.getDatabase(application).drinkDao()
        drinkRepository = DrinkRepository(drinkDao)
        getAllDrink = drinkRepository.getAllDrink
    }

    fun addFood(foodEntity: FoodEntity){
        viewModelScope.launch(Dispatchers.IO){
            foodRepository.addFood(foodEntity)
        }
    }

    fun addDrink(drinkEntity: DrinkEntity){
        viewModelScope.launch(Dispatchers.IO){
            drinkRepository.addDrink(drinkEntity)
        }
    }
}