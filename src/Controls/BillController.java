package Controls;

import java.sql.*;
import java.util.*;
import Models.BillModel;
import Startup.Startup;

public class BillController {
    public static ArrayList<BillModel> getAll() {

        ArrayList<BillModel> list=new ArrayList<BillModel>();

        Connection conn= Startup.getConnection();
     
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Bill");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nguoi_lap = rs.getString("nguoi_lap");
                String ban_an=BanAnController.getByPk(rs.getInt("ban_an")).getName();
                String loai=rs.getString("loai");
                String status=rs.getString("trang_thai");
                String ngaylap_bill=rs.getString("ngaylap_bill");
                String ngaythanh_toan=rs.getString("ngaythanh_toan");
                int tong_cong=rs.getInt("tong_cong");
                int thanh_toan=rs.getInt("da_thanhtoan");
                int giam_gia=rs.getInt("giam_gia");
                
                BillModel bill = new BillModel(
                    id, 
                    nguoi_lap, 
                    ban_an,loai,
                    status,
                    ngaylap_bill,
                    ngaythanh_toan,
                    tong_cong,
                    thanh_toan,
                    giam_gia);
                list.add(bill);
            }

            return list;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return null;
        }
    } 
    
    public static ArrayList<BillModel> getByType() {

        ArrayList<BillModel> list=new ArrayList<BillModel>();

        Connection conn= Startup.getConnection();
     
        try {
            String query="SELECT * FROM Bill where loai=?";
            PreparedStatement st = conn.prepareStatement(query);

            st.setString(1, "Đặt hàng");

            ResultSet rs=st.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nguoi_lap = rs.getString("nguoi_lap");
                String ban_an=BanAnController.getByPk(rs.getInt("ban_an")).getName();
                String loai=rs.getString("loai");
                String status=rs.getString("trang_thai");
                String ngaylap_bill=rs.getString("ngaylap_bill");
                String ngaythanh_toan=rs.getString("ngaythanh_toan");
                int tong_cong=rs.getInt("tong_cong");
                int thanh_toan=rs.getInt("da_thanhtoan");
                int giam_gia=rs.getInt("giam_gia");
                
                BillModel bill = new BillModel(
                    id, 
                    nguoi_lap, 
                    ban_an,loai,
                    status,
                    ngaylap_bill,
                    ngaythanh_toan,
                    tong_cong,
                    thanh_toan,
                    giam_gia);
                list.add(bill);
            }

            return list;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    public static BillModel getByPk(int id){

        Connection connection = null;
        try {
            connection = Startup.getConnection();

            String query = "select * FROM Bill WHERE id=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Integer.toString(id));
            ResultSet rs=ps.executeQuery();

            rs.next();
   
            String nguoi_lap = rs.getString("nguoi_lap");
            String ban_an=BanAnController.getByPk(rs.getInt("ban_an")).getName();
            String loai=rs.getString("loai");
            String status=rs.getString("trang_thai");
            String ngaylap_bill=rs.getString("ngaylap_bill");
            String ngaythanh_toan=rs.getString("ngaythanh_toan");
            int tong_cong=rs.getInt("tong_cong");
            int thanh_toan=rs.getInt("da_thanhtoan");
            int giam_gia=rs.getInt("giam_gia");
            
            BillModel bill = new BillModel(
                id, 
                nguoi_lap, 
                ban_an,loai,
                status,
                ngaylap_bill,
                ngaythanh_toan,
                tong_cong,
                thanh_toan,
                giam_gia);
            return bill;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static boolean update(BillModel mod){

        Connection connection = null;
        try {
            connection = Startup.getConnection();

            if(!mod.getNgayThanhToan().equals("")){
                String query = "update Bill set ban_an=?,loai=?,trang_thai=?,ngaythanh_toan=?,tong_cong=?,da_thanhtoan=?,giam_gia=? WHERE id=?";

                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(mod.getBanAn()));
                ps.setString(2, mod.getLoai());
                ps.setString(3, mod.getTrangThai());
                ps.setString(4, mod.getNgayThanhToan());
                ps.setInt(5, mod.getTongCong());
                ps.setInt(6, mod.getThanhToan());
                ps.setInt(7, mod.getGiamGia());
                ps.setInt(8, mod.getId());
                ps.executeUpdate();
            }
            else{
                String query = "update Bill set ban_an=?,loai=?,trang_thai=?,tong_cong=?,da_thanhtoan=?,giam_gia=? WHERE id=?";

                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(mod.getBanAn()));
                ps.setString(2, mod.getLoai());
                ps.setString(3, mod.getTrangThai());
                ps.setInt(4, mod.getTongCong());
                ps.setInt(5, mod.getThanhToan());
                ps.setInt(6, mod.getGiamGia());
                ps.setInt(7, mod.getId());
                ps.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean add(BillModel mod){

        Connection connection = null;
        try {
            connection = Startup.getConnection();
            String query = "insert into Bill (nguoi_lap,ban_an,loai,trang_thai,ngaylap_bill,tong_cong,da_thanhtoan,giam_gia) values (?,?,?,?,?,?,?,?)" ;
        
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, mod.getNguoiLap());
            ps.setInt(2, Integer.parseInt(mod.getBanAn()));
            ps.setString(3, mod.getLoai());
            ps.setString(4, mod.getTrangThai());
            ps.setString(5, mod.getNgayLapBill());
            ps.setInt(6, mod.getTongCong());
            ps.setInt(7, mod.getThanhToan());
            ps.setInt(8, mod.getGiamGia());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;            
        }

    }


    public static boolean delete(int id){


        Connection connection = null;
        try {
            ShipController.delete(id);
            BillDetailController.delete(id);
            connection = Startup.getConnection();
            String query = "delete from Bill where id=?" ;
        
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;            
        }
    }

    public static ArrayList<BillModel> getByBanAn(int banAn) {

        ArrayList<BillModel> list=new ArrayList<BillModel>();

        Connection conn= Startup.getConnection();
     
        try {
            String query="SELECT * FROM Bill where ban_an=?";
            PreparedStatement st = conn.prepareStatement(query);

            st.setInt(1, banAn);

            ResultSet rs=st.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nguoi_lap = rs.getString("nguoi_lap");
                String ban_an=BanAnController.getByPk(rs.getInt("ban_an")).getName();
                String loai=rs.getString("loai");
                String status=rs.getString("trang_thai");
                String ngaylap_bill=rs.getString("ngaylap_bill");
                String ngaythanh_toan=rs.getString("ngaythanh_toan");
                int tong_cong=rs.getInt("tong_cong");
                int thanh_toan=rs.getInt("da_thanhtoan");
                int giam_gia=rs.getInt("giam_gia");
                
                BillModel bill = new BillModel(
                    id, 
                    nguoi_lap, 
                    ban_an,loai,
                    status,
                    ngaylap_bill,
                    ngaythanh_toan,
                    tong_cong,
                    thanh_toan,
                    giam_gia);
                list.add(bill);
            }

            return list;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
}
