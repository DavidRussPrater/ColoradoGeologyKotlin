package com.example.coloradogeologykotlin


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment

import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class NationalParksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.tour_list, container, false)

        // Create a list of to store a picture of of the national park plus information about the region
        val tours = ArrayList<Tour>()
        tours.add(Tour(R.string.rocky_mountain_park, R.string.rocky_mountain_summary, R.drawable.rocky_mountain_image))
        tours.add(Tour(R.string.sand_dunes_park, R.string.sand_dunes_summary, R.drawable.sand_dunes_image))
        tours.add(Tour(R.string.black_canyon_park, R.string.black_canyon_summary, R.drawable.black_canyon_image))
        tours.add(Tour(R.string.mesa_verde_park, R.string.mesa_verde_summary, R.drawable.mesa_verde_image))

        // Create an {@link TourAdapter}, whose data source is a list of {@link Tours}s. The
        // adapter knows how to create list items for each item in the list.
        val adapter = TourAdapter(context!!, tours)

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // tour_list.xml layout file.
        val listView = rootView.findViewById<View>(R.id.list) as ListView


        // Make the {@link ListView} use the {@link TourAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Tour} in the list
        listView.adapter = adapter

        return rootView

    }
}// Required empty public constructor


