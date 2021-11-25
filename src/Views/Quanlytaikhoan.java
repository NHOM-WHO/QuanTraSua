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

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
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

public class Quanlytaikhoan extends JPanel {
	private JTextField Txtsearch;
	private JTable table;
	private Manager_crud_employee crudEmployee;
	private Employees employees;
	
//	Connection connection =null;
//	String connectionURL="jdbc:sqlserver://DESKTOP-RLD2Q4E\\CAOTHAI:1433;databaseName=QuanTraSua;integratedSecurity=true";
//	PreparedStatement pst=null;
//	ResultSet rs=null;
//	int q,i,id,deleteItem;
//	
//	public void updateDB() {
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			connection=DriverManager.getConnection(connectionURL, "sa", "sa");
//			pst =connection.prepareStatement("SELECT * FROM Employees");
//			rs =pst.executeQuery();
//			ResultSetMetaData stData= rs.getMetaData();
//			q = stData.getColumnCount();
//			DefaultTableModel RecordTable = (DefaultTableModel) table.getModel();
//			RecordTable.setRowCount(0);
//			while(rs.next()) {
//				Vector columnData =new Vector();
//				for(int i = 1;i<=q;i++) {
//					columnData.add(rs.getShort("id"));
//					columnData.add(rs.getShort("ID"));
//					columnData.add(rs.getShort("USERNAME"));
//					columnData.add(rs.getShort("PASSWORD"));
//					columnData.add(rs.getShort("NAME"));
//					columnData.add(rs.getShort("PHONENUMBER"));
//					columnData.add(rs.getShort("PERMISSION"));
//					columnData.add(rs.getShort("SALARY"));
//				}
//				RecordTable.addRow(columnData);
//			}
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e);
//		}
//		
//	}
//	
//	public ArrayList<Employees> EmployeeList(){
//		ArrayList<Employees> EmployeeList = new ArrayList<>();
//		Connection connection =null;
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			String connectionURL="jdbc:sqlserver://DESKTOP-RLD2Q4E\\CAOTHAI:1433;databaseName=QuanTraSua;integratedSecurity=true";
//			connection=DriverManager.getConnection(connectionURL, "sa", "sa");
//			String queryString= "SELECT * FROM Employees";
//			Statement st=connection.createStatement();
//			ResultSet rs=st.executeQuery(queryString);
//			Employees empl;
//			while(rs.next()) {
//				empl= new Employees(rs.getInt("ID"),rs.getString("username"), rs.getString("password"),rs.getString("name"),rs.getString("phone"),rs.getString("permission"),rs.getString("salary"));
//				EmployeeList.add(empl);
//			}
//		} catch (Exception e) {
//			
//		}
//		return EmployeeList;
//		
//	}
	
//	
//	public void show() {
//		ArrayList<Employees> list =EmployeeList();
//		DefaultTableModel model = (DefaultTableModel) table.getModel();
//		Object[] row = new Object[7];
//		for(int i=0;i<list.size();i++) {
//			row[0]=list.get(i).getID();
//			row[1]=list.get(i).getUsername();
//			row[2]=list.get(i).getPassword();
//			row[3]=list.get(i).getName();
//			row[4]=list.get(i).getPhone();
//			row[5]=list.get(i).getPermission();
//			row[6]=list.get(i).getSalary();
//			model.addRow(row);
//			
//		}
//	}
//    
	 
	/**
	 * Create the panel.
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
		Btnsua.setBounds(69, 209, 85, 21);
		panel_1.add(Btnsua);

		JButton Btnxoa = new JButton("Xóa");

		Btnxoa.setBounds(69, 162, 85, 21);
		panel_1.add(Btnxoa);

		JButton Btnload = new JButton("Load");
		Btnload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		Btnload.setBounds(69, 258, 85, 21);
		panel_1.add(Btnload);

		JComboBox<Employees> comboBox = new JComboBox<Employees>();
		comboBox.setBounds(604, 5, 141, 21);
		panel.add(comboBox);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 57, 545, 460);
		add(scrollPane);
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
				
			new Object[][] {
				
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "USERNAME", "PASSWORD", "NAME", "PHONE", "PERMISSION", "SALARY"
			}
		));
		scrollPane.setViewportView(table);
		
	}
}
