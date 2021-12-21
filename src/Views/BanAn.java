
package Views;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;

import Viewspopup.Table.*;
import Controls.BanAnController;

public class BanAn extends JPanel implements ActionListener {


    private JTable table;
    private DefaultTableModel tb;

    /**
     * Create the panel.
     */

    public void actionPerformed(ActionEvent e){
        
        if (e.getActionCommand().equals("Add")){
            Add ad=new Add();
            ad.setVisible(true);
        }
        else if(e.getActionCommand().equals("Update")){
            Update ad=new Update();
            ad.setVisible(true);
        }
        else if(e.getActionCommand().equals("Delete")){
            Delete ad=new Delete();
            ad.setVisible(true);
        }
        else if(e.getActionCommand().equals("Load")){
     
            tb.setRowCount(0);

            try{
               
                ResultSet rs = BanAnController.getAllResultSet();
                tb.setRowCount(0);
                tb= buildTableModel(rs);
                
                table.setModel(tb);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            System.out.println(metaData.getColumnName(column));
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);

    }

    public BanAn() {
        setBackground(new Color(32, 178, 170));
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(32, 178, 170));
        panel.setBounds(0, 0, 755, 59);
        add(panel);
        panel.setLayout(null);

        JTextField Txtsearch = new JTextField();
        Txtsearch.setForeground(new Color(169, 169, 169));
        Txtsearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
        Txtsearch.setText("search mon an");
        Txtsearch.setBounds(366, 6, 176, 19);
        panel.add(Txtsearch);
        Txtsearch.setColumns(10);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(604, 5, 141, 21);
        panel.add(comboBox);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(32, 178, 170));
        panel_1.setBounds(553, 57, 202, 458);
        add(panel_1);
        panel_1.setLayout(null);

        JButton Btnthem = new JButton("Thêm");
        Btnthem.setBounds(69, 110, 85, 21);
        Btnthem.addActionListener(this);
        Btnthem.setActionCommand("Add");
        panel_1.add(Btnthem);


        JButton Btnsua = new JButton("Sửa");
        Btnsua.setBounds(69, 209, 85, 21);
        Btnsua.addActionListener(this);
        Btnsua.setActionCommand("Update");
        panel_1.add(Btnsua);

        JButton Btnxoa = new JButton("Xóa");
        Btnxoa.setBounds(69, 162, 85, 21);
        Btnxoa.addActionListener(this);
        Btnxoa.setActionCommand("Delete");
        panel_1.add(Btnxoa);

        JButton Btnload = new JButton("Load");
        Btnload.setBounds(69, 258, 85, 21);
        Btnload.addActionListener(this);
        Btnload.setActionCommand("Load");
        panel_1.add(Btnload);

        
        try{
        
            ResultSet rs = BanAnController.getAllResultSet();
            tb= buildTableModel(rs);
   
            table = new JTable(tb);
            table.setBounds(10, 57, 545, 460);
            add(table);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    
    }
}
