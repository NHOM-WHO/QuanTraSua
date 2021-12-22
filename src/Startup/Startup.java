
package Startup;
import java.sql.*;

import Config.Config;

public class Startup {
    private static Connection connection=null;
    public Startup(){

        startup();
    }

    private static void establishConnectionDB(){
        try {
			Class.forName(Config.className);
			String connectionURL=Config.connectionURL;
			

			connection=DriverManager.getConnection(connectionURL);
			
			

		} catch (Exception e) {
            e.printStackTrace();
			System.out.println("Kết nối thất bại !!!");
		}
    }

    private static void startup(){

        establishConnectionDB();
    }

    public static Connection getConnection(){

        if(connection==null){

            establishConnectionDB();
        }
        return connection;
    }
}
