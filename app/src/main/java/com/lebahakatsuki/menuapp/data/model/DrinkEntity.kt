package com.lebahakatsuki.menuapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drink_table")
data class DrinkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val drinkName: String,
    val price: String
)
