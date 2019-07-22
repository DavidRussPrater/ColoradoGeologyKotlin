package com.example.coloradogeologykotlin

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class EarthquakePageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return NationalParksFragment()
            1 -> return NationalParksMapsFragment()
        }
        return null
    }

    override fun getCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Earthquake List"
            1 -> return "Earthquake Map"
        }
        return null
    }

    companion object {
        //private static final String TAG = CustomFragmentPageAdapter.class.getSimpleName();
        private val FRAGMENT_COUNT = 2
    }
}

