package com.lebahakatsuki.menuapp.data.network

import com.google.gson.JsonObject
import com.lebahakatsuki.menuapp.BuildConfig
import com.lebahakatsuki.menuapp.data.model.AddMenuRequestModel
import com.lebahakatsuki.menuapp.data.model.AddMenuResponseModel
import com.lebahakatsuki.menuapp.data.model.GetMenuResponseModel
import retrofit2.Call
import retrofit2.http.*

interface RetrofitApi {
    @POST(BuildConfig.ADD_MENU)
    fun addMenu(@Body addMenuRequestModel: AddMenuRequestModel): Call<AddMenuResponseModel>

    @GET(BuildConfig.GET_FOOD)
    fun getFood(): Call<GetMenuResponseModel>

    @GET(BuildConfig.GET_DRINK)
    fun getDrink(): Call<GetMenuResponseModel>

}