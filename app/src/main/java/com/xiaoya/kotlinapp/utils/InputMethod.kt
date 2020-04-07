package com.xiaoya.kotlinapp.utils

import android.app.Activity
import android.view.View
import org.jetbrains.anko.inputMethodManager

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-07
 * @desc:
 **/

fun View.hideSoftInput():Boolean {
    return context.inputMethodManager.hideSoftInputFromWindow(windowToken,0)
}

fun Activity.hideSoftInput():Boolean {
    return currentFocus?.hideSoftInput()?:false
}