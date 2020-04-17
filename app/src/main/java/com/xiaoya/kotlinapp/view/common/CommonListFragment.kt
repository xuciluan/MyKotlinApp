package com.xiaoya.kotlinapp.view.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter
import com.xiaoya.kotlinapp.R
import com.xiaoya.kotlinapp.mvp.impls.BaseFragment
import com.xiaoya.kotlinapp.view.widget.ErrorInfoView
import kotlinx.android.synthetic.main.list.*

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-09
 * @desc:
 **/
abstract class CommonListFragment<DataType, out Presenter:
CommonListPresenter<DataType, CommonListFragment<DataType,Presenter>>>: BaseFragment<Presenter>() {

    protected abstract val adapter : CommonListAdapter<DataType>

    protected val errorInfoView by lazy {
        ErrorInfoView(rootView)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.adapter = LuRecyclerViewAdapter(adapter)
    }




}