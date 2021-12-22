
package Models;


public class BanAn {
    
    private int Id;
    private String Name;
    private String Status;

    public BanAn(int _id,String _name, String _status){
        Id=_id;
        Name=_name;
        Status=_status;
    }

    public BanAn(){
        Id=-1;
        Name=Status="";
    }

    public BanAn(BanAn other){
        Id=other.Id;
        Name=other.Name;
        Status=other.Status;
    }

    public BanAn(String _name,String _status){
        Id=-1;
        Name=_name;
        Status=_status;
    }

    public int getId(){return Id;}
    public String getName(){return Name;}
    public String getStatus(){return Status;}
}
