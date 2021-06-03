package com.lebahakatsuki.menuapp.data.model

import com.google.gson.annotations.SerializedName

data class Menu(
    @field:SerializedName("id")
    val id: String? = null,
    @field:SerializedName("nama")
    val nama: String? = null,
    @field:SerializedName("harga")
    val harga: Int? = null,
    var flagSelected: Int? = 0
)
