package com.sports.api

import androidx.lifecycle.LiveData
import com.sports.model.*
import com.sports.utils.okHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

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
    Retrofit.Builder().baseUrl("https://api-cn.ronghub.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(createCallAdapterFactory())
        .callFactory(
            okHttpClient
        )
        .build()
        .create(SportService::class.java)
}


val testService: SportService by lazy {
    Retrofit.Builder().baseUrl("http://3.113.59.250/sport-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(createCallAdapterFactory())
        .callFactory(
            okHttpClient
        )
        .build()
        .create(SportService::class.java)
}
//http://3.113.59.250/sport-api/
//https://api.netease.im/nimserver/user/update.action

val wangYiService: SportService by lazy {
    Retrofit.Builder().baseUrl("https://api.netease.im/")
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
    @POST("user/getToken.json")
    fun getUserToken(
        @Header("App-Key") key: String,
        @Header("Timestamp") time: String,
        @Header("Nonce") roundNum: String,
        @Header("Signature") sha1: String,
        @Field("userId") userId: String
    ): LiveData<SuccessError<YongYunUser>>


    ///nimserver/user/update.action
//AppKey 	开发者平台分配的appkey
//Nonce 	随机数（最大长度128个字符）
//CurTime 	当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
//CheckSum 	SHA1(AppSecret + Nonce + CurTime)，三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符(String，小写)
    @FormUrlEncoded
    @POST("nimserver/user/create.action")
    fun createWYUser(
        @Header("AppKey") key: String,
        @Header("CurTime") time: String,
        @Header("Nonce") roundNum: String,
        @Header("CheckSum") sha1: String,
        @Field("accid") userId: String
    ): LiveData<SuccessError<WangYiBaseRes<WangYiUser>>>

    @FormUrlEncoded
    @POST("nimserver/chatroom/create.action")
    fun createChatRoom(
        @Header("AppKey") key: String,
        @Header("CurTime") time: String,
        @Header("Nonce") roundNum: String,
        @Header("CheckSum") sha1: String,
        @Field("creator") chatRoomId: String,
        @Field("name") name: String
    ): LiveData<SuccessError<WangYiBaseRes<WangYiUser>>>


    @FormUrlEncoded
    @POST("user/getRegCode")
    fun testApi(
        @Field("phone") phone: String
    ): LiveData<SuccessError<BaseRes<String>>>
}