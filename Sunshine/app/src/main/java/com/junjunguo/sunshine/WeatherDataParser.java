package com.junjunguo.sunshine;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by GuoJunjun on 22.12.14.
 */
public class WeatherDataParser {

    /**
     * Given a string of the form returned by the api call: http://api.openweathermap.org/data/2 *
     * .5/forecast/daily?q=94043&mode=json&units=metric&cnt=7 retrieve the maximum temperature for the day indicated by
     * dayIndex (Note: 0-indexed, so 0 would refer to the first day).
     */
    public static double getMaxTemperatureForDay(String weatherJsonStr, int dayIndex) throws JSONException {
        JSONObject weather = new JSONObject(weatherJsonStr);
        JSONArray ja = weather.getJSONArray("list");
        JSONObject jo = (JSONObject) ja.get(dayIndex);
        JSONObject temp = jo.getJSONObject("temp");
        return temp.getDouble("max");
    }

    /* The date/time conversion code is going to be moved outside the asynctask later,
 * so for convenience we're breaking it out into its own method now.
 */
    private String getReadableDateString(long time) {
        // Because the API returns a unix timestamp (measured in seconds),
        // it must be converted to milliseconds in order to be converted to valid date.
        Date date = new Date(time * 1000);
        SimpleDateFormat format = new SimpleDateFormat("E, MMM d");
        return format.format(date).toString();
    }

    /**
     * Prepare the weather high/lows for presentation.
     */
    private String formatHighLows(double high, double low) {
        // For presentation, assume the user doesn't care about tenths of a degree.
        long roundedHigh = Math.round(high);
        long roundedLow = Math.round(low);

        String highLowStr = roundedHigh + "/" + roundedLow;
        return highLowStr;
    }

    /**
     * Take the String representing the complete forecast in JSON Format and pull out the data we need to construct the
     * Strings needed for the wireframes.
     * <p/>
     * Fortunately parsing is easy:  constructor takes the JSON string and converts it into an Object hierarchy for us.
     */
    private String[] getWeatherDataFromJson(String forecastJsonStr, int numDays) throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String OWM_LIST = "list";
        final String OWM_WEATHER = "weather";
        final String OWM_TEMPERATURE = "temp";
        final String OWM_MAX = "max";
        final String OWM_MIN = "min";
        final String OWM_DATETIME = "dt";
        final String OWM_DESCRIPTION = "main";

        JSONObject forecastJson = new JSONObject(forecastJsonStr);
        JSONArray weatherArray = forecastJson.getJSONArray(OWM_LIST);

        String[] resultStrs = new String[numDays];
        for (int i = 0; i < weatherArray.length(); i++) {
            // For now, using the format "Day, description, hi/low"
            String day;
            String description;
            String highAndLow;

            // Get the JSON object representing the day
            JSONObject dayForecast = weatherArray.getJSONObject(i);

            // The date/time is returned as a long.  We need to convert that
            // into something human-readable, since most people won't read "1400356800" as
            // "this saturday".
            long dateTime = dayForecast.getLong(OWM_DATETIME);
            day = getReadableDateString(dateTime);

            // description is in a child array called "weather", which is 1 element long.
            JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            description = weatherObject.getString(OWM_DESCRIPTION);

            // Temperatures are in a child object called "temp".  Try not to name variables
            // "temp" when working with temperature.  It confuses everybody.
            JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);
            double high = temperatureObject.getDouble(OWM_MAX);
            double low = temperatureObject.getDouble(OWM_MIN);

            highAndLow = formatHighLows(high, low);
            resultStrs[i] = day + " - " + description + " - " + highAndLow;
        }

        return resultStrs;
    }

    /**
     * 
     * @param weatherJsonStr
     * @return
     */
    public static ArrayList<String> getWeekForecast(String weatherJsonStr) {
        ArrayList warray = new ArrayList();
        try {
            JSONObject weather = new JSONObject(weatherJsonStr);
            JSONArray jarray = weather.getJSONArray("list");
            for (int i = 0; i < jarray.length(); i++) {
                String ws = "";
                JSONObject li = (JSONObject) jarray.get(i);
                JSONObject temp = li.getJSONObject("temp");
                ws += "Temp: Day " + temp.get("day") + "; Night: " + temp.get("eve") +
                        "; " +
                        "Morning: " + temp.get("morn") + "; Min: " +
                        "" + temp.get("min") + "; max: " + temp.get("max") + "\n";
                ws += "Pressure: " + li.get("pressure").toString() + "; humidity: " + li.get("humidity").toString() +
                        "\n";
                JSONObject w = li.getJSONArray("weather").getJSONObject(0);
                ws += "Weather: " + w.get("main") + "; description: " + w.get("description");
                warray.add(i, ws);
            }
        } catch (JSONException e) {
            System.out.println("catch Json: " + e);
        } finally {
            return warray;
        }
    }

    public static String [] getWeekForecastL(String weatherJsonStr) {
        String [] warray = null;
        try {
            JSONObject weather = new JSONObject(weatherJsonStr);
            JSONArray jarray = weather.getJSONArray("list");
            warray = new String[jarray.length()];
            for (int i = 0; i < jarray.length(); i++) {
                String ws = "";
                JSONObject li = (JSONObject) jarray.get(i);
                JSONObject temp = li.getJSONObject("temp");
                ws += "Temp: Day " + temp.get("day") + "; Night: " + temp.get("eve") +
                        "; " +
                        "Morning: " + temp.get("morn") + "; Min: " +
                        "" + temp.get("min") + "; max: " + temp.get("max") + "\n";
                ws += "Pressure: " + li.get("pressure").toString() + "; humidity: " + li.get("humidity").toString() +
                        "\n";
                JSONObject w = li.getJSONArray("weather").getJSONObject(0);
                ws += "Weather: " + w.get("main") + "; description: " + w.get("description");
                warray[i]= ws;
            }
        } catch (JSONException e) {
            System.out.println("catch Json: " + e);
        } finally {
            return warray;
        }
    }
}