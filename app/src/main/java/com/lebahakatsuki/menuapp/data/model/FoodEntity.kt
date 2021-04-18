package com.lebahakatsuki.menuapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val foodName: String,
    val price: String
)
