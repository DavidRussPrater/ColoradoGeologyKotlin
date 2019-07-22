package com.example.coloradogeologykotlin

import android.os.Bundle
import com.example.coloradogeologykotlin.QueryUtils.locationArray
import com.example.coloradogeologykotlin.QueryUtils.latitudeArray
import com.example.coloradogeologykotlin.QueryUtils.longitudeArray
import com.example.coloradogeologykotlin.QueryUtils.magnitudeArray
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class EarthquakeMapsFragment : SupportMapFragment(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_maps_fragment);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.*/getMapAsync(this)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        // Define the Long and Lat of Colorado and move the camera here
        val colorado = LatLng(39.5501, -105.7821)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(colorado))


        // Make the camera zoom in to show the full state of Colorado
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(colorado, 6f), 2000, null)

        finish()

    }


    fun finish() {

        /* Put the thread to sleep momentarily to give the earthquake data time to load then add
         * markers for the the earthquakes to the map
         *
         * Other wise the app crashes for null pointer exception in QueryUtils because it is attempting
         * to  split the coordinates data before it has loaded.
         *
         * This is not best practice but its a temporary fix
         */

        try {
            Thread.sleep(600)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // This for loop displays the fifteen most recent earthquakes in Colorado
        for (i in 0..14) {

            // Get the string value for the earthquakes longitude at index i and convert it from a String
            // to a double to display on the map
            val finalLongitudeString =  longitudeArray[i]
            val longitudeDouble = java.lang.Double.parseDouble(finalLongitudeString)

            // Get the string value for the earthquakes latitude at index i and convert it from a String
            // to a double to display on the map
            val finalLatitudeString = latitudeArray[i]
            val latitudeDouble = java.lang.Double.parseDouble(finalLatitudeString)

            // Add the corresponding Lat and Long values to the and set the title of the pin to
            // "Location : Magnitude" and add them to the map
            val earthquake = LatLng(latitudeDouble, longitudeDouble)
            mMap!!.addMarker(MarkerOptions().position(earthquake).title(locationArray[i] + " Magnitude: " + magnitudeArray[i]))

        }

    }
}// Required empty public constructor
