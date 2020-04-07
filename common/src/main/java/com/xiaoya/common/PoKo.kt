package com.xiaoya.common

import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-30
 * @desc:
 **/

//在Kotlin中的BINARY和Java中的CLASS对应，都代表无法通过反射访问，但是会被保留在class文件中。
//在Kotlin中，默认的保留策略为RUNTIME，这表明我们可以通过反射在程序中获取它。
@Retention(BINARY)
@Target(CLASS)
annotation class PoKo