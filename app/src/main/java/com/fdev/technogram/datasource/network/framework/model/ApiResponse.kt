package com.fdev.technogram.datasource.network.framework.model

import com.google.gson.annotations.SerializedName

class ApiResponse<Data>(
        @SerializedName("data")
        val data: Data?,
        @SerializedName("message")
        val message: String,
)