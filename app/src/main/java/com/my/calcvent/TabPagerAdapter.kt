package com.my.calcvent

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter (fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter (fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FragmentTabItem1()
            1 -> return FragmentTabItem2()
            else -> return FragmentTabItem3()
            }
    }

    override fun getCount(): Int {
        return tabCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Tab1"
            1 -> "Tab3"
            else -> {
                return "Tab3"
            }
        }
    }
}