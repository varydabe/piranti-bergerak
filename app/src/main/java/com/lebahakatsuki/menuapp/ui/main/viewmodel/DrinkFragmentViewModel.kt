package com.lebahakatsuki.menuapp.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.lebahakatsuki.menuapp.data.database.DrinkDatabase
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lebahakatsuki.menuapp.data.model.DrinkEntity
import com.lebahakatsuki.menuapp.data.model.FoodDrink
import com.lebahakatsuki.menuapp.data.repository.DrinkRepository
import com.lebahakatsuki.menuapp.data.resource.DrinksData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DrinkFragmentViewModel(application: Application): AndroidViewModel(application) {
    val getAllDrink: LiveData<List<DrinkEntity>>
    private val repository: DrinkRepository

    init {
        val drinkDao = DrinkDatabase.getDatabase(application).drinkDao()
        repository = DrinkRepository(drinkDao)
        getAllDrink = repository.getAllDrink
    }

    fun addDrink(drinkEntity: DrinkEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDrink(drinkEntity)
        }
    }
    /*
    var arrayListMutableLiveData = MutableLiveData<ArrayList<FoodDrink>>()

    var arrayList = ArrayList<FoodDrink>()

    fun getArrayList(): MutableLiveData<ArrayList<FoodDrink>> {

        arrayList.addAll(DrinksData.listData)

        arrayListMutableLiveData.value = arrayList

        return arrayListMutableLiveData
    }*/
}