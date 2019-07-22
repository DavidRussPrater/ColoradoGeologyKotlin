package com.example.coloradogeologykotlin


/**
 * [Tour] represents an attraction that the user can visit in olorado.
 * It contains resource IDs for the attractions names, description and image
 */
class Tour
/**
 * Create a new Tour object
 *
 * @param activityNameId is the string resource ID activites name
 * @param activitySummaryId is the string resource ID for the activities summary
 * @param imageResourceId is the image resource ID for an image of the activity
 */
    (// String resource ID for the activities name
    // Get the string resource ID for the activities name
    val activityNameId: Int, // String resource ID for the activities summary
    // Get the string resource ID for the activities summary
    val activitySummaryId: Int, // Image resource ID for the activities picture
    // Get the image resource ID for the activities picture
    val imageResourceId: Int
)
