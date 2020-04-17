package com.xiaoya.kotlinapp.view.widget


import android.database.DataSetObserver
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.xiaoya.kotlinapp.view.MainActivity
import kotlinx.android.synthetic.main.app_bar_main.*

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/
class ActionBarController(val mainActivity: MainActivity){

    //region tablayout
    private val tabLayout by lazy{
        mainActivity.tabLayout
    }

    class ViewPagerDataSetObserver(val tabLayout : TabLayout): DataSetObserver() {

        var viewPager : ViewPager?=  null
        set(value) {
            viewPager?.adapter?.unregisterDataSetObserver(this)
            value?.adapter?.registerDataSetObserver(this)
            field = value
        }

        override fun onChanged() {
            super.onChanged()
            viewPager?.let { viewPager ->
                if (viewPager.adapter?.count ?: 0 <= 1) {
                    tabLayout.visibility = View.GONE
                } else {
                    tabLayout.visibility = View.VISIBLE
                    tabLayout.tabMode =
                            if(viewPager.adapter?.count ?: 0 > 3)
                                TabLayout.MODE_SCROLLABLE
                            else TabLayout.MODE_FIXED
                }
            }
        }
    }

    private val datasetObserver by lazy {
        ViewPagerDataSetObserver(tabLayout)
    }

    fun setupWithViewPager(viewPager: ViewPager?){
        viewPager?.let {
            datasetObserver.viewPager = viewPager
        }?:kotlin.run {
            tabLayout.visibility = View.GONE
        }
        tabLayout.setupWithViewPager(viewPager)
    }
}