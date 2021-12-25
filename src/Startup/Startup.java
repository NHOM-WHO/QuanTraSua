

// NGHI
// package Startup;
// import java.sql.*;

// import Config.Config;



// public class Startup {
//     private static Connection connection=null;
//     public Startup(){

//         startup();
//     }

//     private static void establishConnectionDB(){
//         try {
// 			Class.forName(Config.className);
// 			String connectionURL=Config.connectionURL;
// 			String user = Config.username;
//             String pass = Config.password;

// 			connection=DriverManager.getConnection(connectionURL,user,pass);
			
// 			System.out.println("Kết nối cơ sở dữ liệu thành công !!!!!!!!!");

// 		} catch (Exception e) {
//             e.printStackTrace();
// 			System.out.println("Kết nối thất bại !!!");
// 		}
//     }

//     private static void startup(){

//         establishConnectionDB();
//     }

//     public static Connection getConnection(){

//         if(connection==null){

//             establishConnectionDB();
//         }
//         return connection;
//     }
// }


package Startup;
import java.sql.*;

import Config.Config;
// THAI
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

