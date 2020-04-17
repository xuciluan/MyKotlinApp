package com.xiaoya.kotlinapp.view.common

import com.xiaoya.kotlinapp.mvp.impls.BasePresenter

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-09
 * @desc:
 **/
class CommonListPresenter<DataType,out View : CommonListFragment<DataType,
        CommonListPresenter<DataType,View>>>:BasePresenter<View>()
}