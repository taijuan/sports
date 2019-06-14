package com.sports.api

import androidx.lifecycle.LiveData
import com.sports.model.BaseResObj
import com.sports.model.PageData
import com.sports.model.News
import com.sports.utils.okHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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
}