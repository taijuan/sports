package com.sports.model

import com.google.gson.annotations.SerializedName

data class WangYiUser(
    @SerializedName("token") val token: String,
    @SerializedName("accid") val id: String
)

data class WangYiBaseRes<T>(
    @SerializedName("code") val code: String,
    @SerializedName("info") val data: T,
    @SerializedName("desc") val errorMsg: String
)