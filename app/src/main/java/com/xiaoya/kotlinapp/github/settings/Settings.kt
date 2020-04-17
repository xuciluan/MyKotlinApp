package com.xiaoya.kotlinapp.github.settings

import com.xiaoya.kotlinapp.AppContext
import com.xiaoya.kotlinapp.R
import com.xiaoya.kotlinapp.github.model.account.AccountManager
import com.xiaoya.kotlinapp.utils.pref
import org.jetbrains.anko.appcompat.v7.Appcompat

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/
object Settings{
    private var defaultPageForUser by pref(R.id.navRepos)
    private var defaultPageForVisitor  by pref(R.id.navRepos)
    private var lastPageIdString by pref("")
    var thememoDE by pref("DAY")

    var lastPage : Int
    get() = if(lastPageIdString.isEmpty()) 0 else AppContext.resources.getIdentifier(lastPageIdString,"id", AppContext.packageName)
    set(value) {
        lastPageIdString = AppContext.resources.getResourceEntryName(value)
    }

    val defaultPage
     get() = if (AccountManager.isloggedIn()) defaultPageForUser else defaultPageForVisitor
}