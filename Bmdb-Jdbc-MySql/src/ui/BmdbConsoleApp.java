package ui;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.util.Console;

import business.Actor;
import business.Credit;
import business.Movie;
import db.ActorDb;
import db.CreditDb;
import db.bmdbData;
import interfaces.DAO;

public class BmdbConsoleApp {

	private static final String ITEM_NOT_FOUND = "No Record Found";

	public static void main(String[] args) throws ParseException {
		msg("Welcome to the Big Movie Database!");

		DAO<Movie> movieDAO = new bmdbData();
		DAO<Actor> actorDAO = new ActorDb();
		CreditDb creditDb = new CreditDb();



		int command = 0;
		while (command != 9) {
			displayMenu();
			command = Console.getInt("Command: ");
			System.out.println();

			switch (command) {
			
			case 1:
				System.out.println("Get all Movies");
				System.out.println("=============================");
				
				for (Movie movie : movieDAO.getAll()) {
					System.out.println(movie);
				}
				break;
				
			case 2:
				System.out.println("Get Item by ID");
				System.out.println("=============================");
				
				int id = Console.getInt("ID: ");
				Movie movie = movieDAO.get(id);
				
				if (movie != null) {
					System.out.println("Movie Found: " + movie);
					
				} else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 3:
				System.out.println("Add Movie");
				System.out.println("=============================");
				
				int year = Console.getInt("Year: ");
				String title = Console.getString("Title: ");
				String rating = Console.getString("Rating: ");
				String director = Console.getString("Director: ");

				if (movieDAO.add(new Movie(year, title, rating, director))) {
					System.out.println("Item added!");
					
				} else {
					System.out.println("Error adding item.");
				}
				break;
				
			case 4:
				System.out.println("Update Movie");
				System.out.println("=============================");
				
				id = Console.getInt("ID: ");
				movie = movieDAO.get(id);
				
				if (movie != null) {
					
					int newYear = Console.getInt("Year: ");
					String newTitle = Console.getString("Title: ");
					String newRating = Console.getString("Rating: ");
					String newDirector = Console.getString("Director: ");
					
					movie.setYear(newYear);
					movie.setTitle(newTitle);
					movie.setRating(newRating);
					movie.setDirector(newDirector);
					
					movieDAO.update(movie);
					
					System.out.println("Movie updated: " + movie);
				} else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 5:
				System.out.println("Delete a Movie");
				System.out.println("=============================");
				
				id = Console.getInt("ID: ");
				movie = movieDAO.get(id);
				
				if (movie != null) {
					movieDAO.delete(movie);
					System.out.println("Movie Deleted: " + movie);
					
				} else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 6:
				System.out.println("Get all Actors");
				System.out.println("=============================");
				
				for (Actor actor : actorDAO.getAll()) {
					System.out.println(actor);
				}
				break;
				
			case 7:
				System.out.println("Get Actor by ID");
				System.out.println("=============================");
				
				id = Console.getInt("ID: ");
				Actor actor = actorDAO.get(id);
				
				if (actor != null) {
					System.out.println("Actor Found: " + actor);
					
				} else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 8:
				System.out.println("Add Actor");
				System.out.println("=============================");
				
				String firstName = Console.getString("First Name: ");
				String lastName = Console.getString("Last Name: ");
				String gender = Console.getString("Gender: ");
				String birthdayStr = Console.getString("Birthday: ");
				LocalDate birthday = LocalDate.parse(birthdayStr);


				if (actorDAO.add(new Actor(firstName, lastName, gender, birthday))) {
					System.out.println("Item added!");
					
				} else {
					System.out.println("Error adding item.");
				}
				break;
				
			case 9:
				System.out.println("Update Actor");
				System.out.println("=============================");
				
				id = Console.getInt("ID: ");
				actor = actorDAO.get(id);
				
				if (actor != null) {
					
					firstName = Console.getString("First Name: ");
					lastName = Console.getString("Last Name: ");
					gender = Console.getString("Gender: ");
					birthdayStr = Console.getString("Birthday(yyyy-mm-dd): ");
					birthday = LocalDate.parse(birthdayStr);
					
					actor.setFirstName(firstName);
					actor.setLastName(lastName);
					actor.setGender(gender);
					actor.setDate(birthday);
					
					actorDAO.update(actor);
					
					System.out.println("Actor updated: " + actor);
				} else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 10:
				System.out.println("Delete an Actor");
				System.out.println("=============================");
				
				id = Console.getInt("ID: ");
				actor = actorDAO.get(id);
				
				if (actor != null) {
					actorDAO.delete(actor);
					System.out.println("Actor Deleted: " + actor);
					
				} else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 11:
				System.out.println("Get all Credits");
				System.out.println("=============================");
				
				for (Credit credit : creditDb.getAll()) {
					System.out.println(credit);
				}
				break;
				
			case 12:
				System.out.println("Get Credit by ID");
				System.out.println("=============================");
				
				id = Console.getInt("ID: ");
				Credit credit = creditDb.get(id);
				
				if (credit != null) {
					System.out.println("Credit Found: " + credit);
					
				} else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 13:
				System.out.println("Add Credit");
				System.out.println("=============================");
				
				int actorId = Console.getInt("Actor ID: ");
				int movieId = Console.getInt("Movie ID: ");
				String role = Console.getString("Role: ");

				if (creditDb.add(new Credit(actorDAO.get(actorId), movieDAO.get(movieId), role))) {
					System.out.println("Item added!");
					
				} else {
					System.out.println("Error adding item.");
				}
				break;
				
			case 14:
				System.out.println("Update Credit");
				System.out.println("=============================");
				
				id = Console.getInt("ID: ");
				credit = creditDb.get(id);
				
				if (credit != null) {
					
					actorId = Console.getInt("Actor ID: ");
					movieId = Console.getInt("Movie ID: ");
					role = Console.getString("Role: ");
					
					credit.getActor().setId(actorId);
					credit.getMovie().setId(movieId);
					credit.setRole(role);
					
					creditDb.update(credit);
					
					System.out.println("Credit updated: " + credit);
				} else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 15:
				System.out.println("Delete a Credit");
				System.out.println("=============================");
				
				id = Console.getInt("ID: ");
				credit = creditDb.get(id);
				
				if (credit != null) {
					creditDb.delete(credit);
					System.out.println("Credit Deleted: " + credit);
					
				} else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 16:
				// get credits for a movie
				System.out.println("Get Credits by Movie:");
				System.out.println("=============================\n");
				id = Console.getInt("Movie ID: ");
				movie = movieDAO.get(id);
				if (movie != null) {
					List<Credit> credits = creditDb.getCreditsForMovie(movie);
					System.out.println("Credits for \""+movie.getTitle()+ 
							"("+movie.getYear()+")\":");
					System.out.println("==========================================");
					for (Credit c: credits) {
						System.out.println(c.getActor().fullName()+" - "+c.getRole());
					}
				}
				else {
					System.out.println(ITEM_NOT_FOUND + id);
				}
				break;
				
			case 0:
				// exit
				break;
				
			default:
				System.out.println("Invalid command!  Try again.");
				break;
			}
		}

		System.out.println("Bye");

	}
	
	private static void msg(String string) {
		System.out.println(string);
	}

	private static void displayMenu() {
		System.out.println("\nCOMMAND MENU:");
		System.out.println("====================");
		System.out.println("1 - Get all movies");
		System.out.println("2 - Get a movie");
		System.out.println("3 - Add a movie");
		System.out.println("4 - Update a movie");
		System.out.println("5 - Delete a movie");
		System.out.println("----------------------");
		System.out.println("6 - Get all actors");
		System.out.println("7 - Get an actor");
		System.out.println("8 - Add an actor");
		System.out.println("9 - Update an actor");
		System.out.println("10 - Delete an actor");
		System.out.println("----------------------");
		System.out.println("11 - Get all credits");
		System.out.println("12 - Get a credit");
		System.out.println("13 - Add a credit");
		System.out.println("14 - Update a credit");
		System.out.println("15 - Delete a credit");
		System.out.println("16 - Show all credits for Movie");
		System.out.println("0 - Exit");
		System.out.println();
	}

}