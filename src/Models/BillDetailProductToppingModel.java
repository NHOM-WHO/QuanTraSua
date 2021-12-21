
package Models;

public class BillDetailProductToppingModel {
    
    private int idBillDetail,idProduct,idTopping;
    private int idBill;
    private int priceProduct,priceTopping;
    private int price;
    private int amount;
    private String nameProduct,nameTopping,image;

    public BillDetailProductToppingModel(
        int _idBillDetail,
        int _idProduct,
        int _idTopping,
        int _idBill,
        int _priceProduct,
        int _priceTopping,
        int _amount,
        String _nameProduct,
        String _nameTopping,
        String _image,
        int _price
    ){
        idBillDetail=_idBillDetail;
        idProduct=_idProduct;
        idTopping=_idTopping;
        idBill=_idBill;
        priceProduct=_priceProduct;
        priceTopping=_priceTopping;
        amount=_amount;
        nameProduct=_nameProduct;
        nameTopping=_nameTopping;
        image=_image;
        price=_price;
    }

    public int getIdBillDetail(){return idBillDetail;}
    public int getIdProduct(){return idProduct;}
    public int getIdTopping(){return idTopping;}
    public int getIdBill(){return idBill;}
    public int getPriceProduct(){return priceProduct;}
    public int getPriceTopping(){return priceTopping;}
    public int getAmount(){return amount;}
    public String getNameProduct(){return nameProduct;}
    public String getNameTopping(){return nameTopping;}
    public String getImage(){return image;}
    public int getPrice(){return price;}

    public void setAmount(int _a){amount=_a;}
}
