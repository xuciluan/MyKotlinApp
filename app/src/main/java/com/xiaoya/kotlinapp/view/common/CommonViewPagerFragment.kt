package com.xiaoya.kotlinapp.view.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.xiaoya.kotlinapp.R
import com.xiaoya.kotlinapp.github.model.account.AccountManager
import com.xiaoya.kotlinapp.github.model.account.OnAccountStateChangeListener
import com.xiaoya.kotlinapp.network.entities.User
import com.xiaoya.kotlinapp.view.MainActivity
import com.xiaoya.kotlinapp.view.config.ViewPagerFragmentConfig
import kotlinx.android.synthetic.main.fragment_common_viewpager.*
import org.jetbrains.anko.appcompat.v7.actionBarContainer

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/
abstract class CommonViewPagerFragment :Fragment(), ViewPagerFragmentConfig,OnAccountStateChangeListener{

    private val viewPagerAdapter by lazy{
        CommonViewPageAdapter(childFragmentManager)
    }

    private lateinit var viewpager : ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val view = inflater.inflate(R.layout.fragment_common_viewpager,container)
       view_pager.adapter = viewPagerAdapter
       return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).actionBarController.setupWithViewPager(viewpager)
        viewPagerAdapter.fragmentPages.addAll(
                if (AccountManager.isloggedIn()){
                    getFragmentPagesLoggedIn()
                }else{
                    getFragmentPagesNotLoggedIn()
                }
        )
    }

    override fun onLogin(user: User) {
        viewPagerAdapter.fragmentPages.clear()
        viewPagerAdapter.fragmentPages.addAll(getFragmentPagesLoggedIn())
    }

    override fun onLogout() {
        viewPagerAdapter.fragmentPages.clear()
        viewPagerAdapter.fragmentPages.addAll(getFragmentPagesNotLoggedIn())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AccountManager.onAccountStateChangeListener.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AccountManager.onAccountStateChangeListener.remove(this)
    }

}