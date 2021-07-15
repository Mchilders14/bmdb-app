import java.sql.*;

class MysqlConnection {

	public static void main(String args[]) {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bmdb", "root", "Bluetruck14");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Actor");
			ResultSet rs2 = stmt.executeQuery("SELECT * FROM Credit");
			ResultSet rs3 = stmt.executeQuery("SELECT * FROM Movie");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
