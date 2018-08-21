package com.m3sv.pagetransformerdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class ViewPagerFragment : ViewPagerDelegationFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ViewPager>(R.id.view_pager).also {
            it.offscreenPageLimit = NUM_PAGES - 1
            it.adapter = PagerAdapter(childFragmentManager)
            it.setPageTransformer(true, ViewPagerTransformer(this))
        }
    }

    inner class PagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getItem(num: Int): Fragment {
            return ViewPagerPageFragment.newInstance(num)
        }

        override fun getCount(): Int = NUM_PAGES
    }

    companion object {
        const val NUM_PAGES = 5
    }
}