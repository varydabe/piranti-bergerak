package com.lebahakatsuki.menuapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lebahakatsuki.menuapp.data.model.DrinkEntity

@Dao
interface DrinkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDrink(drink: DrinkEntity)

    @Query("SELECT * FROM drink_table ORDER BY id ASC")
    fun getAllDrink(): LiveData<List<DrinkEntity>>
}