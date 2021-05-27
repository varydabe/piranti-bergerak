package com.lebahakatsuki.menuapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.lebahakatsuki.menuapp.data.model.AddMenuRequestModel
import com.lebahakatsuki.menuapp.data.model.AddMenuResponseModel
import com.lebahakatsuki.menuapp.data.model.GetMenuResponseModel
import com.lebahakatsuki.menuapp.data.repository.MenuRepository

class AddMenuViewModel: ViewModel() {
    private var addMenuResponseModel: MutableLiveData<AddMenuResponseModel> = MutableLiveData()

    fun addMenu(addMenuRequestModel: AddMenuRequestModel) {
        MenuRepository.init(addMenuRequestModel)
        addMenuResponseModel = MenuRepository.getAddMenu()
    }

    fun getAddMenu(): LiveData<AddMenuResponseModel> {
        return addMenuResponseModel
    }
}