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
        forecast += "City: \t\t" + weatherObject.getCity() + "<br>";
        forecast += "Description: \t" + weatherObject.getDescription() + "<br>";
        forecast += "Temperature: \t" + weatherObject.getTemp() + "<br>";
        forecast += "Pressure: \t" + weatherObject.getPressure() + "<br>";
        forecast += "Humidity: \t" + weatherObject.getHumidity() + "<br>";
        forecast += "Wind power: \t" + weatherObject.getWind() + "<br>";
        forecast += "Sunrise: \t" + weatherObject.getSunrise() + "<br>";
        forecast += "Sunset: \t" + weatherObject.getSunset() + "<br>";

        return forecast;

    }

    public String getWeatherFormatted() {

        return weatherFormatted;

    }

}
