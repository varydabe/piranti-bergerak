package com.lebahakatsuki.menuapp.data.model

import com.google.gson.annotations.SerializedName

data class AddMenuResponseModel(
    @field:SerializedName("values")
    val values: Menu? = null,
    @field:SerializedName("message")
    val message: String? = null
)
