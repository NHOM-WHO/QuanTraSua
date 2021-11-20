package Views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connect {

	public static void main(String[] args) throws SQLException {
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

	}

}
