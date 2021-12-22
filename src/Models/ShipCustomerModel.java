

package Models;


public class ShipCustomerModel{

    private int id,bill,customer,gia;
    private String shipper,phone,status,ngay_batdau,ngay_ketthuc,customerName,address;

    public ShipCustomerModel(
        int _id,
        int _bill,
        int _customer,
        int _gia,
        String _shipper,
        String _phone,
        String _status,
        String _ngay_batdau,
        String _ngay_ketthuc,
        String _customerName,
        String _address
    ){

        id=_id;
        bill=_bill;
        customer=_customer;
        gia=_gia;
        shipper=_shipper;
        phone=_phone;
        status=_status;
        ngay_batdau=_ngay_batdau;
        ngay_ketthuc=_ngay_ketthuc;
        customerName=_customerName;
        address=_address;
    }

    public int getId(){return id;}
    public int getBill(){return bill;}
    public int getCustomer(){return customer;}
    public int getGia(){return gia;}
    public String getShipper(){return shipper;}
    public String getPhone(){return phone;}
    public String getStatus(){return status;}
    public String getNgayBatDau(){return ngay_batdau;}
    public String getNgayKetThuc(){return ngay_ketthuc;}
    public String getCustomerName(){return customerName;}
    public String getAddress(){return address;}
}