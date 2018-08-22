package com.m3sv.pagetransformerdemo

import android.support.v4.app.Fragment


abstract class ViewPagerDelegationFragment : Fragment(), ViewDelegate {

    private val listeners: MutableMap<Int, ViewElement?> = mutableMapOf()

    override fun onScroll(position: Float, tag: Int?) {
        tag?.let { listeners[it]?.onScroll(position) }
    }

    override fun addListener(tag: Int?, viewElement: ViewElement) {
        tag?.let { listeners[it] = viewElement }
    }

    override fun removeListener(tag: Int?) {
        tag?.let { listeners[it] = null }
    }
}