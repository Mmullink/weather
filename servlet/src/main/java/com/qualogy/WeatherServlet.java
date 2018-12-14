package com.qualogy;

import java.io.*;
import javax.servlet.http.*;

public class WeatherServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String city = request.getParameter("q" );
		if(city == null || city == "")
			city = "Rotterdam";

		WeatherService weatherService = new WeatherService(city);

		try {
			printWeather(response, weatherService);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void printWeather(HttpServletResponse response, WeatherService weatherService) throws IOException {

		PrintWriter out = response.getWriter();
		out.println( weatherService.getWeatherFormatted() );
		out.flush();
		out.close();

	}

}