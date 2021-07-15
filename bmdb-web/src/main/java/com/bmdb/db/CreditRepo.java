package com.bmdb.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bmdb.business.Actor;
import com.bmdb.business.Credit;

public interface CreditRepo extends CrudRepository<Credit, Integer>{ // Using Credit entity
	
	// Using the CrudRepository
	
	List<Credit> findAllByMovieId(int id);	// Using list collection to find all credits by movie.
	// List<Credit> findAllByMovie(Movie movie); <- This works too!

	List<Credit> findAllByActorId(int id);
		
}