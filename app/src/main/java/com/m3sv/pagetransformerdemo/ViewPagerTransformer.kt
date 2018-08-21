package com.m3sv.pagetransformerdemo

import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View


class ViewPagerTransformer(viewDelegate: ViewDelegate) : ViewPager.PageTransformer,
    ViewDelegate by viewDelegate {
    override fun transformPage(view: View, position: Float) {
        Log.d(javaClass.simpleName, "Page position: $position")
        onScroll(position, view.tag as? Int)
    }
}

