package com.lebahakatsuki.menuapp.data.model

import com.google.gson.annotations.SerializedName

data class AddMenuRequestModel(
    @field:SerializedName("nama")
    val nama: String? = null,
    @field:SerializedName("harga")
    val harga: Int = 0,
    @field:SerializedName("kategori")
    val kategori: String? = null
)
