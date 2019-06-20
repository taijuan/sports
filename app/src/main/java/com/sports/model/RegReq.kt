package com.sports.model

import com.google.gson.annotations.SerializedName

data class RegReq(
    @SerializedName("phone") val phone: String,
    @SerializedName("phoneCode") val phoneCode: String,
    @SerializedName("phonePwd") val phonePwd: String,
    @SerializedName("regIp") val regIp: String
): BaseReq()