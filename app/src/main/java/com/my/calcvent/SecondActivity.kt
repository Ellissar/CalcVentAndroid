package com.my.calcvent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        configureTabLayout() //функция настройки вкладок
    }


    //функция настройки вкладок
    private fun configureTabLayout() {
/*        tabLayout.addTab(tabLayout.newTab().setText("1"))
        tabLayout.addTab(tabLayout.newTab().setText("2"))
        tabLayout.addTab(tabLayout.newTab().setText("3"))*/

        val adapter = TabPagerAdapter (supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener( TabLayout.TabLayoutOnPageChangeListener (tabLayout))

        tabLayout.addOnTabSelectedListener( object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }
            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })

    }

}