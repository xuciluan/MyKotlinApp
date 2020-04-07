package com.xiaoya.common.shareperferences

import android.content.Context
import java.lang.IllegalArgumentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-06
 * @desc:
 **/
class Preference<T>(val context: Context,val name:String, val default:T,val prefName:String ="default")
    :ReadWriteProperty<Any?,T>{

    private val prefs by lazy {
        context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(findProperName(property))
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(findProperName(property),value)
    }

    private fun findPreference(key:String): T{
        return when(default){
            is Long -> prefs.getLong(key,default)
            is Int -> prefs.getInt(key,default)
            is Boolean -> prefs.getBoolean(key,default)
            is String -> prefs.getString(key,default)
            else -> throw IllegalArgumentException("不支持")
        } as T
    }

    private fun findProperName(property: KProperty<*>):String{
        return if (name.isEmpty()) property.name else name
    }

    private fun putPreference(key:String,value:T){
        with(prefs.edit()){
            when(value){
                is Long -> putLong(key,value)
                is Int -> putInt(key,value)
                is Boolean -> putBoolean(key,value)
                is String -> putString(key,value)
                else -> throw IllegalArgumentException("不支持")
            }
        }.apply()
    }

}
