package com.xiaoya.kotlinapp.utils

import android.content.Context
import android.provider.Settings

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-30
 * @desc:
 **/

val Context.deviceId :String
    get() = Settings.Secure.getString(
            contentResolver,
            Settings.Secure.ANDROID_ID
    )