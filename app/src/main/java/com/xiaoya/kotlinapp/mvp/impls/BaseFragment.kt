package com.xiaoya.kotlinapp.mvp.impls

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.xiaoya.kotlinapp.mvp.IMvpView
import com.xiaoya.kotlinapp.mvp.IPresenter
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

/**
 * @author : xuciluan@126.com
 * @date: 2020-03-24
 * @desc:
 **/

abstract class BaseFragment<out P:BasePresenter<BaseFragment<P>>>(contentLayoutId: Int) : IMvpView<P>, Fragment(contentLayoutId) {

    override val presenter : P

    init {
        presenter = createPresenterkt()
        presenter.view = this
    }

    //todo : 什么是星投影？这段代码什么意思？？？
    fun createPresenterkt():P {
        sequence{
            var thisClass: KClass<*> = this@BaseFragment::class
            while(true){
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure ?: break;
            }
        }.flatMap{
            it.flatMap{
                it.arguments
            }.asSequence()
        }.first{
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class)?:false
        }.let{
            return it.type!!.jvmErasure.primaryConstructor!!.call() as P
        }
    }

    fun createPresenterJava():P{
        sequence<Type>{
            var thisClass:Class<*> = this@BaseFragment.javaClass
            while(true){
                yield(thisClass.genericSuperclass)
                thisClass = thisClass.superclass?:break
            }
        }.filter {
            it is ParameterizedType
        }.flatMap {
            (it as ParameterizedType).actualTypeArguments.asSequence()
        }.first{
            it is Class<*> && IPresenter::class.java.isAssignableFrom(it)
        }.let {
            return (it as Class<P>).newInstance()
        }
    }

    //TODO 重写生命周期方法
}