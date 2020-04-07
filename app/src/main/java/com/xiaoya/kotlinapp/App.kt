package com.xiaoya.kotlinapp

import android.app.Application
import android.content.Context
import android.content.ContextWrapper

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-30
 * @desc:
 **/
private lateinit var INSTANCE : Application

class App:Application(){
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    override fun attachBaseContext(base: Context?) {
        //todo

        super.attachBaseContext(base)
    }
}

object AppContext:ContextWrapper(INSTANCE)