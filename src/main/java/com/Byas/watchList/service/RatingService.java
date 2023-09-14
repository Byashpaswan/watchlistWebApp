package com.Byas.watchList.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {
	
	
	String apiUrl="http://www.omdbapi.com/?i=tt3896198&apikey=fcd8acf5";
	
	public String getMovieRating(String title)
	{
		try {
			//to call the external Api  we use RestTemplate
			RestTemplate template =new RestTemplate();
			  // apiUrl+title
			// RestTemplate gives the json Object as response known as ResponseEntity
			ResponseEntity<ObjectNode>response=template.getForEntity(apiUrl+title,ObjectNode.class);
			
			// we can get whole json body from ResponseEntity using getBody
			ObjectNode jsonObject=response.getBody();
			// after getBody of Json we  fetch the specific imdbRating as a path
			return jsonObject.path("imdbRating").asText();
		}
		catch(Exception e)
		{
			 System.out.println("Enter movie name not available or api is down"+e.getMessage());
			 return null;
		}
	}
}
