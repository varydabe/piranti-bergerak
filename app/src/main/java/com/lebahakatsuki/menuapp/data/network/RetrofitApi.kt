package com.lebahakatsuki.menuapp.data.network

import com.lebahakatsuki.menuapp.BuildConfig
import com.lebahakatsuki.menuapp.data.model.AddMenuRequestModel
import com.lebahakatsuki.menuapp.data.model.AddMenuResponseModel
import com.lebahakatsuki.menuapp.data.model.GetMenuResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitApi {
    @Headers("Content-Type: application/json")
    @POST(BuildConfig.ADD_MENU)
    fun addMenu(@Body addMenu: AddMenuRequestModel): Call<AddMenuResponseModel>

    @GET(BuildConfig.GET_FOOD)
    fun getFood(): Call<GetMenuResponseModel>

    @GET(BuildConfig.GET_DRINK)
    fun getDrink(): Call<GetMenuResponseModel>

}