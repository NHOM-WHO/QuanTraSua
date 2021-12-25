package Controls;

import java.sql.*;
import java.util.*;
import Models.ShipModel;
import Startup.Startup;

public class ShipController {
    
    public static ArrayList<ShipModel> getAll(){

        Connection conn=null;
        ArrayList<ShipModel> list=new ArrayList<ShipModel>();

        try {
            conn=Startup.getConnection();
            
            String query="select * from Ship";

            Statement st=conn.createStatement();
            
            ResultSet rs=st.executeQuery(query);

            while(rs.next()){

                ShipModel sm=new ShipModel(
                    rs.getInt("id"), 
                    rs.getInt("bill"), 
                    rs.getInt("customer"), 
                    rs.getInt("gia"), 
                    rs.getString("shipper"), 
                    rs.getString("phone"), 
                    rs.getString("status"), 
                    rs.getString("ngay_batdau"), 
                    rs.getString("ngay_ketthuc")
                );

                list.add(sm);
            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean add(ShipModel mod){

        Connection conn=null;

        try {
            conn=Startup.getConnection();
            String query="insert into Ship (bill,customer,shipper,phone,gia,status,ngay_batdau) values (?,?,?,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(query);

            ps.setInt(1, mod.getBill());
            ps.setInt(2, mod.getCustomer());
            ps.setString(3, mod.getShipper());
            ps.setString(4, mod.getPhone());
            ps.setInt(5, mod.getGia());
            ps.setString(6, mod.getStatus());
            ps.setString(7, mod.getNgayBatDau());

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
            String query="delete from Ship where id=?";
            PreparedStatement ps=conn.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteByBill(int id){

        Connection conn=null;

        try {
            conn=Startup.getConnection();
            String query="delete from Ship where bill=?";
            PreparedStatement ps=conn.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(ShipModel mod){

        Connection conn=null;

        try {

            if(mod.getStatus().equals("Hoàn thành")){

                conn=Startup.getConnection();
                String query="update Ship set bill=?,customer=?,shipper=?,phone=?,gia=?,status=?,ngay_ketthuc=? where id=?";
                PreparedStatement ps=conn.prepareStatement(query);
    
                ps.setInt(1, mod.getBill());
                ps.setInt(2, mod.getCustomer());
                ps.setString(3, mod.getShipper());
                ps.setString(4, mod.getPhone());
                ps.setInt(5, mod.getGia());
                ps.setString(6, mod.getStatus());
                ps.setString(7, mod.getNgayKetThuc());
                ps.setInt(8, mod.getId());
    
                ps.executeUpdate();
            }
            else{

                conn=Startup.getConnection();
                String query="update Ship set bill=?,customer=?,shipper=?,phone=?,gia=?,status=? where id=?";
                PreparedStatement ps=conn.prepareStatement(query);
    
                ps.setInt(1, mod.getBill());
                ps.setInt(2, mod.getCustomer());
                ps.setString(3, mod.getShipper());
                ps.setString(4, mod.getPhone());
                ps.setInt(5, mod.getGia());
                ps.setString(6, mod.getStatus());
                ps.setInt(7, mod.getId());
    
                ps.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ShipModel getByPk(int id){

        Connection conn=null;

        try {
            conn=Startup.getConnection();
            
            String query="select * from Ship where id=?";

            PreparedStatement st=conn.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs=st.executeQuery();
            rs.next();
            ShipModel sm=new ShipModel(
                rs.getInt("id"), 
                rs.getInt("bill"), 
                rs.getInt("customer"), 
                rs.getInt("gia"), 
                rs.getString("shipper"), 
                rs.getString("phone"), 
                rs.getString("status"), 
                rs.getString("ngay_batdau"), 
                rs.getString("ngay_ketthuc")
            );

            return sm;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
