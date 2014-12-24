package com.junjunguo.sunshine;

/**
 * Created by GuoJunjun on 20.12.14.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view. 1<p/> a fragment is a modular container with activity
 * <p/>
 * fragment_main: res/layout/fragment_main.xml
 */
public class ForecastFragment extends Fragment {
    private final String LOG_TAG = ForecastFragment.class.getSimpleName();
    private ArrayAdapter<String> mForecastAdapter;

    // Will contain the raw JSON response as a string.
    //    private String forecastJsonStr = null;
    private ArrayList<String> weekForecast;

    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            updateWeather();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onStart() {
        super.onStart();
        updateWeather();
    }

    private void updateWeather() {
        FetchWeatherTask weatherTask = new FetchWeatherTask();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String location =
                prefs.getString(getString(R.string.pref_location_key), getString(R.string.pref_location_default));


        weatherTask.execute(new ObjectHolder(location, mForecastAdapter));
        try {
            weekForecast = weatherTask.get();
            System.out.println(weatherTask);
        } catch (InterruptedException e) {
            Log.e(LOG_TAG, "Interrupted weather.get", e);
        } catch (ExecutionException e) {
            Log.e(LOG_TAG, "Execution weather.get", e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //        Log.i("forecastJsonStr ", forecastJsonStr);

        //                new ArrayList(WeatherDataParser.getWeekForecast(forecastJsonStr));
            /*
             *  ArrayAdapter<String> :
             *  Parameters:
             *      Context:
             *              contained global information about app environment*
             *      ID of list item layout
             *              *
             *      ID of text view
             *      list of data
             */
        //        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
        mForecastAdapter = new ArrayAdapter<String>(
                // the current context
                getActivity(),
                // id of list item layout
                R.layout.list_item_forecast,
                // id of the textview to populate
                R.id.list_item_forecast_textview,
                // data
                weekForecast);

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        // get a reference to the ListView, and attach this adapter to listview
        ListView listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String forecast = mForecastAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT, forecast);
                startActivity(intent);
                //                Toast.makeText(getActivity(), forecast, Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    /**
     * Prepare the weather high/lows for presentation.
     */
    private String formatHighLows(double high, double low) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String unitType =
                sharedPrefs.getString(getString(R.string.pref_units_key), getString(R.string.pref_units_metric));

        if (unitType.equals(getString(R.string.pref_units_imperial))){
            high = (high*1.8   )+32;
            low = (low*1.8)+32;
        }else if(unitType.equals(getString(R.string.pref_units_metric))){
            Log.d(LOG_TAG, "Unit type not found: "+ unitType);
        }
        // For presentation, assume the user doesn't care about tenths of a degree.
        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        String highLowStr = roundedHigh + "/" + roundedLow;
        return highLowStr;
    }


    //    public class FetchWeatherTask extends AsyncTask<String, Void, ArrayList<String>> {
    //        private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();
    //
    //        @Override
    //        protected ArrayList<String> doInBackground(String... params) {
    //            if (params.length == 0) {
    //                return null;
    //            }
    //            // These two need to be declared outside the try/catch
    //            // so that they can be closed in the finally block.
    //            HttpURLConnection urlConnection = null;
    //            BufferedReader reader = null;
    //
    //            // Will contain the raw JSON response as a string.
    //            String forecastJsonStr = null;
    //            String format = "json";
    //            String units = "metric";
    //            int numDays = 7;
    //
    //            try {
    //                // Construct the URL for the OpenWeatherMap query
    //                // Possible parameters are avaiable at OWM's forecast API page, at
    //                // http://openweathermap.org/API#forecast
    //                //                URL url = new URL("http://api.openweathermap.org/data/2
    //                // .5/forecast/daily?q=" + "Trondheim" + "&mode=json&units=metric&cnt=7");
    //                final String forecastBaseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    //                final String queryParam = "q";
    //                final String formatParam = "mode";
    //                final String unitsParam = "units";
    //                final String daysParam = "cnt";
    //                Uri builtUri = Uri.parse(forecastBaseUrl).buildUpon().appendQueryParameter(queryParam, params[0])
    //                        .appendQueryParameter(formatParam, format).appendQueryParameter(unitsParam, units)
    //                        .appendQueryParameter(daysParam, Integer.toString(numDays)).build();
    //                // Create the request to OpenWeatherMap, and open the connection
    //                //                urlConnection = (HttpURLConnection) url.openConnection();
    //                //                urlConnection.setRequestMethod("GET");
    //                //                urlConnection.connect();
    //                URL url = new URL(builtUri.toString());
    //                Log.v(LOG_TAG, "Built URI " + builtUri.toString());
    //                // Read the input stream into a String
    //                InputStream inputStream = urlConnection.getInputStream();
    //                StringBuffer buffer = new StringBuffer();
    //                if (inputStream == null) {
    //                    // Nothing to do.
    //                    //                forecastJsonStr = null;
    //                    return null;
    //                }
    //                reader = new BufferedReader(new InputStreamReader(inputStream));
    //
    //                String line;
    //                while ((line = reader.readLine()) != null) {
    //                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
    //                    // But it does make debugging a *lot* easier if you print out the completed
    //                    // buffer for debugging.
    //                    buffer.append(line + "\n");
    //                }
    //
    //                if (buffer.length() == 0) {
    //                    // Stream was empty.  No point in parsing.
    //                    //                forecastJsonStr = null;
    //                    return null;
    //                }
    //                forecastJsonStr = buffer.toString();
    //                Log.v(LOG_TAG, "Forecast JSON String: " + forecastJsonStr);
    //            } catch (IOException e) {
    //                Log.e(LOG_TAG, "Error ", e);
    //                // If the code didn't successfully get the weather data,
    //                // there's no point in attemping
    //                // to parse it.
    //                forecastJsonStr = null;
    //            } finally {
    //                if (urlConnection != null) {
    //                    urlConnection.disconnect();
    //                }
    //                if (reader != null) {
    //                    try {
    //                        reader.close();
    //                    } catch (final IOException e) {
    //                        Log.e(LOG_TAG, "Error closing stream", e);
    //                    }
    //                }
    //            }
    //            return WeatherDataParser.getWeekForecast(forecastJsonStr);
    //        }
    //
    //        @Override
    //        protected void onPostExecute(ArrayList<String> result) {
    //            if (result != null) {
    //                mForecastAdapter.clear();
    //            }
    //            for (String dayForecastStr : result) {
    //                mForecastAdapter.add(dayForecastStr);
    //            }
    //        }
    //    }
}