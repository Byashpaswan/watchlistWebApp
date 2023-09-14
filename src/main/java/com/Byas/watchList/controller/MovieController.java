package com.Byas.watchList.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.Byas.watchList.entity.Movie;
import com.Byas.watchList.service.DatabaseService;

import jakarta.validation.Valid;

@RestController
public class MovieController {
	
	@Autowired
	DatabaseService databaseService;
	
	@GetMapping("/watchlistItemForm")
	public ModelAndView showWatchListForm(@RequestParam(required=false)Integer id)
	{  
		System.out.println(id);
		String viewName="watchlistItemForm";
		
		Map<String,Object>model=new HashMap<>();
		if(id==null)
		  {
			model.put("watchlistItem", new Movie());
		 }
		
		else
		{
			model.put("watchlistItem",databaseService.getMovieById(id));
		}
//		Movie dummyMovi=new Movie();
//		dummyMovi.setTitle("dummy");
//		dummyMovi.setRating(0);
//		dummyMovi.setPriority("Medium");
//		dummyMovi.setComment("Jhon doe");
		
		
		//model.put("watchlistItem", dummyMovi);
		
		
		return new ModelAndView(viewName,model);
	}
	
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchListForm(@Valid @ModelAttribute("watchlistItem")  Movie movie , BindingResult bindingResult)
	{  
		if(bindingResult.hasErrors())
		{
			// if errors are there , redisplay the form and let user enter again
			 return new ModelAndView("watchlistItemForm");
		}
		
		
		Integer id=movie.getId();
		
		if(id==null)
		  {
		 	databaseService.create(movie);
		   }
		else
		{
			databaseService.update(movie,id);
		}
		
		RedirectView rd=new RedirectView();
		
		rd.setUrl("/watchlist");
		return new ModelAndView(rd);
	}
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchList()
	{
		String viewName="watchlist";
		Map<String,Object>model=new HashMap<>();
		
		List<Movie>movilist= databaseService.getAllMovies();
		model.put("watchlistrow",movilist);
		model.put("noofmovies",movilist.size());
		
		return new ModelAndView(viewName,model);
	}

}
