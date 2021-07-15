package com.bmdb.business;

public class CreditOld {

	private int id, actorId, movieId;
	private String role;

	public CreditOld() {
		// default constructor
	}

	public CreditOld(int id, int actorId, int movieId, String role) {
		super();
		this.id = id;
		this.actorId = actorId;
		this.movieId = movieId;
		this.role = role;
	}
	
	public CreditOld(int actorId, int movieId, String role) {
		super();
		this.actorId = actorId;
		this.movieId = movieId;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Credit [id=" + id + ", actorId=" + actorId + ", movieId=" + movieId + ", role=" + role + "]";
	}
	
	

}
