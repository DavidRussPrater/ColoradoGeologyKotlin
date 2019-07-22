package com.example.coloradogeologykotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList

/**
 * [TourAdapter] is an [ArrayAdapter] that can provide the layout for each list item
 * based on a data source, which is a list of [Tour] objects.
 */
class TourAdapter
/**
 * Create a new [TourAdapter] object.
 *
 * @param context is the current context (i.e. Activity) that the adapter is being created in.
 * @param tours is the list of [Tour]s to be displayed.
 */
    (context: Context, tours: ArrayList<Tour>) : ArrayAdapter<Tour>(context, 0, tours) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Check if an existing view is being reused, otherwise inflate the view
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(
                R.layout.list_item, parent, false
            )

        }

        // Get the {@link Tour} oject located at this position in the list
        val currentTour = getItem(position)

        // Find the textview in the list_item.xml layout with the ID activity_name_text_view
        val activityNameTextView = listItemView!!.findViewById<TextView>(R.id.activity_name_text_view)

        // Get the activities name from the current Tour object and set this text on the activity_name_text_view.
        activityNameTextView.setText(currentTour!!.activityNameId)

        // Find the TextView in the list_item.xml layout with the activity_summary_text_view
        val activitySummaryTextView = listItemView.findViewById<View>(R.id.activity_summary_text_view) as TextView

        // Get the activities summary from the Current Tour object and set this text
        // on the activity_summary_text_view
        activitySummaryTextView.setText(currentTour.activitySummaryId)

        // Find the ImageView in the list_item.xml layout with the activity_image_view
        val activityImageView = listItemView.findViewById<View>(R.id.activity_image_view) as ImageView

        // Ge tthe activity summary from the current Tour object and set the image to the activity_image_view
        activityImageView.setImageResource(currentTour.imageResourceId)

        // Return the whole list item layout (containing 2 TextViews and an ImageView) so that it can be shown in
        // the ListView.
        return listItemView

    }
}
