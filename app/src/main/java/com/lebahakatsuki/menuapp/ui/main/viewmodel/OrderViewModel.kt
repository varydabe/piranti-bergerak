package com.lebahakatsuki.menuapp.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lebahakatsuki.menuapp.data.database.OrderDatabase
import com.lebahakatsuki.menuapp.data.model.OrderEntity
import com.lebahakatsuki.menuapp.data.repository.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Order viewmodel
class OrderViewModel(application: Application): AndroidViewModel(application) {
    val getAllOrder: LiveData<List<OrderEntity>>
    private val repository: OrderRepository

    init {
        val orderDao = OrderDatabase.getDatabase(application).orderDao()
        repository = OrderRepository(orderDao)
        getAllOrder = repository.getAllOrder
    }

    fun addOrder(orderEntity: OrderEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addOrder(orderEntity)
        }
    }
}