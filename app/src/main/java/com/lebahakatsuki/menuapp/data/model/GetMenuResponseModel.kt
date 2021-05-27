package com.lebahakatsuki.menuapp.data.model

import com.google.gson.annotations.SerializedName

data class GetMenuResponseModel(
    @field:SerializedName("values")
    val values: List<Menu>? = null,

    @field:SerializedName("message")
    val message: String? = null
)
