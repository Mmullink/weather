package com.qualogy;

import org.json.JSONObject;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class WeatherObject {
	private String description;
	private String temp;
	private String pressure;
	private String humidity;
	private String wind;
	private String sunrise;
	private String sunset;
	private String city;

	public WeatherObject(String jsonContent) {

		JSONObject obj = new JSONObject(jsonContent);
		this.description = obj.getJSONArray("weather").getJSONObject(0).getString("description");
		double temp = Math.round( obj.getJSONObject("main").getDouble("temp") * 10 ) / 10;
		this.temp = "" + (temp - 273);
		this.pressure = obj.getJSONObject("main").getInt("pressure") + "mbar";
		this.humidity = obj.getJSONObject("main").getInt("humidity") + "%";
		this.wind = obj.getJSONObject("wind").getDouble("speed") + " Bf";

		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		long sunrise = obj.getJSONObject("sys").getInt("sunrise");
		this.sunrise = Instant.ofEpochSecond(sunrise).atZone(ZoneId.of("GMT+1")).format(formatter);
		long sunset = obj.getJSONObject("sys").getInt("sunset");
		this.sunset = Instant.ofEpochSecond(sunset).atZone(ZoneId.of("GMT+1")).format(formatter);

		this.city = obj.getString("name");

	}

	public String getDescription() { return description; }

	public String getTemp() { return temp; }

	public String getPressure() { return pressure; }

	public String getHumidity() { return humidity; }

	public String getWind() { return wind; }

	public String getSunrise() { return sunrise; }

	public String getSunset() { return sunset; }

	public String getCity() { return city; }

}

/*
{
"coord":{"lon":4.46,"lat":51.92},
"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04n"}],
"base":"stations",
"main":{"temp":275.34,"pressure":1024,"humidity":86,"temp_min":272.15,"temp_max":278.15},
"visibility":10000,
"wind":{"speed":4.6,"deg":90},
"clouds":{"all":90},
"dt":1544649900,
"sys":{"type":1,"id":1541,"message":0.0104,"country":"NL","sunrise":1544600488,"sunset":1544628656},
"id":2747891,
"name":"Rotterdam",
"cod":200
}
*/