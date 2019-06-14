package com.sports.model

import com.google.gson.annotations.SerializedName

data class YongYunUser(
    @SerializedName("token") val token: String,
    @SerializedName("userId") val userId: String
)