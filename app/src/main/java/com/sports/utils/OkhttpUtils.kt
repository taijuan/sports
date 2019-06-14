package com.sports.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

lateinit var okHttpClient: OkHttpClient

fun initOkHttp() {
    okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .dns(HttpDns())
        .build()
}