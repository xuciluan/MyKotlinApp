package com.xiaoya.kotlinapp.view.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xiaoya.kotlinapp.view.config.FragmentPage

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/
class CommonViewPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    val fragmentPages = ViewPagerAdapterList<FragmentPage>(this)

    override fun getCount(): Int {
        return fragmentPages.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentPages[position].fragment
    }
}