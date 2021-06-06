package com.lebahakatsuki.menuapp.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lebahakatsuki.menuapp.data.model.AddMenuRequestModel
import com.lebahakatsuki.menuapp.data.model.AddMenuResponseModel
import com.lebahakatsuki.menuapp.data.repository.MenuRepository

//Add menu viewmodel
class AddMenuViewModel: ViewModel() {
    private var addMenuResponseModel: MutableLiveData<AddMenuResponseModel> = MutableLiveData()

    fun addMenu(addMenuRequestModel: AddMenuRequestModel) {
        MenuRepository.init(addMenuRequestModel)
        addMenuResponseModel = MenuRepository.getAddMenu()
    }
}