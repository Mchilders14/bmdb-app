import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import com.util.Console;

public class BmdbConsoleApp {

	public static void main(String[] args) throws ParseException {

		msg("Welcome to the Big Movie Database!");

		Scanner sc = new Scanner(System.in);
		String choice = "y";

		while (choice.equalsIgnoreCase("y")) {
			msg("Please choose desired entry.\n============================\n");
			String userEntry = Console.getString("Actor(actor)\n\nCredit(credit)\n\nMovie(movie)\n\nEntry: ");
			userChoice(userEntry);
			choice = Console.askToContinue(sc);
		}

		msg("Now exiting the Big Movie Database!");
	}

	private static void msg(String string) {
		System.out.println(string);
	}

	private static void userChoice(String entry) throws ParseException {

		switch (entry) {

		case "Actor":

		{
			int actorId = Console.getInt("Actor ID: ");
			String firstName = Console.getString("First Name: ");
			String lastName = Console.getString("Last Name: ");
			String gender = Console.getString("Gender: ");
			Date date = Console.getDate("Date of Birth [mm/dd/yyyy]: ");
			Actor a1 = new Actor(actorId, firstName, lastName, gender, date);
			System.out.println(a1.toString());
			break;
		}

		case "actor":

		{
			int actorId = Console.getInt("Actor ID: ");
			String firstName = Console.getString("First Name: ");
			String lastName = Console.getString("Last Name: ");
			String gender = Console.getString("Gender: ");
			Date date = Console.getDate("Date of Birth [mm/dd/yyyy]: ");
			Actor a1 = new Actor(actorId, firstName, lastName, gender, date);
			System.out.println(a1.toString());
			break;
		}

		case "Credit":

		{
			int creditId = Console.getInt("Credit ID: ");
			int aId = Console.getInt("Actor ID: ");
			int mId = Console.getInt("Movie ID: ");
			String role = Console.getString("Role: ");
			Credit c1 = new Credit(creditId, aId, mId, role);
			System.out.println(c1.toString());
			break;
		}

		case "credit":

		{
			int creditId = Console.getInt("Credit ID: ");
			int aId = Console.getInt("Actor ID: ");
			int mId = Console.getInt("Movie ID: ");
			String role = Console.getString("Role: ");
			Credit c1 = new Credit(creditId, aId, mId, role);
			System.out.println(c1.toString());
			break;
		}

		case "Movie":

		{
			int movieId = Console.getInt("Movie ID: ");
			int year = Console.getInt("Year: ");
			String title = Console.getString("Title: ");
			String rating = Console.getString("Rating: ");
			String director = Console.getString("Director: ");
			Movie m1 = new Movie(movieId, year, title, rating, director);
			System.out.println(m1.toString());
			break;
		}

		case "movie":

		{
			int movieId = Console.getInt("Movie ID: ");
			int year = Console.getInt("Year: ");
			String title = Console.getString("Title: ");
			String rating = Console.getString("Rating: ");
			String director = Console.getString("Director: ");
			Movie m1 = new Movie(movieId, year, title, rating, director);
			System.out.println(m1.toString());
			break;
		}

		}
	}
}
