
package Controls;

import Models.ProducTypeModel;
import Startup.Startup;
import java.sql.*;
import java.util.*;
public class ProductTypeController {
    
    public static ArrayList<ProducTypeModel> getAll() {

        ArrayList<ProducTypeModel> list=new ArrayList<ProducTypeModel>();

        Connection conn= Startup.getConnection();
     
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ProductType");

            while (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                String slug=rs.getString("slug");
           
                ProducTypeModel pt = new ProducTypeModel(
                    id,
                    ten,
                    slug);
                list.add(pt);
            }

            return list;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return null;
        }
    } 

    public static ProducTypeModel getBySlug(String slug){

        Connection connection = null;
        try {
            connection = Startup.getConnection();

            String query = "select * FROM ProductType WHERE slug=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, slug);
            ResultSet rs=ps.executeQuery();

            rs.next();
            int id=rs.getInt("id");
            String ten=rs.getString("ten");

            return new ProducTypeModel(id,ten,slug); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
