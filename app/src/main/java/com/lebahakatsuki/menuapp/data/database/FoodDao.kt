package com.lebahakatsuki.menuapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lebahakatsuki.menuapp.data.model.FoodEntity

//DAO of Food Database
@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFood(food: FoodEntity)

    @Query("SELECT * FROM food_table ORDER BY id ASC")
    fun getAllFood(): LiveData<List<FoodEntity>>
}