package com.xiaoya.kotlinapp.network

import com.xiaoya.kotlinapp.AppContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit.SECONDS
import java.util.logging.Level

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-30
 * @desc:
 **/

private const val BASE_URL= "https://api.github/com"

private val cacheFile by lazy{
    File(AppContext.cacheDir,"webServiceApi").apply{
        ensureDir()
    }
}

val retrofit by lazy{
    Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder()
                    .connectTimeout(60,SECONDS)
                    .readTimeout(60,SECONDS)
                    .cache(Cache(cacheFile,1024 * 1024))
                    .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
                    .build())
            .baseUrl(BASE_URL)
            .build()
}