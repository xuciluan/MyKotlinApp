package com.xiaoya.kotlinapp.mvp



/**
 * @author : xuciluan@126.com
 * @date: 2020-03-24
 * @desc:
 **/

interface IPresenter<out View:IMvpView<IPresenter<View>>> : ILifeCycle{

     val view:View
}

interface IMvpView<out Presenter:IPresenter<IMvpView<Presenter>>> : ILifeCycle{
     val presenter:Presenter
}
