package com.qualogy;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class WeatherService {

    private String weatherFormatted;

    public WeatherService(String city) {

        String weatherJson = receiveWeather(city);

        WeatherObject weatherObject = new WeatherObject(weatherJson);

        weatherFormatted = formatWeather(weatherObject);

    }

    public String receiveWeather(String city) {

        String encodedCity = "", weatherJson = "", line;
        try {
            encodedCity = URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException ignored) {
        }

        URL url;
        URLConnection conn = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + encodedCity + ",nl&APPID=ba8ea36af4b42adef8b514d89141dd1b");
            conn = url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            while ((line = br.readLine()) != null)
                weatherJson += line;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherJson;

    }

    public String formatWeather(WeatherObject weatherObject) {

        String forecast = "";
        forecast += "Stad: \t\t" + weatherObject.getCity() + "\n";
        forecast += "Beschrijving: \t" + weatherObject.getDescription() + "\n";
        forecast += "Temperatuur: \t" + weatherObject.getTemp() + "\n";
        forecast += "Druk: \t\t" + weatherObject.getPressure() + "\n";
        forecast += "Vochtigheid: \t" + weatherObject.getHumidity() + "\n";
        forecast += "Windkracht: \t" + weatherObject.getWind() + "\n";
        forecast += "Zonsopgang: \t" + weatherObject.getSunrise() + "\n";
        forecast += "Zonsondergang: \t" + weatherObject.getSunset() + "\n";

        return forecast;

    }

    public String getWeatherFormatted() {

        return weatherFormatted;

    }

}
