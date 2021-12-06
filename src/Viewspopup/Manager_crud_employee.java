package Viewspopup;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.JavaBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

import Views.GDadmin;
import Views.Quanlytaikhoan;
import Views.connect;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Manager_crud_employee extends JFrame {

	private JPanel contentPane;
	Connection connection =null;
	String connectionURL="jdbc:sqlserver://DESKTOP-RLD2Q4E\\CAOTHAI:1433;databaseName=QuanTraSua;integratedSecurity=true";
	PreparedStatement pst=null;
	ResultSet rs=null;
	int q,i,id,deleteItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager_crud_employee frame = new Manager_crud_employee();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public  Manager_crud_employee() {
		
		setBounds(100, 100, 481, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setBounds(49, 5, 382, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thêm nhân viên");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblNewLabel.setBounds(112, 10, 153, 33);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(49, 64, 421, 315);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 35, 69, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 80, 69, 13);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(10, 125, 69, 13);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("PhoneNumber");
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(10, 170, 94, 13);
		panel_1.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Permission");
		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(10, 215, 83, 13);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Salary");
		lblNewLabel_1_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_5.setBounds(10, 260, 83, 13);
		panel_1.add(lblNewLabel_1_5);
		
		JTextPane txtUsername = new JTextPane();
		txtUsername.setBounds(97, 29, 185, 19);
		panel_1.add(txtUsername);
		
		JTextPane txtPassword = new JTextPane();
		txtPassword.setBounds(97, 74, 185, 19);
		panel_1.add(txtPassword);
		
		JTextPane txtName = new JTextPane();
		txtName.setBounds(97, 119, 185, 19);
		panel_1.add(txtName);
		
		JTextPane txtPhone = new JTextPane();
		txtPhone.setBounds(97, 164, 185, 19);
		panel_1.add(txtPhone);
		
		JTextPane txtPermission = new JTextPane();
		txtPermission.setBounds(97, 209, 185, 19);
		panel_1.add(txtPermission);
		
		JTextPane txtSalary = new JTextPane();
		txtSalary.setBounds(97, 254, 185, 19);
		panel_1.add(txtSalary);
		
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection=null;
				try {
				
					String connectionURL="jdbc:sqlserver://DESKTOP-RLD2Q4E\\CAOTHAI:1433;databaseName=QuanTraSua;integratedSecurity=true";
					connection=DriverManager.getConnection(connectionURL, "sa", "sa");
					String query="INSERT INTO Employees VALUES(?,?,?,?,?,?)";
					PreparedStatement ps=connection.prepareStatement(query);
					
					ps.setString(1, txtUsername.getText());
					ps.setString(2, txtPassword.getText());
					ps.setString(3, txtName.getText());
					ps.setString(4, txtPhone.getText());
					ps.setString(5, txtPermission.getText());
					ps.setString(6, txtSalary.getText());
					ps.execute();
					JOptionPane.showMessageDialog(null, "saved");
					dispose();
				} catch (Exception e2) {
					System.out.printf(null,e);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnNewButton.setBounds(203, 405, 85, 21);
		contentPane.add(btnNewButton);
		
	}

	
}
