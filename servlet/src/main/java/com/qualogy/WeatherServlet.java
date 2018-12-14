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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<pre>");
		out.println("Weather forecast Dutch cities.");
		out.println("Example: http://localhost:8080/servlet/weather?q=Rotterdam\n");
		out.println( weatherService.getWeatherFormatted() );
		out.println("</pre>");
		out.flush();
		out.close();

	}

}