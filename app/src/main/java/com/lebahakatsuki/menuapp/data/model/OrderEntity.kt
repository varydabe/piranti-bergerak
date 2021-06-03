package com.lebahakatsuki.menuapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
data class OrderEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val orderDate: Long,
        val foodName: String,
        val foodPrice: Int,
        val drinkName: String,
        val drinkPrice: Int,
        val totalPrice: Int
)
