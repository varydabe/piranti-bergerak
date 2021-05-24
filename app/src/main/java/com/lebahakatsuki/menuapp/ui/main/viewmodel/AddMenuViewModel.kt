package com.lebahakatsuki.menuapp.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lebahakatsuki.menuapp.data.model.AddMenuRequestModel
import com.lebahakatsuki.menuapp.data.model.AddMenuResponseModel
import com.lebahakatsuki.menuapp.data.repository.MenuRepository

class AddMenuViewModel: ViewModel() {
    fun addMenu(addMenuRequestModel: AddMenuRequestModel) {
        MenuRepository.init(addMenuRequestModel)
    }
}