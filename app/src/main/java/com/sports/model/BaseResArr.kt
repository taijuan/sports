package com.sports.model

import com.google.gson.annotations.SerializedName

/**
 * 直接解析data为数组对象
 */
data class BaseResArr<T>(
    @SerializedName("resCode") val code: String,
    @SerializedName("resObject") val data: List<T>,
    @SerializedName("resMsg") val msg: String
)