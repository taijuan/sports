package com.sports.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.sports.utils.uuid

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
    @Keep
    @SerializedName("deviceType")
    val deviceType: String = "1"
    @Keep
    @SerializedName("deviceUid")
    val deviceUid: String = uuid
}