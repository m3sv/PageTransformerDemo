package com.m3sv.pagetransformerdemo

import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View


class ViewPagerTransformer(private val viewDelegate: ViewDelegate) : ViewPager.PageTransformer {
    override fun transformPage(view: View, position: Float) {

        Log.d(javaClass.simpleName, "Page position: $position")
        viewDelegate.onScroll(position, view.tag as? Int)
    }

    fun addListener(tag: Int, viewElement: ViewElement) {
        viewDelegate.addListener(tag, viewElement)
    }
}

interface ViewDelegate {
    fun onScroll(position: Float, tag: Int?)

    fun addListener(tag: Int, viewElement: ViewElement)
}

interface ViewElement {
    fun onScroll(position: Float)
}