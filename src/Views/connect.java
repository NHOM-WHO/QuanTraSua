


// NGHI
// package Views;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;

// import javax.swing.table.DefaultTableModel;

// public class connect {
// 	public static Connection getConnection() throws SQLException {
// 		Connection connection = null;
// 		try {
// 			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
// 			String connectionURL="jdbc:sqlserver://DESKTOP-859V190\\SQLEXPRESS:0;databaseName=QuanTS";
// 			String user = "sa";
//             String pass = "123456789";
// 			connection = DriverManager.getConnection(connectionURL,user,pass);
// 			System.out.println("Kết nối thành công !!!");
// 		} catch (ClassNotFoundException e) {
// 			System.out.println("Kết nối thất bại !!!");
// 			System.err.println(e.getMessage() + "/n" + e.getClass() + "/n" + e.getCause());
// 		}
// 		return connection;
// 	}

// 	public static void main(String[] args) throws SQLException {
// 		getConnection();

// 	}

// }

// THAI

package Views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class connect {
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://DESKTOP-RLD2Q4E\\CAOTHAI:1433;databaseName=QuanTS;integratedSecurity=true";
			connection = DriverManager.getConnection(connectionURL);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Kết nối thất bại !!!");
			System.err.println(e.getMessage() + "/n" + e.getClass() + "/n" + e.getCause());
		}
		return connection;
	}

	public static void main(String[] args) throws SQLException {
		getConnection();

	}

}