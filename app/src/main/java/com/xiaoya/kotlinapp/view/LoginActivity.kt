package com.xiaoya.kotlinapp.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.xiaoya.common.otherwise
import com.xiaoya.common.yes
import com.xiaoya.kotlinapp.R
import com.xiaoya.kotlinapp.mvp.impls.BaseActivity
import com.xiaoya.kotlinapp.presenter.LoginPresenter
import com.xiaoya.kotlinapp.utils.hideSoftInput
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.app_bar_simple.*
import org.jetbrains.anko.sdk15.listeners.onClick
import org.jetbrains.anko.toast

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-06
 * @desc:
 **/

class LoginActivity : BaseActivity<LoginPresenter>(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO 这里有一个Themer，不知道作用，暂时不写

        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

        signInButton.onClick{
            presenter.checkUserName(username.text.toString())
                    .yes{
                        presenter.checkPasswd(password.text.toString())
                                .yes{
                                    hideSoftInput()
                                    presenter.doLogin(username.text.toString(), password.text.toString())
                                }.otherwise {
                                    showTips(password, "密码不合法")
                                }
                            }
                    .otherwise {
                        showTips(username, "用户名不合法")
                    }
        }
    }

    private fun showTips(view: EditText, tips:String){
        view.requestFocus()
        view.error = tips
    }

    fun onLoginStart(){
        showProgress(true)
    }

    private fun showProgress(show:Boolean){
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
        loginForm.animate()
                .setDuration(shortAnimTime.toLong())
                .alpha((if(show) 0 else 1).toFloat())
                .setListener(object:AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        loginForm.visibility = if(show) View.GONE else View.VISIBLE
                    }
                })

        loginProgress.animate()
                .setDuration(shortAnimTime.toLong())
                .alpha((if(show) 1 else 0).toFloat())
                .setListener(object:AnimatorListenerAdapter(){
                    override fun onAnimationEnd(animation: Animator?) {
                        loginProgress.visibility = if(show) View.VISIBLE else View.GONE
                    }
                })


    }

    fun onLoginSuccess() {
        toast("登录成功")
        showProgress(false)
        startActivity(Intent(this,MainActivity::class.java))
    }

    fun onLoginError(it: Throwable?) {
        it?.printStackTrace()
        toast("登录失败")
        showProgress(false)
    }
}