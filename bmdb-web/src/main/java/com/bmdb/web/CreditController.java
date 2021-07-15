package com.bmdb.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Actor;
import com.bmdb.business.Credit;
import com.bmdb.business.Movie;
import com.bmdb.db.ActorRepo;
import com.bmdb.db.CreditRepo;
import com.bmdb.db.MovieRepo;

@CrossOrigin
@RestController
@RequestMapping("/api/credits")
public class CreditController {
	
	@Autowired
	private CreditRepo creditRepo;
	
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private ActorRepo actorRepo;
	
	@GetMapping("/")
	public Iterable<Credit> getAll(){
		return creditRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Credit> get(@PathVariable int id) {
		return creditRepo.findById(id);
	}

	@PostMapping("/")
	public Credit add(@RequestBody Credit actor) {
		return creditRepo.save(actor);
	}

	@PutMapping("/")
	public Credit update(@RequestBody Credit actor) {
		return creditRepo.save(actor);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) {
		creditRepo.deleteById(id);
	}
	
	@GetMapping("/movie/{id}")
	public Iterable<Credit> getAllByMovie(@PathVariable int id){
		// Optional<Movie> movie = movieRepo.findById(id);	// Optional type can never be null. protects methods from being null.
		// return creditRepo.findAllByMovie(movie.get()); // Get() function for optional class returns object type.
		return creditRepo.findAllByMovieId(id); // findAll(SELECT *) (creditRepo(FROM Credit)) ByMovie(WHERE MovieId = ) Id(id)
	}
	
	@GetMapping("/actor/{id}")
	public Iterable<Credit> getAllByActor(@PathVariable int id){
		return creditRepo.findAllByActorId(id); // findAll(SELECT *) (creditRepo(FROM Credit)) ByActor(WHERE ActorId = ) Id(id)
	}
	

}
