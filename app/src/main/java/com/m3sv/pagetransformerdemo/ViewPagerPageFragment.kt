package com.m3sv.pagetransformerdemo

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class ViewPagerPageFragment : Fragment(), ViewElement {

    var currentPosition: Int? = null

    lateinit var imageView: ImageView

    lateinit var supportImageView: ImageView

    lateinit var supportImageView2: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentPosition = arguments?.getInt(PAGE_NUM_KEY)
        Log.d(javaClass.simpleName, "onCreateIsCalled:$currentPosition ")
        return when (currentPosition) {
            0 -> inflater.inflate(R.layout.view_pager_page_fragment, container, false)
            1 -> inflater.inflate(R.layout.view_pager_page_fragment_1, container, false)
            2 -> inflater.inflate(R.layout.view_pager_page_fragment_2, container, false)
            3 -> inflater.inflate(R.layout.view_pager_page_fragment_3, container, false)
            4 -> inflater.inflate(R.layout.view_pager_page_fragment_4, container, false)
            else -> throw IllegalArgumentException("Page number is out of range, should be from 0 to 4")
            // Crucial, add the tag, so that you can recognise your view among others
        }.also { it.tag = currentPosition }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (parentFragment as ViewDelegate).addListener(currentPosition, this)
        when (currentPosition) {
            0 -> {
                imageView = view.findViewById(R.id.planet_0)
                supportImageView = view.findViewById(R.id.planet_01)
                supportImageView2 = view.findViewById(R.id.planet_02)
            }
            1 -> {
                imageView = view.findViewById(R.id.planet_1)
                supportImageView = view.findViewById(R.id.planet_11)
                supportImageView2 = view.findViewById(R.id.planet_12)
            }
            2 -> {
                imageView = view.findViewById(R.id.planet_2)
                supportImageView = view.findViewById(R.id.planet_21)
                supportImageView2 = view.findViewById(R.id.planet_22)
                supportImageView.setColorFilter(resources.getColor(R.color.light_goldenrod), PorterDuff.Mode.MULTIPLY)
                supportImageView2.setColorFilter(resources.getColor(R.color.yellow), PorterDuff.Mode.MULTIPLY)
            }
            3 -> {
                imageView = view.findViewById(R.id.planet_3)
                supportImageView = view.findViewById(R.id.planet_31)
                supportImageView2 = view.findViewById(R.id.planet_32)
            }
            4 -> {
                imageView = view.findViewById(R.id.planet_4)
                supportImageView = view.findViewById(R.id.planet_41)
                supportImageView2 = view.findViewById(R.id.planet_42)
            }
        }
    }

    override fun onScroll(position: Float) {
        Log.d(javaClass.simpleName, "onScrollIsCalled, position: $position")
        if (position in 0.0..1.0) {
            imageView.translationX = position * imageView.width * 0.2f
            supportImageView.translationX = position * supportImageView.width * 5f
            supportImageView2.translationX = position * supportImageView.width * 20f
        }

        if (position in -1.0..0.0) {
            imageView.translationX = position * imageView.width * 0.2f
            supportImageView.translationX = position * supportImageView.width * 5f
            supportImageView2.translationX = position * supportImageView.width * 5f
        }
    }

    companion object {
        private const val PAGE_NUM_KEY = "page_number"

        fun newInstance(pageNum: Int): ViewPagerPageFragment {
            val fragment = ViewPagerPageFragment()
            val bundle = Bundle()
            bundle.putInt(PAGE_NUM_KEY, pageNum)
            fragment.arguments = bundle
            return fragment
        }
    }
}