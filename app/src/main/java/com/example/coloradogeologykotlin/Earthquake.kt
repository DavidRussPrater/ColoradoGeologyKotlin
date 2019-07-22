package com.example.coloradogeologykotlin


class Earthquake
/**
 * Constructs a new [Earthquake] object.
 *
 * @param magnitude          is the magnitude (size) of the earthquake
 * @param location           is the location where the earthquake happened
 * @param timeInMilliseconds is the time in milliseconds (from the Epoch) when the
 * earthquake happened
 * @param url                is the website URL to find more details about the earthquake
 */
    (
    /**
     * Magnitude of the earthquake
     */
    /**
     * Returns the magnitude of the earthquake.
     */
    val magnitude: Double,
    /**
     * Location of the earthquake
     */
    /**
     * Returns the location of the earthquake.
     */
    val location: String,
    /**
     * Time of the earthquake
     */
    /**
     * Returns the time of the earthquake.
     */
    val timeInMilliseconds: Long,
    /**
     * Website URL of the earthquake
     */
    val url: String,
    /**
     * Longitude of the earthquake
     */
    //private String mLongitude;

    /**
     * Latitude of the earthquake
     */
    //private String mLatitude;

    val coordinates: String
)