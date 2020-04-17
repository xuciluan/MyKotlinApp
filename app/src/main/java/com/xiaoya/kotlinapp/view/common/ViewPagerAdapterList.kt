package com.xiaoya.kotlinapp.view.common

import androidx.viewpager.widget.PagerAdapter

/**
 * @author : xuciluan@126.com
 * @date: 2020-04-09
 * @desc:
 **/
class ViewPagerAdapterList<T>(val adapter:PagerAdapter) : ArrayList<T>(){

    override fun removeAt(index: Int): T {
        return super.removeAt(index).apply { adapter.notifyDataSetChanged() }
    }

    override fun remove(element: T): Boolean {
        val index = remove(element)
        return super.remove(element).apply {
            adapter.notifyDataSetChanged()
        }
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex).apply { adapter.notifyDataSetChanged() }
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        return super.removeAll(elements).apply { adapter.notifyDataSetChanged() }
    }

    override fun add(element: T): Boolean {
        val res = super.add(element)
        adapter.notifyDataSetChanged()
        return res
    }

    override fun add(index: Int, element: T) {
        super.add(index, element).apply {
            adapter.notifyDataSetChanged()
        }
    }
    override fun addAll(elements: Collection<T>): Boolean {
        return super.addAll(elements).apply { adapter.notifyDataSetChanged() }
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        return super.addAll(index, elements).apply { adapter.notifyDataSetChanged() }
    }

    fun update(elements: Collection<T>){
        super.clear()
        super.addAll(elements)
        adapter.notifyDataSetChanged()
    }
}