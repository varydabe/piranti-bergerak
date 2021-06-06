package com.lebahakatsuki.menuapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lebahakatsuki.menuapp.data.database.DrinkDao
import com.lebahakatsuki.menuapp.data.model.DrinkEntity
import com.lebahakatsuki.menuapp.data.model.GetMenuResponseModel
import com.lebahakatsuki.menuapp.data.network.RetrofitApi
import com.lebahakatsuki.menuapp.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Drink Repository
class DrinkRepository(private val drinkDao: DrinkDao) {
    val getAllDrink: LiveData<List<DrinkEntity>> = drinkDao.getAllDrink()

    //Add Drink with Drink Entity using Drink DAO
    suspend fun addDrink(drinkEntity: DrinkEntity){
        drinkDao.addDrink(drinkEntity)
    }

    companion object {
        var dataSet: MutableLiveData<GetMenuResponseModel> = MutableLiveData()

        fun init() {
            val api: RetrofitApi = RetrofitClient.retrofit.create(RetrofitApi::class.java)
            api.getDrink().enqueue(object : Callback<GetMenuResponseModel> {
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

        //Get Drink with GetMenuResponseModel
        fun getDrink(): MutableLiveData<GetMenuResponseModel> {
            return dataSet
        }
    }
}