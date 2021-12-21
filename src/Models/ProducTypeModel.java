
package Models;

public class ProducTypeModel {
    
    private int id;
    private String ten;
    private String slug;

    public ProducTypeModel(int i,String t, String s){
        id=i;
        ten=t;
        slug=s;
    }

    public int getId(){return id;}
    public String getTen(){return ten;};
    public String getSlug(){return slug;};
}
