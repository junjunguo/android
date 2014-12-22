import java.util.ArrayList;

/**
 * Created by GuoJunjun on 22.12.14.
 */
public class TestWeatherApp {
    public static void main(String[] args) {
        String ws = OpenWeather.getWeather("Trondheim");
        ArrayList<String > al = WeatherParser.getWeekForecast(ws);

        for (String s : al) {
            System.out.println(s);
        }
    }
}

