package com.bmdb.web;

import com.bmdb.business.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.User;
import com.bmdb.db.UserRepo;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping("/")
	public Iterable<User> getAll() {
		return userRepo.findAll();
	}

	@GetMapping("/{id}")
	public Optional<User> get(@PathVariable int id) {return userRepo.findById(id);}

	@PostMapping("/")
	public User add(@RequestBody User user) {
		return userRepo.save(user);
	}

	@PutMapping("/")
	public User update(@RequestBody User user) {
		return userRepo.save(user);
	}

	@DeleteMapping("/{id}")
	public Optional<User> delete(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			try {
				userRepo.deleteById(id);
			}
			catch (DataIntegrityViolationException dive) {
				// catch dive when user exists as fk on another table
				System.err.println(dive.getRootCause().getMessage());
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Foreign Key Constraint Issue - user id: "+id+ " is "
								+ "referred to elsewhere.");
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"Exception caught during user delete.");
			}
		}
		else {
			System.err.println("User delete error - no user found for id:"+id);
		}
		return user;
	}
}
