package com.lebahakatsuki.menuapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lebahakatsuki.menuapp.data.model.OrderEntity

// DAO of Order
@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOrder(order: OrderEntity)

    @Query("SELECT * FROM order_table ORDER BY id ASC")
    fun getAllOrder(): LiveData<List<OrderEntity>>
}