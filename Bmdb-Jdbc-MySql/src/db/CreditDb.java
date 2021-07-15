package db;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import business.Actor;
import business.Credit;
import business.Movie;
import interfaces.DAO;

public class CreditDb extends BaseDb implements DAO<Credit> {
	
	public List<Credit> getCreditsForMovie(Movie movie) {
		List<Credit> credits = new ArrayList<>();
		String sql = "select * from credit"
				+ "  join actor on actorid = actor.ID"
				+ "  join movie on movieid = movie.ID"
				+ "  where movieid = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, movie.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Credit credit = getCreditFromRow(rs);
				credits.add(credit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return credits;
	}


	private Credit getCreditFromRow(ResultSet rs) throws SQLException {
		Credit credit;
		int actorId = rs.getInt(5);
		String firstName = rs.getString(6);
		String lastName = rs.getString(7);
		String gender = rs.getString(8);
		String birthdayString = rs.getString(9);
		LocalDate birthday = LocalDate.parse(birthdayString);
		
		Actor actor = new Actor(actorId, firstName, lastName, gender, birthday);
		
		int movieId = rs.getInt(10);
		String title = rs.getString(11);
		int year = rs.getInt(12);
		String rating = rs.getString(13);
		String director = rs.getString(14);

		Movie movie = new Movie(movieId, year, title, rating, director);
		
		int creditId = rs.getInt(1);
		String role = rs.getString(4);


		credit = new Credit(creditId, actor, movie, role);
		return credit;
	}

	@Override
	public Credit get(int id) {
		Credit credit = null;
		String sql = ("SELECT * FROM credit c"
				+ " JOIN actor ON actorid = actor.ID"
				+ " JOIN movie ON movieid = movie.ID"
				+ " WHERE c.id = ?;");

		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				credit = getCreditFromRow(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return credit;
	}

	@Override
	public List<Credit> getAll() {

		List<Credit> credits = new ArrayList<>();

		try (Statement stmt = getConnection().createStatement()) { // Try with resources statement
			ResultSet rs = stmt.executeQuery("SELECT * FROM credit\n"
					+ "JOIN actor ON actorid = actor.ID\n"
					+ "JOIN movie ON movieid = movie.ID;");

			while (rs.next()) { // While there is a next line to get. For each row parse an item.
				Credit credit = getCreditFromRow(rs);
				credits.add(credit);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return credits;
	}

	@Override
	public boolean add(Credit credit) {
		boolean success = false;
		String sql = "INSERT INTO credit (ActorId, MovieId, Role) values (?,?,?)";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, credit.getActor().getId());
			stmt.setInt(2, credit.getMovie().getId());
			stmt.setString(3, credit.getRole());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 1) {
				success = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean update(Credit credit) {
		boolean success = false;
		String sql = "UPDATE credit SET ActorId = ?, MovieId = ?, Role = ? WHERE id = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, credit.getActor().getId());
			stmt.setInt(2, credit.getMovie().getId());
			stmt.setString(3, credit.getRole());
			stmt.setInt(4, credit.getId());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 1) {
				success = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delete(Credit credit) {
		boolean success = false;
		String sql = "DELETE FROM credit WHERE id = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, credit.getId());
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 1) {
				success = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

}
