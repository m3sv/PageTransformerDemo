package com.m3sv.pagetransformerdemo


interface ViewDelegate {
    fun onScroll(position: Float, tag: Int?)

    fun addListener(tag: Int?, viewElement: ViewElement)

    fun removeListener(tag: Int?)
}

interface ViewElement {
    fun onScroll(position: Float)
}