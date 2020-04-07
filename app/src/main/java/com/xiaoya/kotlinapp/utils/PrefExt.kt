package com.xiaoya.kotlinapp.utils

import com.xiaoya.common.shareperferences.Preference
import com.xiaoya.kotlinapp.AppContext
import kotlin.reflect.jvm.jvmName

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-06
 * @desc:
 **/
inline fun <reified R,T> R.pref(default:T)=
        Preference(AppContext,"",default,R::class.jvmName)