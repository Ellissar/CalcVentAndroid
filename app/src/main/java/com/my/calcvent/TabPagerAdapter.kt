package com.my.calcvent

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter (fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FragmentTabItem1()
            1 -> return FragmentTabItem2()
            else -> return FragmentTabItem2()
        }
    }

    //возвращает количество вкладок
    override fun getCount(): Int {
        return tabCount
    }

    //устанавливает название вкладок
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.tabItem1Name)
            1 -> context.getString(R.string.tabItem2Name)
            else -> {
                return context.getString(R.string.tabItem2Name)
            }
        }
    }

    companion object {
        //передача контекста приложения в класс
        //для использования в MainActivity в функции onCreate необходимо прописать TabPagerAdapter.Companion.setContext(this)
        private lateinit var context: Context
        fun setContext(con: Context) {
            context = con
        }
    }
}