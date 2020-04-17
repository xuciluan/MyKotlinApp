package com.xiaoya.kotlinapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.xiaoya.common.no
import com.xiaoya.common.otherwise
import com.xiaoya.common.yes
import com.xiaoya.kotlinapp.AppContext
import com.xiaoya.kotlinapp.R
import com.xiaoya.kotlinapp.github.model.account.AccountManager
import com.xiaoya.kotlinapp.github.model.account.OnAccountStateChangeListener
import com.xiaoya.kotlinapp.network.entities.User
import com.xiaoya.kotlinapp.utils.launchUI
import com.xiaoya.kotlinapp.view.config.NavViewItem
import com.xiaoya.kotlinapp.view.widget.ActionBarController
import com.xiaoya.kotlinapp.view.widget.NavigationController
import com.xiaoya.kotlinapp.view.widget.confirm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.toast

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-07
 * @desc:
 **/
class MainActivity : AppCompatActivity(),OnAccountStateChangeListener{


    override fun onLogin(user: User) {
        navigationController.useLoginLayout()
    }

    override fun onLogout() {
        navigationController.useNoLoginLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        AccountManager.onAccountStateChangeListener.remove(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toogle = ActionBarDrawerToggle(this,drawer_layout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.setDrawerListener(toogle)
        toogle.syncState()

        initNavigationView()

        AccountManager.onAccountStateChangeListener.add(this)
    }


    val actionBarController by lazy{
        ActionBarController(this)
    }

    private val navigationController by lazy{
        NavigationController(navigationView,::onNavigationItemChanged,::handleNavigationHeaderClickEvent)
    }

    private fun onNavigationItemChanged(navViewItem: NavViewItem){

    }

    private fun handleNavigationHeaderClickEvent(){
        AccountManager.isloggedIn()
                .no {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
                .otherwise {
                    launchUI {
                        if (confirm("提示","确认注销那？")){
                            AccountManager
                                    .logout()
                                    .subscribe({
                                        toast("注销成功")
                                    },{
                                        it.printStackTrace()
                                    })
                        }
                    }
                }
    }

    private fun initNavigationView() {
        AccountManager.isloggedIn()
                .yes {
                    navigationController.useLoginLayout()
                }
                .otherwise {
                    navigationController.useNoLoginLayout()
                }
        navigationController.selectProperItem()
    }


}