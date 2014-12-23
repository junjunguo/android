package com.junjunguo.sunshine;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by GuoJunjun on 22.12.14.
 */
public class FetchWeatherTask extends AsyncTask<ObjectHolder, Void, ArrayList<String>> {
    //AsyncTask<Params, Progress, Result> execute (Params... params)
    private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();
    private ArrayAdapter<String> mForecastAdapter;

    @Override
    protected ArrayList<String> doInBackground(ObjectHolder... params) {
        if (params.length == 0) {
            return null;
        }
        mForecastAdapter = (ArrayAdapter) params[0].getSecond();
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;
        String format = "json";
        String units = "metric";
        int numDays = 7;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            //                URL url = new URL("http://api.openweathermap.org/data/2
            // .5/forecast/daily?q=" + "Trondheim" + "&mode=json&units=metric&cnt=7");
            final String forecastBaseUrl = "http://api.openweathermap.org/data/2.5/forecast/daily?";
            final String queryParam = "q";
            final String formatParam = "mode";
            final String unitsParam = "units";
            final String daysParam = "cnt";
            Uri builtUri = Uri.parse(forecastBaseUrl).buildUpon()
                    .appendQueryParameter(queryParam, (String) params[0].getFirst())
                    .appendQueryParameter(formatParam, format).appendQueryParameter(unitsParam, units)
                    .appendQueryParameter(daysParam, Integer.toString(numDays)).build();
            // Create the request to OpenWeatherMap, and open the connection
            //                urlConnection = (HttpURLConnection) url.openConnection();
            //                urlConnection.setRequestMethod("GET");
            //                urlConnection.connect();
            URL url = new URL(builtUri.toString());
            Log.v(LOG_TAG, "Built URI " + builtUri.toString());
            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                //                forecastJsonStr = null;
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                //                forecastJsonStr = null;
                return null;
            }
            forecastJsonStr = buffer.toString();
            Log.v(LOG_TAG, "Forecast JSON String: " + forecastJsonStr);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data,
            // there's no point in attemping
            // to parse it.
            forecastJsonStr = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return WeatherDataParser.getWeekForecast(forecastJsonStr);
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        if (result != null) {
            mForecastAdapter.clear();
        }
        for (String dayForecastStr : result) {
            mForecastAdapter.add(dayForecastStr);
        }
    }

}