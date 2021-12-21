
package Models;

import java.util.ArrayList;

public class BillDetailToppingModel {
    
    private int id,billDetailId,topping;

    public BillDetailToppingModel(int i,int b,int t){
        id=i;
        billDetailId=b;
        topping=t;
    }

    public int getId(){return id;}
    public int getBillDetailID(){return billDetailId;}
    public int getTopping(){return topping;}

    public static BillDetailToppingModel getSpecificBillDetailTopping(ArrayList<BillDetailToppingModel> abdt,int id){


        for(BillDetailToppingModel item :abdt){
            if(item.getBillDetailID() == id){
                return item;
            }
        }
        return null;
    }
}
