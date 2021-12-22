
package Views;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;
import java.awt.event.*;


import Viewspopup.Ship.*;
import Controls.ShipController;
import Controls.ShipCustomerController;
import Models.ShipCustomerModel;

public class Ship extends JPanel implements ActionListener {

    private JTable table;
    private DefaultTableModel tb;
    private String[] columnNames = { "Mã HD", "Tên KH", "Địa chỉ", "Tên Shipper", "SDT shipper","Giá ship","Trạng thái","Ngày bắt đầu","Ngày kết thúc" };
    private Integer[][] myData;
    /**
     * Create the panel.
     */

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Add")) {
            new AddShipView();
        } else if (e.getActionCommand().equals("Update")) {
            try {
                int row = table.getSelectedRow();

                int id=myData[row][0];
                ShipCustomerModel scm=ShipCustomerController.getByPk(id);
                new UpdateShipView(scm);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Hãy chọn một hàng");
            }
        } else if (e.getActionCommand().equals("Delete")) {
            try {
                int row = table.getSelectedRow();

                int id=myData[row][0];
                ShipController.delete(id);
                JOptionPane.showMessageDialog(this, "Thành công");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Hãy chọn một hàng");
            }
        } else if (e.getActionCommand().equals("Load")) {

            try {

                tb.setRowCount(0);
                tb = buildTableModel();

                table.setModel(tb);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public DefaultTableModel buildTableModel() {

        ArrayList<ShipCustomerModel> bList = ShipCustomerController.getAll();

        Vector<String> columnName = new Vector<String>();

        for (int column = 0; column < columnNames.length; column++) {
            columnName.add(columnNames[column]);
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        
        myData=new Integer[bList.size()][1];
        
        for (int i = 0; i < bList.size(); ++i) {
            Vector<Object> vector = new Vector<Object>();

            vector.add(Integer.toString(bList.get(i).getBill()));
            vector.add(bList.get(i).getCustomerName());
            vector.add(bList.get(i).getAddress());
            vector.add(bList.get(i).getShipper());
            vector.add(bList.get(i).getPhone());
            vector.add(Integer.toString(bList.get(i).getGia()));
            vector.add(bList.get(i).getStatus());
            vector.add(bList.get(i).getNgayBatDau());
            vector.add(bList.get(i).getNgayKetThuc());

            myData[i][0]=bList.get(i).getId();

            data.add(vector);
        }

        return new DefaultTableModel(data, columnName);

    }

    public Ship() {
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

        try {

            tb = buildTableModel();

            table = new JTable(tb);
            table.setBounds(10, 57, 545, 460);
            table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(table);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
