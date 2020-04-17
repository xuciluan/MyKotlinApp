package com.xiaoya.kotlinapp.view.config

import androidx.fragment.app.Fragment

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/
interface ViewPagerFragmentConfig{
    fun getFragmentPagesLoggedIn():List<FragmentPage>
    fun getFragmentPagesNotLoggedIn():List<FragmentPage>
}

data class FragmentPage(val fragment:Fragment, val title:String)