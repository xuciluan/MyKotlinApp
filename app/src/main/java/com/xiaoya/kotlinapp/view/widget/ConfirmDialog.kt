package com.xiaoya.kotlinapp.view.widget

import android.content.Context
import org.jetbrains.anko.alert
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/
suspend fun Context.confirm(title:String,message:String = "") =
        suspendCoroutine<Boolean> {
            continuation ->
            alert {
                this.title = title
                this.message = message

                negativeButton("titlle"){
                    continuation.resume(false)
                }
                negativeButton("OK"){
                    continuation.resume(true)
                }
                onCancelled {
                    continuation.resume(false)
                }
            }.show()
        }