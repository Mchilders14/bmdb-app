package com.bmdb.business;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Credit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ActorID")
	private Actor actor;
	
	@ManyToOne
	@JoinColumn(name="MovieID")	// Joining Column on Credit(Entity).
	private Movie movie;
	private String role;
	
	public Credit() {
		super();
	}

	public Credit(int id, Actor actor, Movie movie, String role) {
		super();
		this.id = id;
		this.actor = actor;
		this.movie = movie;
		this.role = role;
	}
	
	public Credit(Actor actor, Movie movie, String role) {
		super();
		this.actor = actor;
		this.movie = movie;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Credit [id=" + id + ", actor=" + actor + ", movie=" + movie + ", role=" + role + "]";
	}
	
}