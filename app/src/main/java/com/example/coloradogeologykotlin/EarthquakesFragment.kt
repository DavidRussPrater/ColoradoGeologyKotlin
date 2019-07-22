package com.example.coloradogeologykotlin


import android.app.Fragment
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.Locale


/**
 * A simple [Fragment] subclass.
 */
class EarthquakesFragment : Fragment() {

    /** Adapter for the list of earthquakes  */
    private var mAdapter: EarthquakeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.earthquake_list, container, false)


        // Find a reference to the {@link ListView} in the layout
        val earthquakeListView = rootView.findViewById<View>(R.id.earthquake_list) as ListView

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = EarthquakeAdapter(activity!!, ArrayList<Earthquake>())

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.adapter = mAdapter

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        earthquakeListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, l ->
            // Find the current earthquake that was clicked on
            val currentEarthquake = mAdapter!!.getItem(position)

            // Convert the String URL into a URI object (to pass into the Intent constructor)
            val earthquakeUri = Uri.parse(currentEarthquake!!.url)

            // Create a new intent to view the earthquake URI
            val websiteIntent = Intent(Intent.ACTION_VIEW, earthquakeUri)

            // Send the intent to launch a new activity
            startActivity(websiteIntent)
        }

        // Start the AsyncTask to fetch the earthquake data
        val task = EarthquakeAsyncTask()
        task.execute(USGS_REQUEST_URL)

        return rootView

    }

    /**
     * [AsyncTask] to perform the network request on a background thread, and then
     * update the UI with the list of earthquakes in the response.
     *
     * AsyncTask has three generic parameters: the input type, a type used for progress updates, and
     * an output type. Our task will take a String URL, and return an Earthquake. We won't do
     * progress updates, so the second generic is just Void.
     *
     * We'll only override two of the methods of AsyncTask: doInBackground() and onPostExecute().
     * The doInBackground() method runs on a background thread, so it can run long-running code
     * (like network activity), without interfering with the responsiveness of the app.
     * Then onPostExecute() is passed the result of doInBackground() method, but runs on the
     * UI thread, so it can use the produced data to update the UI.
     */
    private inner class EarthquakeAsyncTask : AsyncTask<String, Void, List<Earthquake>>() {
        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * [Earthquake]s as the result.
         */

        // public AsyncResponse delegate = null;


        override fun doInBackground(vararg urls: String): List<Earthquake>? {
            // Don't perform the request if there are no URLs, or the first URL is null
            return if (urls.size < 1 || urls[0] == null) {
                null
            } else QueryUtils.fetchEarthquakeData(urls[0])
        }

        /**
         * This method runs on the main UI thread after the background work has been
         * completed. This method receives as input, the return value from the doInBackground()
         * method. First we clear out the adapter, to get rid of earthquake data from a previous
         * query to USGS. Then we update the adapter with the new list of earthquakes,
         * which will trigger the ListView to re-populate its list items.
         */
        override fun onPostExecute(data: List<Earthquake>?) {
            // Clear the adapter of previous earthquake data
            mAdapter!!.clear()
            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter!!.addAll(data)
            }


            //delegate.finish();
        }
    }

    companion object {

        private val LOG_TAG = EarthquakesFragment::class.java.name

        //Get today's date
        private val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // Get current year as string
        val yearString = currentDate.substring(0, 4)

        //Convert the current year to an integer
        val yearInt = Integer.parseInt(yearString)

        //Convert current the current year to the year last decade
        val decadeInt = yearInt - 10

        //Convert the current year integer back to a string
        val decadeString = Integer.toString(decadeInt)

        //Replace the current year string with the year from a decade ago
        val finalDecadeDate = currentDate.replace(yearString, decadeString)

        /** URL for earthquake data from the USGS dataset  */
        private val USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=$finalDecadeDate&endtime=$currentDate&minmagnitude=3.5&minlatitude=37&maxlatitude=41&minlongitude=-109&maxlongitude=-102"
    }
}// Required empty public constructor

