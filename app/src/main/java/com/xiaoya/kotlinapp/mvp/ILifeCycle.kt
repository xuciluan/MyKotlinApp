package com.xiaoya.kotlinapp.mvp

import android.content.res.Configuration
import android.os.Bundle


/**
 * @author : xuciluan@126.com
 * @date: 2020-03-24
 * @desc:
 **/

interface ILifeCycle{
    fun onCreate(savedInstanceState: Bundle?)

    fun onSaveInstanceState(outState: Bundle)

    fun onViewStateRestored(savedInstanceState: Bundle?)

    fun onConfigurationChanged(newConfig: Configuration)

    fun onDestroy()

    fun onStart()

    fun onStop()

    fun onResume()

    fun onPause()
}