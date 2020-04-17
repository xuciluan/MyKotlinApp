package com.xiaoya.kotlinapp.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.view.menu.MenuItemImpl
import androidx.core.view.ViewCompat
import androidx.core.view.doOnLayout
import com.google.android.material.navigation.NavigationView
import com.xiaoya.common.log.logger
import com.xiaoya.common.otherwise
import com.xiaoya.common.yes
import com.xiaoya.kotlinapp.view.config.NavViewItem

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/
fun NavigationView.selectItem(@IdRes resId:Int){
    doOnLayout {
        logger.debug("select Item:${NavViewItem[resId].title}")
        setCheckedItem(resId)
        //TODO  这句话有什么作用？？？
        (menu.findItem(resId) as MenuItemImpl)
    }
}


inline fun NavigationView.doOnLayoutAvailable(crossinline block: () -> Unit){
    ViewCompat.isLaidOut(this)
            .yes{
                block()
            }
            .otherwise {
                addOnLayoutChangeListener(object: View.OnLayoutChangeListener{
                    override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
                        removeOnLayoutChangeListener(this)
                        block()
                    }
                })
            }
}