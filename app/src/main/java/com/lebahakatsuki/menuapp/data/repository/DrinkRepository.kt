package com.lebahakatsuki.menuapp.data.repository

import androidx.lifecycle.LiveData
import com.lebahakatsuki.menuapp.data.database.DrinkDao
import com.lebahakatsuki.menuapp.data.model.DrinkEntity

class DrinkRepository(private val drinkDao: DrinkDao) {
    val getAllDrink: LiveData<List<DrinkEntity>> = drinkDao.getAllDrink()

    suspend fun addDrink(drinkEntity: DrinkEntity){
        drinkDao.addDrink(drinkEntity)
    }
}