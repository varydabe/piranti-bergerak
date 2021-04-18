package com.lebahakatsuki.menuapp.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.lebahakatsuki.menuapp.data.database.FoodDatabase
import com.lebahakatsuki.menuapp.data.model.FoodEntity
import com.lebahakatsuki.menuapp.data.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddMenuActivityViemModel(application: Application): AndroidViewModel(application) {
    private val getAllFood: LiveData<List<FoodEntity>>
    private val repository: FoodRepository

    init {
        val foodDao = FoodDatabase.getDatabase(application).foodDao()
        repository = FoodRepository(foodDao)
        getAllFood = repository.getAllFood
    }

    fun addFood(foodEntity: FoodEntity){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFood(foodEntity)
        }
    }
}