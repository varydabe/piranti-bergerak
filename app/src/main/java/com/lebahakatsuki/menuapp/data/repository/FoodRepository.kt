package com.lebahakatsuki.menuapp.data.repository

import androidx.lifecycle.LiveData
import com.lebahakatsuki.menuapp.data.database.FoodDao
import com.lebahakatsuki.menuapp.data.model.FoodEntity

class FoodRepository(private val foodDao: FoodDao) {
    val getAllFood: LiveData<List<FoodEntity>> = foodDao.getAllFood()

    suspend fun addFood(foodEntity: FoodEntity){
        foodDao.addFood(foodEntity)
    }
}