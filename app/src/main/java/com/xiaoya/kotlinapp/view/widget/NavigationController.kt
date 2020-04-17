package com.xiaoya.kotlinapp.view.widget


import android.view.MenuItem
import com.google.android.material.navigation.NavigationView
import com.xiaoya.common.log.logger
import com.xiaoya.kotlinapp.R
import com.xiaoya.kotlinapp.github.model.account.AccountManager
import com.xiaoya.kotlinapp.github.settings.Settings
import com.xiaoya.kotlinapp.network.entities.User
import com.xiaoya.kotlinapp.utils.doOnLayoutAvailable
import com.xiaoya.kotlinapp.utils.loadWithGlide
import com.xiaoya.kotlinapp.utils.selectItem
import com.xiaoya.kotlinapp.view.config.NavViewItem
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.sdk15.listeners.onClick

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-08
 * @desc:
 **/

class NavigationController(
        private val navigationView:NavigationView,
        private val onNavItemChanged: (NavViewItem) -> Unit,
        private val onHeaderClick : () -> Unit
) : NavigationView.OnNavigationItemSelectedListener{

    init {
        navigationView.setNavigationItemSelectedListener (this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            navigationView.apply {
               Settings.lastPage = item.itemId
               val navItem = NavViewItem[item.itemId]
                onNavigationItemSelected(item)
            }
        return true
    }

    fun useLoginLayout() {
        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.activity_main_drawer)
        onUpdate(AccountManager.currentUser)
        selectProperItem()
    }

    private val currentItem : NavViewItem?= null
     fun selectProperItem() {
        logger.debug("select proper item")
        navigationView.doOnLayoutAvailable {
            logger.debug("select proper item onLayout : $currentItem")
            ((currentItem?.let {
                NavViewItem[it]
            } ?: Settings.lastPage)
                    .takeIf {
                        navigationView.menu.findItem(it) != null
                    }
                    ?: kotlin.run {
                        Settings.defaultPage
                    })
                    .let (navigationView::selectItem)
        }
    }

    private fun onUpdate(user : User?) {

        navigationView.doOnLayoutAvailable {
            navigationView.apply {
                usernameView.text = user?.login ?: "请登录"
                emailView.text = user?.email ?: "benny.com"
                if (user == null){
                    avatarView.imageResource = R.drawable.ic_github
                }else{
                    avatarView.loadWithGlide(user.avatar_url,user.login.first())
                }
                navigationHeader.onClick {
                    onHeaderClick()
                }
            }
        }
    }

    fun useNoLoginLayout() {
        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.activity_main_drawer_no_logged_in)
        onUpdate(AccountManager.currentUser)
        selectProperItem()
    }


}