package com.example.coloradogeologykotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class EarthquakeTabsFragment : Fragment() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    /**
     * Set up a ViewPager and tab layout to handle the Earthquakes tabs
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.earthquake_tabs, container, false)
        tabLayout = view.findViewById<View>(R.id.tabs) as TabLayout
        viewPager = view.findViewById<View>(R.id.view_pager) as ViewPager
        viewPager!!.adapter = EarthquakePageAdapter(childFragmentManager)
        tabLayout!!.setupWithViewPager(viewPager)
        return view
    }


}// Required empty public constructor
