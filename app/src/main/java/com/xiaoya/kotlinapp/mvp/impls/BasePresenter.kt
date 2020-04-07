package com.xiaoya.kotlinapp.mvp.impls

import android.content.res.Configuration
import android.os.Bundle
import com.xiaoya.kotlinapp.mvp.IMvpView
import com.xiaoya.kotlinapp.mvp.IPresenter

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-24
 * @desc:
 **/
abstract class BasePresenter<out V:IMvpView<BasePresenter<V>>> :IPresenter<V>{

    override lateinit var view: @UnsafeVariance V

    override fun onCreate(savedInstanceState: Bundle?) {}
    override fun onSaveInstanceState(outState: Bundle) {}
    override fun onViewStateRestored(savedInstanceState: Bundle?) {}
    override fun onConfigurationChanged(newConfig: Configuration) {}
    override fun onDestroy() {}
    override fun onStart() {}
    override fun onStop() {}
    override fun onResume() {}
    override fun onPause() {}

}