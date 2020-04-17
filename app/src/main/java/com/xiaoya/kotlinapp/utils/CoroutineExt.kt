package com.xiaoya.kotlinapp.utils

import kotlinx.coroutines.*


/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/

fun launchUI(start: CoroutineStart = CoroutineStart.DEFAULT,
             parent: Job? = null,
             block: suspend  CoroutineScope.() -> Unit)
= GlobalScope.launch(parent,start,block)