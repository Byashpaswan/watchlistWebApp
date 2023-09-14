package com.Byas.watchList.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Byas.watchList.Repo.MoviRepo;
import com.Byas.watchList.entity.Movie;
@Service
public class DatabaseService {
	@Autowired
	MoviRepo moviRepo;
	
	@Autowired
	RatingService ratingService;
	public void create(Movie movie)
	{  
		String rating= ratingService.getMovieRating(movie.getTitle());
		
		if(rating!=null)
		{
			movie.setRating(Float.parseFloat(rating));
		}
		moviRepo.save(movie);
	}

	public List<Movie>getAllMovies()
	{
		return moviRepo.findAll();
		
	}
	
	public Movie getMovieById(Integer id)
	{
		return moviRepo.findById(id).get();
	}

	public void update(Movie movie, Integer id) {
		// TODO Auto-generated method stub
		Movie toBeupdated=getMovieById(id);
		 toBeupdated.setTitle(movie.getTitle());
		 toBeupdated.setRating(movie.getRating());
		 toBeupdated.setPriority(movie.getPriority());
		 toBeupdated.setComment(movie.getComment());
		 
		 moviRepo.save(toBeupdated);
	}
}
