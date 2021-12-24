
package Controls;

import java.sql.*;
import java.util.*;
import Models.BanAn;
import Startup.Startup;

public class BanAnController {
	public static ArrayList<BanAn> getAll() {
		ArrayList<BanAn> list = new ArrayList<BanAn>();
		Connection conn = Startup.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM BanAn");

			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String status = rs.getString("Status");
				BanAn banAn = new BanAn(id, name, status);
				list.add(banAn);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage() + "/n" + e.getClass() + "/n" + e.getCause());
		}
		return list;
	}

	public static boolean add(BanAn ins) {

		String name = ins.getName();
		String status = ins.getStatus();

		try {
			Connection conn = Startup.getConnection();
			String query = "INSERT INTO BanAn (Name,Status) VALUES (?,?);";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, status);

			if (ps.executeUpdate() > 0) {
				return true;
			} else
				return false;

		} catch (Exception e) {
			System.err.println(e.getMessage() + "/n" + e.getClass() + "/n" + e.getCause());
			return false;
		}
	}

	public static boolean update(BanAn ins) {

		String name = ins.getName();
		String status = ins.getStatus();
		int id = ins.getId();

		Connection connection = null;
		try {
			connection = Startup.getConnection();
			String query = "UPDATE BanAn SET Name=?,Status=? WHERE Id=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(id));
			ps.setString(2, name);
			ps.setString(3, status);

			if (ps.executeUpdate() > 0) {
				return true;
			} else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delete(int id) {

		Connection connection = null;
		try {
			connection = Startup.getConnection();

			String query = "Delete FROM BanAn WHERE Id=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(id));

			if (ps.executeUpdate() > 0) {
				return true;
			} else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ResultSet getAllResultSet() {

		Connection connection = null;
		try {
			connection = Startup.getConnection();
			Statement stmt = connection.createStatement();
			String query = "select * from BanAn";
			return stmt.executeQuery(query);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}

	}

	public static BanAn getByPk(int id) {

		Connection connection = null;
		try {
			connection = Startup.getConnection();

			String query = "select * FROM BanAn WHERE Id=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();

			rs.next();
			String name = rs.getString("Name");
			String status = rs.getString("Status");

			return new BanAn(id, name, status);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
