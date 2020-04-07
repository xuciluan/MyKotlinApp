package com.xiaoya.kotlinapp.settings

import com.xiaoya.common.log.logger
import com.xiaoya.kotlinapp.AppContext
import com.xiaoya.kotlinapp.utils.deviceId

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-30
 * @desc:
 **/
object Configs{
    object Accout{
        val SCOPES = listOf("user","repo","notifications","gist","admin:org")
        const val clientId = "cccb7d70ba4fe6d4f62d"
        const val clientSecret = "30bea5fc021ed503bef21e23ce8e2b02d588ab6c"
        const val note = "kotliner.cn"
        const val noteUrl = "http://www.kotliner.cn"

        val fingerPrint by lazy {
            (AppContext.deviceId + clientId).also {
                logger.info("fingerPrint: "+it))
            }
        }
    }
}