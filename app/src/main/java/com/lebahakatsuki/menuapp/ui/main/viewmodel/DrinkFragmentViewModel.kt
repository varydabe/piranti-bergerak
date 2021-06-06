package com.lebahakatsuki.menuapp.ui.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lebahakatsuki.menuapp.data.database.DrinkDatabase
import com.lebahakatsuki.menuapp.data.model.DrinkEntity
import com.lebahakatsuki.menuapp.data.model.GetMenuResponseModel
import com.lebahakatsuki.menuapp.data.repository.DrinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Drinkfragment viewmodel
class DrinkFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val getAllDrink: LiveData<List<DrinkEntity>>
    private val repository: DrinkRepository
    private var getMenuResponseModel: MutableLiveData<GetMenuResponseModel> = MutableLiveData()

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

    fun getDrink(): LiveData<GetMenuResponseModel> {
        DrinkRepository.init()
        getMenuResponseModel = DrinkRepository.getDrink()
        return getMenuResponseModel
    }
}