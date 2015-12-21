package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.predictions.Descriptions;
import beans.predictions.Predictions;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/cityName")
public class GetCityName extends HttpServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		ArrayList<String> cityNames = new ArrayList<String>();
		String input = request.getParameter("cityName");
		if(input.split(" ").length != 1)
			return;
		ObjectMapper mapper = new ObjectMapper();
		try 
		{
			URL url = new URL("https://maps.googleapis.com/maps/api/place/autocomplete"
					+ "/json?input="+input+"&key=AIzaSyBi2YaZ0ae8s9eMrDEBpSKyKoHTc3CSxME");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output = "",temp;
			while ((temp = br.readLine()) != null) {
				output = output + "\r\n" + temp;
			}
			Predictions predictions = mapper.readValue(output, Predictions.class);
			for(Descriptions description : predictions.getPredictions())
			{
				cityNames.add(description.getDescription());
				
			}
			conn.disconnect();
		  } 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.println(mapper.writeValueAsString(cityNames));
	}
}