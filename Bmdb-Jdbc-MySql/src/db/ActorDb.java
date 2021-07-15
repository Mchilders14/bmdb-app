package db;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import business.Actor;
import interfaces.DAO;

public class ActorDb extends BaseDb implements DAO<Actor> {

	private Actor getActorFromRow(ResultSet rs) throws SQLException {
		Actor actor;
		int idPk = rs.getInt(1);
		String firstName = rs.getString(2);
		String lastName = rs.getString(3);
		String gender = rs.getString(4);
		String birthdayStr = rs.getString(5) ;
		LocalDate birthday =  LocalDate.parse(birthdayStr);
		actor = new Actor(idPk, firstName, lastName, gender, birthday);
		return actor;
	}

	@Override
	public Actor get(int id) {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";

		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = getActorFromRow(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> getAll() {

		List<Actor> actors = new ArrayList<>();

		try (Statement stmt = getConnection().createStatement()) { // Try with resources statement
			ResultSet rs = stmt.executeQuery("SELECT * FROM actor ORDER BY id");

			while (rs.next()) { // While there is a next line to get. For each row parse an item.
				Actor actor = getActorFromRow(rs);
				actors.add(actor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}

	@Override
	public boolean add(Actor actor) {
		boolean success = false;
		String sql = "INSERT INTO actor (firstName, lastName, gender, birthDate) values (?,?,?,?)";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setString(3, actor.getGender());
			stmt.setString(4, actor.getDate().toString());

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
	public boolean update(Actor actor) {
		boolean success = false;
		String sql = "UPDATE actor SET firstName = ?, lastName = ?, gender = ?, birthDate = ? WHERE id = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setString(3, actor.getGender());
			stmt.setString(4, actor.getDate().toString());
			stmt.setInt(5, actor.getId());

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
	public boolean delete(Actor actor) {
		boolean success = false;
		String sql = "DELETE FROM actor WHERE id = ?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
			stmt.setInt(1, actor.getId());
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
