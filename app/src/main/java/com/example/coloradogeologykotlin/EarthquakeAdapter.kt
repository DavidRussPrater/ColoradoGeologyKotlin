package com.example.coloradogeologykotlin

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date


/**
 * An [EarthquakeAdapter] knows how to create a list item layout for each earthquake
 * in the data source (a list of [Earthquake] objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
class EarthquakeAdapter
/**
 * Constructs a new [EarthquakeAdapter].
 *
 * @param context of the app
 * @param earthquakes is the list of earthquakes, which is the data source of the adapter
 */
    (context: Context, earthquakes: List<Earthquake>) : ArrayAdapter<Earthquake>(context, 0, earthquakes) {

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(
                R.layout.earthquake_list_item, parent, false
            )
        }

        // Find the earthquake at the given position in the list of earthquakes
        val currentEarthquake = getItem(position)

        // Find the TextView with view ID magnitude
        val magnitudeView = listItemView!!.findViewById<View>(R.id.magnitude) as TextView
        // Format the magnitude to show 1 decimal place
        val formattedMagnitude = formatMagnitude(currentEarthquake!!.magnitude)
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.text = formattedMagnitude


        // Get the original location string from the Earthquake object,
        // which can be in the format of "5km N of Denver, Colorado" or "Pacific-Antarctic Ridge".
        val originalLocation = currentEarthquake!!.location

        // If the original location string (i.e. "5km N of Denver, Colorado") contains
        // a primary location (Denver, Colorado) and a location offset (5km N of that city)
        // then store the primary location separately from the location offset in 2 Strings,
        // so they can be displayed in 2 TextViews.
        val primaryLocation: String
        val locationOffset: String

        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            // Split the string into different parts (as an array of Strings)
            // based on the " of " text. We expect an array of 2 Strings, where
            // the first String will be "5km N" and the second String will be "City, Colorado".
            val parts =
                originalLocation.split(LOCATION_SEPARATOR.toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            // Location offset should be "5km N " + " of " --> "5km N of"
            locationOffset = parts[0] + LOCATION_SEPARATOR
            // Primary location should be "Denver, Colorado"
            primaryLocation = parts[1]
        } else if (originalLocation == STATE_ONLY) {
            // If the epicenter is unknown change dont use "near the"
            locationOffset = context.getString(R.string.epicenter_unknown)
            primaryLocation = context.getString(R.string.greater_colorado_area)

        } else {
            Log.i("originalLocation", originalLocation)
            // Otherwise, there is no " of " text in the originalLocation string.
            // Hence, set the default location offset to say "Near the".
            locationOffset = context.getString(R.string.near_the)
            // The primary location will be the full location string "Pacific-Antarctic Ridge".
            primaryLocation = originalLocation


            Log.i("primaryLocation", primaryLocation)
        }

        // Find the TextView with view ID location
        val primaryLocationView = listItemView.findViewById<View>(R.id.primary_location) as TextView
        // Display the location of the current earthquake in that TextView
        primaryLocationView.text = primaryLocation

        // Find the TextView with view ID location offset
        val locationOffsetView = listItemView.findViewById<View>(R.id.location_offset) as TextView
        // Display the location offset of the current earthquake in that TextView
        locationOffsetView.text = locationOffset

        // Create a new Date object from the time in milliseconds of the earthquake
        val dateObject = Date(currentEarthquake.timeInMilliseconds)

        // Find the TextView with view ID date
        val dateView = listItemView.findViewById<View>(R.id.date) as TextView
        // Format the date string (i.e. "Mar 3, 1984")
        val formattedDate = formatDate(dateObject)
        // Display the date of the current earthquake in that TextView
        dateView.text = formattedDate

        // Find the TextView with view ID time
        val timeView = listItemView.findViewById<View>(R.id.time) as TextView
        // Format the time string (i.e. "4:30PM")
        val formattedTime = formatTime(dateObject)
        // Display the time of the current earthquake in that TextView
        timeView.text = formattedTime

        // Set the proper background color on the magnitude Square.
        // Fetch the background from the TextView, which is a GradientDrawable.
        val magnitudeSquare = magnitudeView.background as GradientDrawable

        // Get the appropriate background color based on the current earthquake magnitude
        val magnitudeColor = getMagnitudeColor(currentEarthquake.magnitude)

        // Set the color on the magnitude Square
        magnitudeSquare.setColor(magnitudeColor)

        // Return the list item view that is now showing the appropriate data
        return listItemView
    }


    private fun getMagnitudeColor(magnitude: Double): Int {
        val magnitudeColorResourceId: Int
        val magnitudeFloor = Math.floor(magnitude).toInt()
        when (magnitudeFloor) {
            0, 1 -> magnitudeColorResourceId = R.color.magnitude_1
            2 -> magnitudeColorResourceId = R.color.magnitude_2
            3 -> magnitudeColorResourceId = R.color.magnitude_3
            4 -> magnitudeColorResourceId = R.color.magnitude_4
            5 -> magnitudeColorResourceId = R.color.magnitude_5
            6 -> magnitudeColorResourceId = R.color.magnitude_6
            7 -> magnitudeColorResourceId = R.color.magnitude_7
            8 -> magnitudeColorResourceId = R.color.magnitude_8
            9 -> magnitudeColorResourceId = R.color.magnitude_9
            else -> magnitudeColorResourceId = R.color.magnitude_10
        }
        return ContextCompat.getColor(context, magnitudeColorResourceId)
    }


    private fun formatDate(dateObject: Date): String {
        val dateFormat = SimpleDateFormat("LLL dd, yyyy")
        return dateFormat.format(dateObject)
    }

    private fun formatTime(dateObject: Date): String {
        val timeFormat = SimpleDateFormat("h:mm a")
        return timeFormat.format(dateObject)
    }

    private fun formatMagnitude(magnitude: Double): String {
        val magnitudeFormat = DecimalFormat("0.0")
        return magnitudeFormat.format(magnitude)
    }

    companion object {

        /**
         * The part of the location string from the USGS service that we use to determine
         * whether or not there is a location offset present ("5km N of Cairo, Egypt").
         */
        private val LOCATION_SEPARATOR = " of "
        private val STATE_ONLY = "Colorado"
    }

}
