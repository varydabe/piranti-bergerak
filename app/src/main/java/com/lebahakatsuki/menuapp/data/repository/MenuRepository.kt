package com.lebahakatsuki.menuapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.lebahakatsuki.menuapp.data.model.AddMenuRequestModel
import com.lebahakatsuki.menuapp.data.model.AddMenuResponseModel
import com.lebahakatsuki.menuapp.data.model.GetMenuResponseModel
import com.lebahakatsuki.menuapp.data.network.RetrofitApi
import com.lebahakatsuki.menuapp.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuRepository {
    companion object {
        var dataSet: MutableLiveData<AddMenuResponseModel> = MutableLiveData()

        fun init(addMenuRequestModel: AddMenuRequestModel) {
            val api: RetrofitApi = RetrofitClient.retrofit.create(RetrofitApi::class.java)

            val call: Call<AddMenuResponseModel> = api.addMenu(addMenuRequestModel)

            call.enqueue(object : Callback<AddMenuResponseModel> {
                override fun onResponse(
                    call: Call<AddMenuResponseModel>,
                    response: Response<AddMenuResponseModel>
                ) {
                    if (response.isSuccessful){
                        dataSet.value = response.body()
                    } else {
                        dataSet.value = null
                    }
                }

                override fun onFailure(call: Call<AddMenuResponseModel>, t: Throwable) {
                    dataSet.value = null
                    Log.e("DATA", t.message.toString())
                }

            })
        }

        fun getAddMenu(): MutableLiveData<AddMenuResponseModel> {
            return dataSet
        }
    }
}