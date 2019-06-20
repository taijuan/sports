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
open class BaseReq {
    @SerializedName("deviceType")
    val deviceType: String = "1"
    @SerializedName("deviceUid")
    val deviceUid: String = "deviceUid"
}