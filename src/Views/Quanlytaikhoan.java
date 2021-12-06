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
import javax.swing.ListModel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.w3c.dom.html.HTMLTableCaptionElement;

import Models.Employees;
import Viewspopup.Manager_crud_employee;



import java.awt.Font;

import javax.management.modelmbean.ModelMBean;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.security.Identity;
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
import javax.swing.JLabel;

public class Quanlytaikhoan extends JPanel {
	private JTextField Txtsearch;
	private Manager_crud_employee crudEmployee;
	private Employees employees;
	private JTable table;
	private JScrollPane scrollPane;                                                                                                                                                                                                                                  
	Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    ResultSetMetaData rsmd = null;
    Statement st = null;
    Vector vtCol = null;
    Vector vtData = null;  
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtName;
    private JTextField txtPhone;
    private JTextField txtPermission;
    private JTextField txtSalary;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	/**
	 * 
	 */
   
    public  Quanlytaikhoan() {
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
		Btnthem.setBounds(117, 361, 85, 21);
		panel_1.add(Btnthem);

		JButton Btnsua = new JButton("Sửa");
		Btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection=null;
				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int select=table.getSelectedRow();
				String ID=d1.getValueAt(select, 0).toString();
					int ret = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắc muốn sửa!","yes",JOptionPane.YES_NO_OPTION);
					if(ret != JOptionPane.YES_OPTION) {
						 return ;
						}
				try {
					
					String connectionURL="jdbc:sqlserver://DESKTOP-RLD2Q4E\\CAOTHAI:1433;databaseName=QuanTraSua;integratedSecurity=true";
					connection=DriverManager.getConnection(connectionURL, "sa", "sa");
					String query="UPDATE  Employees SET USERNAME=?,PASSWORD=?,NAME=?,PHONENUMBER=?,PERMISSION=?,SALARY=? WHERE ID=?";
					 
					PreparedStatement ps=connection.prepareStatement(query);
					
					ps.setString(1, txtUsername.getText());
					ps.setString(2, txtPassword.getText());
					ps.setString(3, txtName.getText());
					ps.setString(4, txtPhone.getText());
					ps.setString(5, txtPermission.getText());
					ps.setString(6, txtSalary.getText());
					ps.setString(7, ID);
					
					int k =ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Đã sửa thành công!");
					
					
				} catch (Exception e2) {
					
					System.out.printf(null,e);
				}
			
			}
		});
		Btnsua.setBounds(10, 361, 85, 21);
		panel_1.add(Btnsua);

	
		JButton Btnxoa = new JButton("Xóa");
		Btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 int i = table.getSelectedRow();
				int ret = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắc muốn xóa!","yes",JOptionPane.YES_NO_OPTION);
				if(ret != JOptionPane.YES_OPTION) {
					 return;
					}
				try {
					
					conn = connect.getConnection();
					 pst = conn.prepareStatement("Delete From Employees where ID= ?");
					 TableModel model = table.getModel();
					 String id = model.getValueAt(i, 0).toString();
					 pst.setString(1, id);
					 ret = pst.executeUpdate();
					 if (ret != -1) {
						  JOptionPane.showMessageDialog(null, "Tài khoản đã được xóa!");  
						 }
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		Btnxoa.setBounds(117, 405, 85, 21);
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
		                
		                    vtRow.add(rs.getString("ID"));
		                    vtRow.add(rs.getString("USERNAME"));
		                    vtRow.add(rs.getString("PASSWORD"));
		                    vtRow.add(rs.getString("NAME"));
		                    vtRow.add(rs.getString("PHONENUMBER"));
		                    vtRow.add(rs.getString("PERMISSION"));
		                    vtRow.add(rs.getString("SALARY"));
		             
		                
		                vtData.add(vtRow);
		            }
		            table.setModel(new DefaultTableModel(vtData, vtCol){
		                
		                    
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
		btnNewButton.setBounds(10, 405, 85, 21);
		panel_1.add(btnNewButton);
		
		JLabel label1 = new JLabel("Username");
		label1.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label1.setBounds(10, 46, 67, 13);
		panel_1.add(label1);
		
		JLabel label2 = new JLabel("Password");
		label2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label2.setBounds(10, 80, 67, 13);
		panel_1.add(label2);
		
		JLabel label3 = new JLabel("Name");
		label3.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label3.setBounds(10, 120, 67, 13);
		panel_1.add(label3);
		
		JLabel label4 = new JLabel("Phone");
		label4.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label4.setBounds(10, 161, 67, 13);
		panel_1.add(label4);
		
		JLabel label5 = new JLabel("Permission");
		label5.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label5.setBounds(10, 199, 67, 13);
		panel_1.add(label5);
		
		JLabel label6 = new JLabel("Salary");
		label6.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		label6.setBounds(10, 238, 67, 13);
		panel_1.add(label6);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(87, 44, 96, 19);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(87, 78, 96, 19);
		panel_1.add(txtPassword);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(87, 118, 96, 19);
		panel_1.add(txtName);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(87, 159, 96, 19);
		panel_1.add(txtPhone);
		
		txtPermission = new JTextField();
		txtPermission.setColumns(10);
		txtPermission.setBounds(87, 197, 96, 19);
		panel_1.add(txtPermission);
		
		txtSalary = new JTextField();
		txtSalary.setColumns(10);
		txtSalary.setBounds(87, 236, 96, 19);
		panel_1.add(txtSalary);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(604, 5, 141, 21);
		panel.add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int select=table.getSelectedRow();
				String ID=d1.getValueAt(select, 0).toString();
				txtUsername.setText(d1.getValueAt(select, 1).toString());
				txtPassword.setText(d1.getValueAt(select, 2).toString());
				txtName.setText(d1.getValueAt(select, 3).toString());
				txtPhone.setText(d1.getValueAt(select, 4).toString());
				txtPermission.setText(d1.getValueAt(select, 5).toString());
				txtSalary.setText(d1.getValueAt(select, 6).toString());
			
				
				
			}
		});
		scrollPane.setBounds(10, 58, 541, 457);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
	}   
}
