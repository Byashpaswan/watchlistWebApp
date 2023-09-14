package com.Byas.watchList.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Byas.watchList.entity.Movie;

@Repository
public interface MoviRepo extends JpaRepository<Movie, Integer> {
	

}
