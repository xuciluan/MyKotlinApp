package com.xiaoya.kotlinapp.view.config

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

import com.xiaoya.kotlinapp.R
import com.xiaoya.kotlinapp.view.fragments.AboutFragment
import com.xiaoya.kotlinapp.view.fragments.MyIssueFragment
import com.xiaoya.kotlinapp.view.fragments.PeopleFragment
import com.xiaoya.kotlinapp.view.fragments.RepoFragment

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/
class NavViewItem private constructor(val groupId:Int = 0, val title:String,
                                         @DrawableRes val icon:Int,
                                         val fragmentClass:Class<out Fragment>,
                                         val arguments: Bundle = Bundle()){
    companion object {
        private val items = mapOf(
                R.id.navRepos to NavViewItem(0,"Repository", R.drawable.ic_repository, RepoFragment::class.java,Bundle().apply {
                    putParcelable("USER",null)
                }),
                R.id.navPeople to NavViewItem(0, "People", R.drawable.ic_people, PeopleFragment::class.java),
                R.id.navIssue to NavViewItem(0, "Issue", R.drawable.ic_issue, MyIssueFragment::class.java),
                R.id.navAbout to NavViewItem(0, "About", R.drawable.ic_about_us, AboutFragment::class.java)
        )

        operator fun get(@IdRes id:Int):NavViewItem{
            return items[id]?: items[R.id.navRepos]!!
        }

        operator fun get(item:NavViewItem):Int{
            return items.filter {
                it.value == item
            }.keys.first()
        }
    }


}