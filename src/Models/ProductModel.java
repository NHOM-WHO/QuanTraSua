
package Models;
import java.util.*;
public class ProductModel {
    
    private int id;
    private String ten;
    private int loai_sp;
    private int gia;
    private String don_vi;
    private String hinh_anh;

    public ProductModel(int i,String t,int l, int g, String d,String h){

        id=i;
        ten=t;
        loai_sp=l;
        gia=g;
        don_vi=d;
        hinh_anh=h;
    }

    public int getId(){return id;}
    public String getTen(){return ten;}
    public int getLoaiSp(){return loai_sp;}
    public int getGia(){return gia;}
    public String getDonVi(){return don_vi;}
    public String getHinhAnh(){return hinh_anh;} 

    public static ProductModel getSpecificProduct(ArrayList<ProductModel> ap,int id){

        for(ProductModel item : ap){
            if(item.getId() == id){

                return item;
            }

        }

        return null;
    }
}
