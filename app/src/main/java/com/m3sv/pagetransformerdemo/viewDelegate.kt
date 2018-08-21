package com.m3sv.pagetransformerdemo


interface ViewDelegate {
    fun onScroll(position: Float, tag: Int?)

    fun addListener(tag: Int?, viewElement: ViewElement)
}

interface ViewElement {
    fun onScroll(position: Float)
}