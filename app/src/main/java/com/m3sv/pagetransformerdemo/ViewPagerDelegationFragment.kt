package com.m3sv.pagetransformerdemo

import android.support.v4.app.Fragment
import java.lang.ref.WeakReference


abstract class ViewPagerDelegationFragment: Fragment(), ViewDelegate {
    private val listeners: MutableMap<Int, WeakReference<ViewElement>> = mutableMapOf()

    override fun onScroll(position: Float, tag: Int?) {
        tag?.let {
            listeners[it]?.get()?.onScroll(position)
        }
    }

    override fun addListener(tag: Int?, viewElement: ViewElement) {
        tag?.let {
            listeners[it] = WeakReference(viewElement)
        }
    }
}