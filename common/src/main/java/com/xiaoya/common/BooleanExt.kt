package com.xiaoya.common

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-06
 * @desc:
 **/
//他们说类 C 是在参数 T 上是协变的，
// 或者说 T 是一个协变的类型参数。
// 你可以认为 C 是 T 的生产者，而不是 T 的消费者。
sealed class BooleanExt<out T>

class WithData<T>(val data: T) : BooleanExt<T>()
object Otherwise : BooleanExt<Nothing>()

inline fun <T> Boolean.yes(block: () -> T) =
        when {
            this -> {
                WithData(block())
            }
            else -> {
                Otherwise
            }
        }

inline fun <T> Boolean.no(block: () -> T) = when {
    this -> Otherwise
    else -> {
        WithData(block())
    }
}

inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T =
        when (this) {
            is Otherwise -> block()
            is WithData -> this.data
        }




