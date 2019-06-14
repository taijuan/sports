package com.sports.model

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("title") val title: String,
    @SerializedName("bigTitleImage") val imageUrl: String
)