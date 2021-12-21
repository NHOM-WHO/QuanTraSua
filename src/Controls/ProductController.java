
package Controls;

import Models.ProductModel;
import Startup.Startup;
import java.sql.*;
import java.util.*;

public class ProductController {


    public static ArrayList<ProductModel> getAll() {
        ArrayList<ProductModel> list = new ArrayList<ProductModel>();
        Connection conn = Startup.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Product");
       
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("ten");
                int type = rs.getInt("loai_sp");
                int gia=rs.getInt("gia");
                String unit=rs.getString("don_vi");
                String img=rs.getString("hinh_anh");
                ProductModel banAn = new ProductModel(id, name, type,gia,unit,img);
                list.add(banAn);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage() + "/n" + e.getClass() + "/n" + e.getCause());
        }
        return list;
    }
    
    public static ArrayList<ProductModel> getByProductType(int type){

        ArrayList<ProductModel> ap=new ArrayList<ProductModel>();
        Connection connection = null;
        try {
            connection = Startup.getConnection();

            String query = "select * FROM Product WHERE loai_sp=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Integer.toString(type));
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                
                int id=rs.getInt("id");
                String ten=rs.getString("ten");
                int loai=rs.getInt("loai_sp");
                int gia=rs.getInt("gia");
                String unit=rs.getString("don_vi");
                String image=rs.getString("hinh_anh");

                ap.add(new ProductModel(id, ten, loai, gia, unit, image));
            }

            return ap; 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ProductModel getByPk(int id){

        Connection connection = null;
        try {
            connection = Startup.getConnection();

            String query = "select * FROM Product WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Integer.toString(id));
            ResultSet rs=ps.executeQuery();

            rs.next();
            String ten=rs.getString("ten");
            int loai=rs.getInt("loai_sp");
            int gia=rs.getInt("gia");
            String unit=rs.getString("don_vi");
            String image=rs.getString("hinh_anh");

            return new ProductModel(id,ten,loai,gia,unit,image); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<ProductModel> getTopping(int id){

        ArrayList<ProductModel> ap=new ArrayList<ProductModel>();
        Connection connection = null;
        try {
            connection = Startup.getConnection();

            String query = "select * FROM Product_Topping WHERE san_pham=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Integer.toString(id));
            ResultSet rs=ps.executeQuery();
            if(rs != null){

                while(rs.next()){
                    int toppingId=rs.getInt("topping");

                    ProductModel pm=getByPk(toppingId);
                    ap.add(pm);
                }
            }
            return ap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean productServedTopping(int id){

        Connection connection = null;
        try {
            connection = Startup.getConnection();

            String query = "select * FROM Product_Topping WHERE san_pham=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Integer.toString(id));
            ResultSet rs=ps.executeQuery();
            if(rs != null){

                if(rs.next())
                    return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
