package Views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;
	
public class connect {
	public static Connection getConnection() throws SQLException {
		Connection connection =null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL="jdbc:sqlserver://DESKTOP-RLD2Q4E\\CAOTHAI:1433;databaseName=QuanTraSua;integratedSecurity=true";
			connection=DriverManager.getConnection(connectionURL, "sa", "sa");
			System.out.println("Kết nối cơ sở dữ liệu thành công !!!!!!!!!");
		} catch (ClassNotFoundException e) {
			System.out.println("Kết nối thất bại !!!");
			System.err.println(e.getMessage()+"/n"+e.getClass()+"/n"+e.getCause());
		}
		return connection;
	}

	public static void main(String[] args) throws SQLException {
			getConnection();

	}

}