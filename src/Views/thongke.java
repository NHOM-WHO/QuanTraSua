package Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controls.BillController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import Models.BillModel;

public class thongke extends JPanel {
	/**
	
	 */
	
	private final JTable table = new JTable();
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	Statement st = null;
	Vector vtCol = null;
	Vector vtData = null;
	JLabel lbTong;
	/**
	 * Create the panel.
	 */

	private JTable table_2;

	public thongke() {
		
		setBackground(new Color(32, 178, 170));
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 877, 59);
		add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Thống Kê Doanh Thu");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(332, 10, 186, 39);
		panel.add(lblNewLabel);

		JButton Btnsua = new JButton("Thống Kê");
		Btnsua.setBounds(30, 69, 85, 21);
		add(Btnsua);

		JButton Btnload = new JButton("Thoát");
		Btnload.setBounds(30, 100, 85, 21);
		add(Btnload);

		Label lbTong = new Label("Tông Doanh Thu: ");
		lbTong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbTong.setBounds(366, 65, 511, 56);
		add(lbTong);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 155, 468, 303);
		add(scrollPane);

		table_2 = new JTable();
		scrollPane.setViewportView(table_2);

		table_2.setAutoCreateRowSorter(true);
		showTable();
		
		Btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat x=new DecimalFormat("###,###,###");
				int Tong = 0;
				for(int i = 0; i<table_2.getRowCount();i++) {
					Tong = Tong + Integer.parseInt(table_2.getValueAt(i,2).toString());
					lbTong.setText("Tông Doanh Thu: "+" "+ x.format(Tong)+" "+"VND");
				}
			}
		});
	}
	public void showTable() {
		Vector vtRow = null;
		vtCol = new Vector();
		vtData = new Vector();

		try {
			conn = connect.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		String sql = "select * from Bill where(1=1) order by ID desc";
		try {

			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery(sql);
			rsmd = rs.getMetaData();
			
				vtCol.add(rsmd.getColumnName(1));
				vtCol.add(rsmd.getColumnName(7));
				vtCol.add(rsmd.getColumnName(9));
				
			
			rs.afterLast();
			while (rs.previous()) {
				vtRow = new Vector();

				vtRow.add(rs.getString("id"));
				vtRow.add(rs.getString("ngaythanh_toan"));
				vtRow.add(rs.getString("da_thanhtoan"));

				vtData.add(vtRow);
			}
			table_2.setModel(new DefaultTableModel(vtData, vtCol) {

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
	public void Tong() {
		DecimalFormat x=new DecimalFormat("###,###,###");
		int Tong = 0;
		for(int i = 0; i<table_2.getRowCount();i++) {
			Tong = Tong + Integer.parseInt(table_2.getValueAt(i,2).toString());
			lbTong.setText("Tông Doanh Thu: "+" "+ x.format(Tong)+" "+"VND");
		}
		
		
	}
}