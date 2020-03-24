package com.xiaoya.kotlinapp.mvp.impls

import android.os.Bundle
import com.xiaoya.kotlinapp.mvp.IMvpView
import com.xiaoya.kotlinapp.mvp.IPresenter

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-24
 * @desc:
 **/
class BasePresenter<out V:IMvpView<BasePresenter<V>>> :IPresenter<V>{

    override lateinit var view: @UnsafeVariance V

    override fun onCreate(saveInstance: Bundle?) = Unit

}