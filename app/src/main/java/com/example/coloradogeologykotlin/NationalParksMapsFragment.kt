package com.example.coloradogeologykotlin

import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class NationalParksMapsFragment : SupportMapFragment(), OnMapReadyCallback {

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
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Define the Long and Lat of Colorado and move the camera here
        val colorado = LatLng(39.5501, -105.7821)
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(colorado))


        // Define the latitude and longitude of each National Park in Colorado
        val rockyMountainNP = LatLng(40.3428, -105.6836)
        val greatSandDunesNP = LatLng(37.7916, -105.5943)
        val blackCanyonNP = LatLng(38.5754, -107.7416)
        val mesaVerdeNP = LatLng(37.2309, -108.4618)
        val dinosaurRidgeFossils = LatLng(39.6942, -105.2000)
        val canonCityFossils = LatLng(38.4494, -105.2253)

        // Add a marker with the name of each national park to the map of Colorado
        mMap!!.addMarker(MarkerOptions().position(rockyMountainNP).title(getString(R.string.rocky_mountain_park)))
        mMap!!.addMarker(MarkerOptions().position(greatSandDunesNP).title(getString(R.string.sand_dunes_park)))
        mMap!!.addMarker(MarkerOptions().position(blackCanyonNP).title(getString(R.string.black_canyon_park)))
        mMap!!.addMarker(MarkerOptions().position(mesaVerdeNP).title(getString(R.string.mesa_verde_park)))
        mMap!!.addMarker(MarkerOptions().position(dinosaurRidgeFossils).title(getString(R.string.dinosaur_ridge)))
        mMap!!.addMarker(MarkerOptions().position(canonCityFossils).title(getString(R.string.cañon_city)))

        // Make the camera zoom in to show the full state of Colorado
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(colorado, 6f), 2000, null)

    }
}// Required empty public constructor
