package Controls;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Views.connect;

public class Employees {
	public static ArrayList<Models.Employees> getAllEmployees() throws SQLException {
		ArrayList<Models.Employees> list = new ArrayList<>();
		Connection conn= connect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM Employees");
			while(rs.next()) {
				int id=rs.getInt("ID");
				String username=rs.getString("USERNAME");
				String password=rs.getString("PASSWORD");
				String name=rs.getString("NAME");
				String phone=rs.getString("PHONENUMBER");
				String permission=rs.getString("PERMISSION");
				String salary=rs.getString("SALARY");
				Models.Employees employees= new Models.Employees(0, username, password, name, phone, permission, salary);
				list.add(employees);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage()+"/n"+e.getClass()+"/n"+e.getCause());
		}
		return list;
	}
}
