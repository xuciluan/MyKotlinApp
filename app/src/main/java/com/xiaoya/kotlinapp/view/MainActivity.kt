package com.xiaoya.kotlinapp.view

import androidx.appcompat.app.AppCompatActivity
import com.xiaoya.kotlinapp.github.model.account.OnAccountStateChangeListener
import com.xiaoya.kotlinapp.network.entities.User

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-07
 * @desc:
 **/
class MainActivity : AppCompatActivity(),OnAccountStateChangeListener{


    override fun onLogin(user: User) {

    }

    override fun onLogout() {
    }


}