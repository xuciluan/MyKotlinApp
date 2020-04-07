package com.xiaoya.common.log

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-30
 * @desc:
 **/

val loggerMap = HashMap<Class<*>, Logger>()

inline val<reified  T> T.logger:Logger
    get() {
        return loggerMap[T::class.java]?:
        LoggerFactory.getLogger(T::class.java)
                .apply{
                    loggerMap[T::class.java] = this
                }
    }
