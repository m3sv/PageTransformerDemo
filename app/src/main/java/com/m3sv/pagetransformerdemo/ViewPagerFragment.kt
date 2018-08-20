package com.m3sv.pagetransformerdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.lang.ref.WeakReference


class ViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_pager_fragment, container, false)
    }

    private val pageScrollDelegate = ViewPagerTransformer(object : ViewDelegate {
        private val listeners: MutableMap<Int, WeakReference<ViewElement>> = mutableMapOf()

        override fun onScroll(position: Float, tag: Int?) {
            tag?.let {
                listeners[it]?.get()?.onScroll(position)
            }
        }

        override fun addListener(tag: Int, viewElement: ViewElement) {
            listeners[tag] = WeakReference(viewElement)
        }
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ViewPager>(R.id.view_pager).also {
            it.offscreenPageLimit = NUM_PAGES - 1
            it.adapter = PagerAdapter(fragmentManager)
            it.setPageTransformer(true, pageScrollDelegate)
        }
    }

    inner class PagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getItem(num: Int): Fragment {
            val fragment = ViewPagerPageFragment.newInstance(num)
            pageScrollDelegate.addListener(num, fragment)
            return fragment
        }

        override fun getCount(): Int = NUM_PAGES
    }

    companion object {
        const val NUM_PAGES = 5
    }
}