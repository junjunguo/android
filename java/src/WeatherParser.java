import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by GuoJunjun on 22.12.14.
 */
public class WeatherParser {

    /**
     * parser a weather Json string to an arrayList
     *
     * @param weatherJsonStr
     *
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
}
