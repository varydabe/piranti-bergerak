package com.lebahakatsuki.menuapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lebahakatsuki.menuapp.data.database.FoodDao
import com.lebahakatsuki.menuapp.data.model.FoodEntity
import com.lebahakatsuki.menuapp.data.model.GetMenuResponseModel
import com.lebahakatsuki.menuapp.data.network.RetrofitApi
import com.lebahakatsuki.menuapp.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodRepository(private val foodDao: FoodDao) {
    val getAllFood: LiveData<List<FoodEntity>> = foodDao.getAllFood()

    //Add Food with Food Entity
    suspend fun addFood(foodEntity: FoodEntity){
        foodDao.addFood(foodEntity)
    }

    companion object {
        var dataSet: MutableLiveData<GetMenuResponseModel> = MutableLiveData()

        fun init() {
            val api: RetrofitApi = RetrofitClient.retrofit.create(RetrofitApi::class.java)
            api.getFood().enqueue(object : Callback<GetMenuResponseModel>{
                override fun onResponse(
                    call: Call<GetMenuResponseModel>,
                    response: Response<GetMenuResponseModel>
                ) {
                    val getMenuResponseModel: GetMenuResponseModel = response.body()!!
                    dataSet.value = getMenuResponseModel
                }

                override fun onFailure(call: Call<GetMenuResponseModel>, t: Throwable) {
                    Log.e("DATA", t.message.toString())
                }
            })
        }

        //Get Food with GetMenuResponseModel
        fun getFood(): MutableLiveData<GetMenuResponseModel> {
            return dataSet
        }
    }
}