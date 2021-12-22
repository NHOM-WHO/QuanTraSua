
package Models;

public class CustomerModel {
    private int id;
    private String name;
    private String phone;
    private String address;
    private String ngay_sinh;

    public CustomerModel(int i,String n,String p,String a,String _ngay_sinh){

        id=i;
        name=n;
        phone=p;
        address=a;
        ngay_sinh=_ngay_sinh;
    }

    public int getId(){return id;}
    public String getName(){return name;}
    public String getPhone(){return phone;}
    public String getAddress(){return address;}
    public String getNgaySinh(){return ngay_sinh;}
}
