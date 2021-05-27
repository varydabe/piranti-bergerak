package com.lebahakatsuki.menuapp.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.lebahakatsuki.menuapp.data.database.FoodDatabase
import com.lebahakatsuki.menuapp.data.model.FoodEntity
import com.lebahakatsuki.menuapp.data.model.GetMenuResponseModel
import com.lebahakatsuki.menuapp.data.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodFragmentViewModel(application: Application): AndroidViewModel(application) {
    val getAllFood: LiveData<List<FoodEntity>>
    private val repository: FoodRepository
    private var getMenuResponseModel: MutableLiveData<GetMenuResponseModel> = MutableLiveData()

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
    /*
    var arrayListMutableLiveData = MutableLiveData<ArrayList<FoodDrink>>()

    var arrayList = ArrayList<FoodDrink>()

    fun getArrayList(): MutableLiveData<ArrayList<FoodDrink>>{

        arrayList.addAll(FoodsData.listData)

        arrayListMutableLiveData.value = arrayList

        return arrayListMutableLiveData
    }*/

    fun getFood(): LiveData<GetMenuResponseModel> {
        FoodRepository.init()
        getMenuResponseModel = FoodRepository.getFood()
        return getMenuResponseModel
    }
}