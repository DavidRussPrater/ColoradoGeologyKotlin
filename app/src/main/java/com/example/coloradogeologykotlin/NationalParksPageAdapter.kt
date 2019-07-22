package com.example.coloradogeologykotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class NationalParksPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return NationalParksFragment()
            1 -> return Fragment()
        }
        return null
    }

    override fun getCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "National Parks"
            1 -> return "Map of Parks"
        }
        return null
    }

    companion object {

        private val FRAGMENT_COUNT = 2
    }
}
