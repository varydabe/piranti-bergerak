package com.lebahakatsuki.menuapp.data.repository

import androidx.lifecycle.LiveData
import com.lebahakatsuki.menuapp.data.database.OrderDao
import com.lebahakatsuki.menuapp.data.model.OrderEntity
import javax.inject.Inject

class OrderRepository @Inject constructor(private val orderDao: OrderDao) {
    val getAllOrder: LiveData<List<OrderEntity>> = orderDao.getAllOrder()

    //Add Order with Order Entity
    suspend fun addOrder(orderEntity: OrderEntity){
        orderDao.addOrder(orderEntity)
    }
}