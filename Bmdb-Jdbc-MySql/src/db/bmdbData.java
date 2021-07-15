package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import business.Movie;
import interfaces.DAO;

public class bmdbData extends BaseDb implements DAO<Movie> {

	private Movie getMovieFromRow(ResultSet rs) throws SQLException {
		Movie movie;
		int idPk = rs.getInt(1);
		String title = rs.getString(2);
		int year = rs.getInt(3);
		String rating = rs.getString(4);
		String director = rs.getString(5);
		movie = new Movie(idPk, year, title, rating, director);
		return movie;
	}

	@Override
	public Movie get(int id) {
		Movie movie = null;
		String sql = "SELECT * FROM movie WHERE id = ?";

		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				movie = getMovieFromRow(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}

	@Override
	public List<Movie> getAll() {

		List<Movie> movies = new ArrayList<>();

		try (Statement stmt = getConnection().createStatement()) { // Try with resources statement
			ResultSet rs = stmt.executeQuery("SELECT * FROM movie ORDER BY id");

			while (rs.next()) { // While there is a next line to get. For each row parse an item.
				Movie movie = getMovieFromRow(rs);
				movies.add(movie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movies;
	}

	@Override
	public boolean add(Movie movie) {
		boolean success = false;
		String sql = "INSERT INTO movie (title, year, rating, director) values (?,?,?,?)";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, movie.getTitle());
			stmt.setInt(2, movie.getYear());
			stmt.setString(3, movie.getRating());
			stmt.setString(4, movie.getDirector());

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
	public boolean update(Movie movie) {
		boolean success = false;
		String sql = "UPDATE movie SET title = ?, year = ?, rating = ?, director = ? WHERE id = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, movie.getTitle());
			stmt.setInt(2, movie.getYear());
			stmt.setString(3, movie.getRating());
			stmt.setString(4, movie.getDirector());
			stmt.setInt(5, movie.getId());

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
	public boolean delete(Movie movie) {
		boolean success = false;
		String sql = "DELETE FROM movie WHERE id = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, movie.getId());
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
