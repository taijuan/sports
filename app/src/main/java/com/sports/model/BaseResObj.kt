package com.sports.model

import com.google.gson.annotations.SerializedName

/**
 * 直接解析data为非数组对象
 */
data class BaseResObj<T>(
    @SerializedName("resCode") val code: String,
    @SerializedName("resObject") val data: T,
    @SerializedName("resMsg") val msg: String
)