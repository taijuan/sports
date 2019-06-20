package com.sports.model

import com.google.gson.annotations.SerializedName

/**
 * {
"deviceType": 0,
"deviceUid": "string",
"phone": "string",
"phoneCode": "string",
"phonePwd": "string",
"regIp": "string"
}
 */
data class BaseReq(
    @SerializedName("deviceType") val deviceType: String,
    @SerializedName("deviceUid") val deviceUid: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("phoneCode") val phoneCode: String,
    @SerializedName("phonePwd") val phonePwd: String,
    @SerializedName("regIp") val regIp: String
)