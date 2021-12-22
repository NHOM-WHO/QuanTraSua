package Views;
import Startup.Startup;
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
import javax.swing.RowFilter;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.w3c.dom.html.HTMLTableCaptionElement;

import Models.Employees;

import java.awt.Font;
import java.awt.Image;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class Quanlytaikhoan extends JPanel {

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
	private JTextField txtSalary;
	private JTextField TxtSearch;

	public void load() {
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

			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
			table.setModel(new DefaultTableModel(vtData, vtCol) {

			});
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				conn.close();
				st.close();
				rs.close();
			} catch (Exception e3) {
				System.out.println(e3);
			}
		}
	}

	public void refresh() {
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

			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
			table.setModel(new DefaultTableModel(vtData, vtCol) {

			});
		} catch (SQLException ex) {
			System.err.printf(null, ex);
		} finally {
			try {
				conn.close();
				st.close();
				rs.close();
			} catch (Exception e3) {
				System.out.println(e3);
			}
		}
	}

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	/**
	 * 
	 */

	public Quanlytaikhoan() {
		setBackground(new Color(32, 178, 170));
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 755, 59);
		add(panel);
		panel.setLayout(null);
		
		TxtSearch = new JTextField();
		TxtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model= (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> tRowSorter =new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(tRowSorter);
				tRowSorter.setRowFilter(RowFilter.regexFilter(TxtSearch.getText().trim()));
			}
		});
		TxtSearch.setBounds(452, 10, 176, 19);
		panel.add(TxtSearch);
		TxtSearch.setColumns(10);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 178, 170));
		panel_1.setBounds(553, 57, 202, 458);
		add(panel_1);
		panel_1.setLayout(null);
		JComboBox cmbPermission = new JComboBox();
		cmbPermission.setModel(new DefaultComboBoxModel(new String[] {"employee", "manager"}));
		cmbPermission.setBounds(87, 196, 96, 21);
		panel_1.add(cmbPermission);
		
		JButton Btnthem = new JButton("Thêm");
		Btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {

					connection = connect.getConnection();
					String query = "INSERT INTO Employees VALUES(?,?,?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(query);

					ps.setString(1, txtUsername.getText());
					ps.setString(2, txtPassword.getText());
					ps.setString(3, txtName.getText());
					ps.setString(4, txtPhone.getText());
					String perString;
					perString = cmbPermission.getSelectedItem().toString();
					ps.setString(5, perString);
					ps.setString(6, txtSalary.getText());
					ps.executeUpdate();
					load();
					JOptionPane.showMessageDialog(null, "saved");

				} catch (Exception e2) {
					System.out.printf(null, e);
				}

			}
		});
		Btnthem.setBounds(117, 361, 85, 21);
		panel_1.add(Btnthem);

		JButton Btnsua = new JButton("Sửa");
		Btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;

				int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc muốn sửa!", "yes",
						JOptionPane.YES_NO_OPTION);
				if (ret != JOptionPane.YES_OPTION) {
					return;
				}
				try {
					DefaultTableModel d1 = (DefaultTableModel) table.getModel();
					int select = table.getSelectedRow();
					String ID = d1.getValueAt(select, 0).toString();

					connection = connect.getConnection();
					String query = "UPDATE  Employees SET USERNAME=?,PASSWORD=?,NAME=?,PHONENUMBER=?,PERMISSION=?,SALARY=? WHERE ID=?";

					PreparedStatement ps = connection.prepareStatement(query);

					ps.setString(1, txtUsername.getText());
					ps.setString(2, txtPassword.getText());
					ps.setString(3, txtName.getText());
					ps.setString(4, txtPhone.getText());
					String perString;
					perString = cmbPermission.getSelectedItem().toString();
					ps.setString(5, perString);
					ps.setString(6, txtSalary.getText());
					ps.setString(7, ID);

					int k = ps.executeUpdate();

					JOptionPane.showMessageDialog(null, "Đã sửa thành công!");

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Hãy chọn id!");

				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector();
				load();
			}
		});
		Btnsua.setBounds(117, 405, 85, 21);
		panel_1.add(Btnsua);

		JButton Btnxoa = new JButton("Xóa");
		Btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc muốn xóa!", "yes",
						JOptionPane.YES_NO_OPTION);
				if (ret != JOptionPane.YES_OPTION) {
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
					JOptionPane.showMessageDialog(null, "Hãy chọn id!");

				}
				load();
			}
		});
		Btnxoa.setBounds(10, 405, 85, 21);
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

					st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
					table.setModel(new DefaultTableModel(vtData, vtCol) {

					});
				} catch (SQLException ex) {
					System.err.printf(null, ex);
				} finally {
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
		btnNewButton.setBounds(10, 361, 85, 21);
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
		txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtUsername.setBounds(87, 44, 96, 19);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPassword.setColumns(10);
		txtPassword.setBounds(87, 78, 96, 19);
		panel_1.add(txtPassword);

		txtName = new JTextField();
		txtName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtName.setColumns(10);
		txtName.setBounds(87, 118, 96, 19);
		panel_1.add(txtName);

		txtPhone = new JTextField();
		txtPhone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtPhone.setColumns(10);
		txtPhone.setBounds(87, 159, 96, 19);
		panel_1.add(txtPhone);

		txtSalary = new JTextField();
		txtSalary.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		txtSalary.setColumns(10);
		txtSalary.setBounds(87, 236, 96, 19);
		panel_1.add(txtSalary);

		scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		scrollPane.setBounds(10, 58, 541, 457);
		add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int select = table.getSelectedRow();
				String ID = d1.getValueAt(select, 0).toString();
				txtUsername.setText(d1.getValueAt(select, 1).toString());
				txtPassword.setText(d1.getValueAt(select, 2).toString());
				txtName.setText(d1.getValueAt(select, 3).toString());
				txtPhone.setText(d1.getValueAt(select, 4).toString());
				String perS=d1.getValueAt(select, 5).toString();
				switch(perS) {
				case "employee":
					cmbPermission.setSelectedIndex(0);
					break;
				case "manager":
					cmbPermission.setSelectedIndex(1);
					break;
					
				}
				txtSalary.setText(d1.getValueAt(select, 6).toString());

			}
		});
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		load();
	}
}
