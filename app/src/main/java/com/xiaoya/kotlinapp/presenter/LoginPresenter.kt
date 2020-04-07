package com.xiaoya.kotlinapp.presenter


import android.accounts.Account
import com.xiaoya.kotlinapp.BuildConfig
import com.xiaoya.kotlinapp.github.model.account.AccountManager
import com.xiaoya.kotlinapp.mvp.impls.BasePresenter
import com.xiaoya.kotlinapp.view.LoginActivity

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-06
 * @desc:
 **/
class LoginPresenter : BasePresenter<LoginActivity>(){

    fun doLogin(name:String,passwd:String){
        AccountManager.userName = name
        AccountManager.passwd = passwd
        view.onLoginStart()
        AccountManager.login()
                .subscribe({
                    view.onLoginSuccess()
                },{
                    view.onLoginError(it)
                })
    }

    fun checkUserName(name:String):Boolean{
        return true
    }

    fun checkPasswd(passwd:String):Boolean{
        return true
    }

    override fun onResume() {
        super.onResume()
        //TODO 自动填充数据
    }

}