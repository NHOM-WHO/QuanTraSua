package Views;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import Models.Employees;
import Viewspopup.Manager_crud_employee;
import Viewspopup.Update_employee;

import java.awt.Font;

import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Quanlytaikhoan extends JPanel {
	private JTextField Txtsearch;
	private Manager_crud_employee crudEmployee;
	private Employees employees;
	private JTable table;
	private JScrollPane scrollPane;
	private String header[] = {"ID", "USERNAME", "PASSWORD"};
	Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    Statement st = null;
    Vector vtCol = null;
    Vector vtData = null;


	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public Quanlytaikhoan() {
	
	     
		setBackground(new Color(32, 178, 170));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 755, 59);
		add(panel);
		panel.setLayout(null);

		Txtsearch = new JTextField();
		Txtsearch.setForeground(new Color(169, 169, 169));
		Txtsearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Txtsearch.setText("search");
		Txtsearch.setBounds(366, 6, 176, 19);
		panel.add(Txtsearch);
		Txtsearch.setColumns(10);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 178, 170));
		panel_1.setBounds(553, 57, 202, 458);
		add(panel_1);
		panel_1.setLayout(null);

		JButton Btnthem = new JButton("Thêm");
		Btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager_crud_employee cd = new Manager_crud_employee();
				cd.setVisible(true);

			}
		});
		Btnthem.setBounds(69, 110, 85, 21);
		panel_1.add(Btnthem);

		JButton Btnsua = new JButton("Sửa");
		Btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_employee update_employee=new Update_employee();
				update_employee.setVisible(true);
			}
		});
		Btnsua.setBounds(69, 209, 85, 21);
		panel_1.add(Btnsua);

		JButton Btnxoa = new JButton("Xóa");
		Btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ret = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắc muốn xóa!","yes",JOptionPane.YES_NO_OPTION);
				if(ret != JOptionPane.YES_OPTION) {
					 return;
					}
				try {
					conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-RLD2Q4E\\\\CAOTHAI:1433;databaseName=QuanTraSua;integratedSecurity=true");
					 pst = conn.prepareStatement("Delete From Employees where id = ?");
					 ret = pst.executeUpdate();
					 if (ret != -1) {
						  JOptionPane.showMessageDialog(null, "This book has been deleted");  
						 }
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		Btnxoa.setBounds(69, 162, 85, 21);
		panel_1.add(Btnxoa);
		
		
		JButton btnNewButton = new JButton("Load");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector vtRow = null;
				vtCol = new Vector();
		        vtData = new Vector();
		        try {
					conn = connect.getConnection();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
		        String sql = "select * from Employees where(1=1) order by ID desc";
		        try {
		            st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		            rs = st.executeQuery(sql);
		            rsmd = rs.getMetaData();
		            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
		                vtCol.add(rsmd.getColumnName(i));
		            }
		            rs.afterLast();
		            while (rs.previous()) {               
		                vtRow = new Vector();
		                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
		                    vtRow.add(rs.getString(i));
		                }
		                vtData.add(vtRow);
		            }
		            table.setModel(new DefaultTableModel(vtData, vtCol){
		                public boolean EditCustomer(int col,int row){
		                    return false;
		                }
		            });
		        } catch (SQLException ex) {
		           System.err.printf(null,ex);
		        }finally{
		            try {
		                conn.close();
		                st.close();
		                rs.close();
		            } catch (Exception e3) {
		                System.out.println(e3);
		            }
		        }
		   
		        
			}
		});
		btnNewButton.setBounds(69, 254, 85, 21);
		panel_1.add(btnNewButton);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(604, 5, 141, 21);
		panel.add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 541, 457);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}   
	
}
