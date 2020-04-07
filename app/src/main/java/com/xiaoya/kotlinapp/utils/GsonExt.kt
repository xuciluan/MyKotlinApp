package com.xiaoya.kotlinapp.utils

import com.google.gson.Gson

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-06
 * @desc:
 **/
inline fun <reified T> Gson.fromJson2(json:String)= fromJson(json,T::class.java)