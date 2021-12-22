
package Controls;
import Startup.Startup;
import java.util.*;
import Models.CustomerModel;
import java.sql.*;
public class CustomerController {
    
    public static ArrayList<CustomerModel> getAll(){

        Connection conn=null;
        ArrayList<CustomerModel> list=new ArrayList<CustomerModel>();
        try {
            conn=Startup.getConnection();

            Statement st=conn.createStatement();

            String query="select * from Customer";

            ResultSet rs = st.executeQuery(query);

            while(rs.next()){

                CustomerModel cm= new CustomerModel(
                    rs.getInt("id"),
                    rs.getString("ten"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("ngay_sinh")
                );
                list.add(cm);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean update(CustomerModel cm){

        Connection conn=null;
        try {
            conn=Startup.getConnection();
            String query="update Customer set ten=?,phone=?,address=?,ngay_sinh=? where id=?";
            PreparedStatement ps=conn.prepareStatement(query);

            ps.setString(1, cm.getName());
            ps.setString(2, cm.getPhone());
            ps.setString(3, cm.getAddress());
            ps.setString(4, cm.getNgaySinh());
            ps.setInt(5, cm.getId());

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean add(CustomerModel cm){

        Connection conn=null;
        try {
            conn=Startup.getConnection();
            String query="insert into Customer (ten,phone,address,ngay_sinh) values (?,?,?,?);";
            PreparedStatement ps=conn.prepareStatement(query);

            ps.setString(1, cm.getName());
            ps.setString(2, cm.getPhone());
            ps.setString(3, cm.getAddress());
            ps.setString(4, cm.getNgaySinh());

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int id){

        Connection conn=null;
        try {
            conn=Startup.getConnection();
            String query="delete from Customer where id=?";
            PreparedStatement ps=conn.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static CustomerModel getByPk(int id){

        Connection connection = null;
        try {
            connection = Startup.getConnection();

            String query = "select * FROM Customer WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Integer.toString(id));
            ResultSet rs=ps.executeQuery();

            rs.next();
   
            String name = rs.getString("ten");
            String phone=rs.getString("phone");
            String address=rs.getString("address");
            String ngaysinh=rs.getString("ngay_sinh");
            
            CustomerModel bill = new CustomerModel(
                id,
                name,
                phone,
                address,
                ngaysinh);
            return bill;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
