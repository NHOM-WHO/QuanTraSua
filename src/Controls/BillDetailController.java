
package Controls;

import Models.BillDetailModel;
import java.util.*;
import Startup.Startup;
import java.sql.*;
import Models.BillDetailProductToppingModel;
import Models.ProductModel;
import Models.BillDetailToppingModel;

public class BillDetailController {
    
    public static ArrayList<BillDetailModel> getByBillId(int billid){

        ArrayList<BillDetailModel> ap=new ArrayList<BillDetailModel>();
        Connection connection = null;

        try {
            connection = Startup.getConnection();

            String query = "select * FROM BillDetail WHERE bill=?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, Integer.toString(billid));
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
                
                int id=rs.getInt("id");
                int bill=rs.getInt("bill");
                int product=rs.getInt("san_pham");
                int gia=rs.getInt("gia");
                int soluong=rs.getInt("so_luong");

                ap.add(new BillDetailModel(id, bill, product, gia, soluong));
            }

            return ap; 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public static ArrayList<BillDetailToppingModel> getAll() {
        ArrayList<BillDetailToppingModel> list = new ArrayList<BillDetailToppingModel>();
        Connection conn = Startup.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM BillDetail_Topping");

            while (rs.next()) {
                int id = rs.getInt("id");
                int bill = rs.getInt("bill");
                int topping=rs.getInt("topping");
                BillDetailToppingModel banAn = new BillDetailToppingModel(id,bill,topping);
                list.add(banAn);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage() + "/n" + e.getClass() + "/n" + e.getCause());
        }
        return list;
    }

    public static ArrayList<BillDetailProductToppingModel> getByHero(int billid){

        ArrayList<BillDetailModel> ab=getByBillId(billid);

        if (ab.size() == 0){
            return new ArrayList<BillDetailProductToppingModel>(); 
        }

        ArrayList<ProductModel> ap=ProductController.getAll();
      
        ArrayList<BillDetailToppingModel> abdt=getAll();

        ArrayList<BillDetailProductToppingModel> abdpt=new ArrayList<BillDetailProductToppingModel>();

        for(BillDetailModel item : ab){
       
            ProductModel pm=ProductModel.getSpecificProduct(ap, item.getIdProduct());

            BillDetailToppingModel a=BillDetailToppingModel.getSpecificBillDetailTopping(abdt, item.getId());
            BillDetailProductToppingModel temp=null;
            if (a != null){

                ProductModel topping=ProductController.getByPk(a.getTopping());

                temp=new BillDetailProductToppingModel(
                    item.getId(), 
                    item.getIdProduct(), 
                    topping.getId(), 
                    item.getIdBill(), 
                    pm.getGia(), 
                    topping.getGia(), 
                    item.getAmount(), 
                    pm.getTen(), 
                    topping.getTen(),
                    pm.getHinhAnh(),
                    item.getPrice());
            }
            else{

                if (!ProductController.productServedTopping(pm.getId())){
                    temp=new BillDetailProductToppingModel(
                        item.getId(), 
                        item.getIdProduct(), 
                        -1, 
                        item.getIdBill(), 
                        pm.getGia(), 
                        -1, 
                        item.getAmount(), 
                        pm.getTen(), 
                        "",
                        pm.getHinhAnh(),
                        item.getPrice());
                    System.out.println(pm.getLoaiSp());
                }
                    
                else{
                    temp=new BillDetailProductToppingModel(
                        item.getId(), 
                        item.getIdProduct(), 
                        -1, 
                        item.getIdBill(), 
                        pm.getGia(), 
                        -1, 
                        item.getAmount(), 
                        pm.getTen(), 
                        "No Topping",
                        pm.getHinhAnh(),
                        item.getPrice());
                }
                    
            }
            abdpt.add(temp);
        }

        return abdpt;
    }


    public static boolean update(ArrayList<BillDetailProductToppingModel> list){

        Connection connection = null;
        try {
            connection = Startup.getConnection();

            for (BillDetailProductToppingModel item : list){

                String query = "update BillDetail set so_luong=? WHERE id=?";

                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(2, item.getIdBillDetail());
                ps.setInt(1, item.getAmount());
                ps.executeUpdate();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean add(ArrayList<BillDetailProductToppingModel> list){

        Connection connection = null;
        try {
            connection = Startup.getConnection();
            for (BillDetailProductToppingModel item : list){

                String query = "insert into BillDetail (bill,san_pham,gia,so_luong) values (?,?,?,?)";
                
                PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
                               
                ps.setInt(1, item.getIdBill());
                ps.setInt(2, item.getIdProduct());
                ps.setInt(3, item.getPrice());
                ps.setInt(4, item.getAmount());
                ps.executeUpdate();

                if(item.getIdTopping() > 0){

                    query="insert into BillDetail_Topping (bill,topping) values (?,?)";

                    PreparedStatement _ps=connection.prepareStatement(query);
                    ResultSet keys=ps.getGeneratedKeys();
                    keys.next();

                    _ps.setInt(1, keys.getInt(1));
                    _ps.setInt(2, item.getIdTopping());
                    _ps.executeUpdate();
                }
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
   public static boolean delete(int id){

        Connection connection = null;
        try {
            connection = Startup.getConnection();

            ArrayList<BillDetailModel> data=getByBillId(id);

            for(BillDetailModel item : data){

                String query = "delete from BillDetail_Topping where bill=?" ;

                PreparedStatement ps = connection.prepareStatement(query);

                ps.setInt(1, item.getId());

                ps.executeUpdate();
            }
            
            String query = "delete from BillDetail where bill=?" ;
        
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;            
        }

    }
}
