package com.m3sv.pagetransformerdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        savedInstanceState?.let {  } ?: supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, ViewPagerFragment())
            .commit()
    }
}
