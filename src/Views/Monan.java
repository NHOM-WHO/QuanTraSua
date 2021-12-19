package Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Viewspopup.FileTypeFilter;
import Viewspopup.Monan_crud;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.border.MatteBorder;

public class Monan extends JPanel {
	private File file;
	String filename = null;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	Statement st = null;
	Vector vtCol = null;
	Vector vtData = null;
	private JTable table;
	private JTextField txtName;
	private JTextField txtDescription;
	private JTextField txtUntilname;
	private JTextField txtPrice;
	private JTextField txtIdcate;

	private byte[] ConvertFile(String filename) {
		FileInputStream fileInputStream = null;
		File file = new File(filename);
		byte[] bFile = new byte[(int) file.length()];
		try {
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			bFile = null;
		}
		return bFile;
	}

	/**
	 * Create the panel.
	 */
	public void load() {
		Vector vtRow = null;
		vtCol = new Vector();
		vtData = new Vector();

		try {
			conn = connect.getConnection();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		String sql = "select * from Food_Item where(1=1) order by id desc";
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

				vtRow.add(rs.getInt("id"));
				vtRow.add(rs.getString("name"));
				vtRow.add(rs.getString("description"));
				vtRow.add(rs.getString("unitName"));
				vtRow.add(rs.getString("unitPrice"));
				vtRow.add(rs.getInt("idCategory"));
				vtRow.add(rs.getBytes("urlImage"));

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
				e3.printStackTrace();
			}
		}

	}

	public Monan() {
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
		Btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {

					connection = connect.getConnection();
//					String query = "INSERT INTO Food_Item SELECT  N'"+txtName.getText()+"',N'"+txtDescription.getText()+"',  N'"+txtUntilname.getText()+"',N'"+txtPrice.getText()+"',N'"+txtIdcate.getText()+"',BulkColumn FROM OPENROWSET(BULK N'"+anh+"', SINGLE_BLOB)  urlImage";
					String query = "INSERT INTO Food_Item VALUES(?,?,?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, txtName.getText());
					ps.setString(2, txtDescription.getText());
					ps.setString(3, txtUntilname.getText());
					ps.setString(4, txtPrice.getText());
					ps.setString(5, txtIdcate.getText());
					ps.setBytes(6, ConvertFile(file.getAbsolutePath()));
//					 
					ps.execute();
					JOptionPane.showMessageDialog(null, "saved");

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				load();

			}
		});
		Btnthem.setBounds(107, 360, 85, 21);
		panel_1.add(Btnthem);

		JButton Btnsua = new JButton("Sửa");
		Btnsua.setBounds(107, 391, 85, 21);
		panel_1.add(Btnsua);

		JButton Btnxoa = new JButton("Xóa");
		Btnxoa.setBounds(10, 391, 85, 21);
		panel_1.add(Btnxoa);

		JButton Btnload = new JButton("Load");
		Btnload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector vtRow = null;
				vtCol = new Vector();
				vtData = new Vector();

				try {
					conn = connect.getConnection();
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				String sql = "select * from Food_Item where(1=1) order by id desc";
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

						vtRow.add(rs.getInt("id"));
						vtRow.add(rs.getString("name"));
						vtRow.add(rs.getString("description"));
						vtRow.add(rs.getString("unitName"));
						vtRow.add(rs.getString("unitPrice"));
						vtRow.add(rs.getInt("idCategory"));
						vtRow.add(rs.getBytes("urlImage"));

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
						e3.printStackTrace();
					}
				}
				
			}
		});
		Btnload.setBounds(10, 360, 85, 21);
		panel_1.add(Btnload);

		txtName = new JTextField();
		txtName.setBounds(76, 40, 96, 19);
		panel_1.add(txtName);
		txtName.setColumns(10);

		txtDescription = new JTextField();
		txtDescription.setColumns(10);
		txtDescription.setBounds(76, 69, 96, 19);
		panel_1.add(txtDescription);

		txtUntilname = new JTextField();
		txtUntilname.setColumns(10);
		txtUntilname.setBounds(76, 99, 96, 19);
		panel_1.add(txtUntilname);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(76, 128, 96, 19);
		panel_1.add(txtPrice);

		txtIdcate = new JTextField();
		txtIdcate.setColumns(10);
		txtIdcate.setBounds(76, 155, 96, 19);
		panel_1.add(txtIdcate);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 43, 45, 13);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Description");
		lblNewLabel_1.setBounds(10, 72, 45, 13);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("UnitName");
		lblNewLabel_2.setBounds(10, 102, 45, 13);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Unitprice");
		lblNewLabel_3.setBounds(10, 131, 45, 13);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Idcate");
		lblNewLabel_4.setBounds(10, 158, 45, 13);
		panel_1.add(lblNewLabel_4);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_1.setBounds(10, 184, 99, 120);
		panel_1.add(lblNewLabel_2_1);

		JButton btnChoose = new JButton("Choose");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser = new JFileChooser();
				jFileChooser.setDialogTitle("ADD IMAGE");
				jFileChooser.setMultiSelectionEnabled(false);
				jFileChooser.setFileFilter(new FileTypeFilter(".jpg", "JPG"));
				jFileChooser.setFileFilter(new FileTypeFilter(".gif", "GIF"));
				jFileChooser.setFileFilter(new FileTypeFilter(".png", "PNG"));
				int result = jFileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					file = jFileChooser.getSelectedFile();
					filename = file.getAbsolutePath();

					ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(
							lblNewLabel_2_1.getWidth(), lblNewLabel_2_1.getHeight(), Image.SCALE_SMOOTH));
					lblNewLabel_2_1.setIcon(imageIcon);

				}
			}
		});
		btnChoose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChoose.setBounds(107, 239, 95, 21);
		panel_1.add(btnChoose);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 58, 541, 457);
		add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel d1 = (DefaultTableModel) table.getModel();
				int select = table.getSelectedRow();
				String ID = d1.getValueAt(select, 0).toString();
				txtName.setText(d1.getValueAt(select, 1).toString());
				txtDescription.setText(d1.getValueAt(select, 2).toString());
				txtUntilname.setText(d1.getValueAt(select, 3).toString());
				txtPrice.setText(d1.getValueAt(select, 4).toString());
				txtIdcate.setText(d1.getValueAt(select, 5).toString());
				lblNewLabel_2_1.setText(d1.getValueAt(select, 6).toString());
				
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(
						lblNewLabel_2_1.getWidth(), lblNewLabel_2_1.getHeight(), Image.SCALE_SMOOTH));
				lblNewLabel_2_1.setIcon(imageIcon);
			}
		});

		table.setRowHeight(100);
		table.setRowHeight(100);
		table.setBounds(10, 58, 541, 457);

		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		load();
	}

}
