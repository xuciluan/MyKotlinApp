package com.xiaoya.kotlinapp.view.common

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-09
 * @desc:
 **/

abstract class CommonListAdapter<T>(@LayoutRes val itemResId:Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    companion object {
        private const val CARD_TAP_DURATION = 100L
    }

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private var oldPosition = -1

}