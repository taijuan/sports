package com.sports.api

import androidx.lifecycle.LiveData
import com.sports.model.BaseResObj
import com.sports.model.News
import com.sports.model.PageData
import com.sports.model.UserToken
import com.sports.utils.okHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

val sportService: SportService by lazy {
    Retrofit.Builder().baseUrl("https://api.cdeclips.com/hknews-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(createCallAdapterFactory())
        .callFactory(
            okHttpClient
        )
        .build()
        .create(SportService::class.java)
}

val rongYunService: SportService by lazy {
    Retrofit.Builder().baseUrl("https://api-cn.ronghub.com")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(createCallAdapterFactory())
        .callFactory(
            okHttpClient
        )
        .build()
        .create(SportService::class.java)
}

interface SportService {
    /**
     * currentPage=$curPage&dataType=1
     */
    @FormUrlEncoded
    @POST("selectNewsList")
    fun getUser(
        @Field("subjectCode") code: String,
        @Field("currentPage") curPage: Int,
        @Field("dataType") type: Int
    ): LiveData<SuccessError<BaseResObj<PageData<News>>>>

    @FormUrlEncoded
    @POST("/user/getToken.json")
    fun getUserToken(
        @Header("App-Key") key: String,
        @Header("Timestamp") time: String,
        @Header("Nonce") roundNum: String,
        @Header("Signature") sha1: String,
        @Field("userId") userId: String
    ): LiveData<SuccessError<UserToken>>
}