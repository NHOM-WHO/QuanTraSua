
package Controls;
import java.lang.ModuleLayer.Controller;
import java.sql.*;
import java.util.*;

import Models.CustomerModel;
import Models.ShipCustomerModel;
import Models.ShipModel;
import Startup.Startup;
public class ShipCustomerController {
    
    public static ArrayList<ShipCustomerModel> getAll(){

        ArrayList<ShipCustomerModel> list=new ArrayList<ShipCustomerModel>();
        ArrayList<ShipModel> listShip=ShipController.getAll();
        ArrayList<CustomerModel> listCustomer=CustomerController.getAll();
        try {

            for (ShipModel sm : listShip){

                for (CustomerModel cm : listCustomer){

                    if(cm.getId() == sm.getCustomer()){

                        ShipCustomerModel mod=new ShipCustomerModel(
                            sm.getId(), 
                            sm.getBill(), 
                            sm.getCustomer(), 
                            sm.getGia(), 
                            sm.getShipper(), 
                            sm.getPhone(), 
                            sm.getStatus(), 
                            sm.getNgayBatDau(), 
                            sm.getNgayKetThuc(),
                            cm.getName(),
                            cm.getAddress()

                        );

                        list.add(mod);

                        break;
                    }
                }
                
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ShipCustomerModel getByPk(int id){

        ShipModel sm=ShipController.getByPk(id);

        CustomerModel cm=CustomerController.getByPk(sm.getCustomer());

        ShipCustomerModel mod=new ShipCustomerModel(
            sm.getId(), 
            sm.getBill(), 
            sm.getCustomer(), 
            sm.getGia(), 
            sm.getShipper(), 
            sm.getPhone(), 
            sm.getStatus(), 
            sm.getNgayBatDau(), 
            sm.getNgayKetThuc(),
            cm.getName(),
            cm.getAddress()

        );

        return mod;
    }
}

