package com.sports.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * 分页数据对象
 */
data class PageData<T>(
    @SerializedName("dateList") val dataList: MutableList<T>,
    @SerializedName("totalPage") val totalPage: Int,
    @SerializedName("currentPage") val curPage: Int,
    @Expose(serialize = false, deserialize = false)
    var state: PageState = PageState.Empty,
    @Expose(serialize = false, deserialize = false)
    var errorMsg: String = ""
)
