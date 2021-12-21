
package Models;

public class BillDetailModel {
    
    private int id;
    private int idBill;
    private int idProduct;
    private int price;
    private int amount;

    public BillDetailModel(int _id,int _idBill,int _idProduct,int _price,int _amount){

        id=_id;
        idBill=_idBill;
        idProduct=_idProduct;
        price=_price;
        amount=_amount;
    }

    public int getId(){return id;}
    public int getIdBill(){return idBill;}
    public int getIdProduct(){return idProduct;}
    public int getPrice(){return price;}
    public int getAmount(){return amount;}

    
}
