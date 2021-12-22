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
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;

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
	private JTextField txtUntilname;
	private JTextField txtPrice;

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
		String sql = "select * from Product where(1=1) order by id desc";
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
				vtRow.add(rs.getString("ten"));
				vtRow.add(rs.getString("loai_sp"));
				vtRow.add(rs.getString("gia"));
				vtRow.add(rs.getString("don_vi"));
				vtRow.add(rs.getString("hinh_anh"));

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
		Txtsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				TableRowSorter<DefaultTableModel> tRowSorter = new TableRowSorter<DefaultTableModel>(model);
				table.setRowSorter(tRowSorter);
				tRowSorter.setRowFilter(RowFilter.regexFilter(Txtsearch.getText().trim()));
			}
		});
		Txtsearch.setForeground(new Color(169, 169, 169));
		Txtsearch.setFont(new Font("Tahoma", Font.PLAIN, 12));

		Txtsearch.setBounds(366, 6, 176, 19);
		panel.add(Txtsearch);
		Txtsearch.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 178, 170));
		panel_1.setBounds(553, 57, 202, 458);
		add(panel_1);
		panel_1.setLayout(null);
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
		comboBox.setBounds(76, 68, 96, 19);
		panel_1.add(comboBox);
		JButton Btnthem = new JButton("Thêm");
		Btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection = null;
				try {

					connection = connect.getConnection();
					String query = "INSERT INTO Product VALUES(?,?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, txtName.getText());
					String perString;
					perString = comboBox.getSelectedItem().toString();
					ps.setString(2, perString);
					ps.setString(3, txtUntilname.getText());
					ps.setString(4, txtPrice.getText());
					ps.setString(5, file.getAbsolutePath());
//					 
					ps.executeUpdate();
					load();
					JOptionPane.showMessageDialog(null, "saved");

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				load();

			}
		});
		Btnthem.setBounds(107, 396, 85, 21);
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
					String query = "UPDATE  Product SET ten=?,loai_sp=?,gia=?,don_vi=?,hinh_anh=? WHERE id=?";

					PreparedStatement ps = connection.prepareStatement(query);

					ps.setString(1, txtName.getText());
					String perString;
					perString = comboBox.getSelectedItem().toString();
					ps.setString(2, perString);
					ps.setString(3, txtUntilname.getText());
					ps.setString(4, txtPrice.getText());
					ps.setString(5, file.getAbsolutePath());
					ps.setString(6, ID);

					int k = ps.executeUpdate();

					JOptionPane.showMessageDialog(null, "Đã sửa thành công!");

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Hãy chọn ảnh!");

				}
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.getDataVector();
				load();

			}
		});
		Btnsua.setBounds(107, 427, 85, 21);
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
					pst = conn.prepareStatement("Delete From Product where id= ?");
					TableModel model = table.getModel();
					String ID = model.getValueAt(i, 0).toString();
					pst.setString(1, ID);
					ret = pst.executeUpdate();
					load();
					if (ret != -1) {
						JOptionPane.showMessageDialog(null, "Item đã được xóa!");
					}

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Hãy chọn id!");

				}

			}
		});
		Btnxoa.setBounds(10, 427, 85, 21);
		panel_1.add(Btnxoa);

		txtName = new JTextField();
		txtName.setBounds(76, 40, 96, 19);
		panel_1.add(txtName);
		txtName.setColumns(10);

		txtUntilname = new JTextField();
		txtUntilname.setColumns(10);
		txtUntilname.setBounds(76, 99, 96, 19);
		panel_1.add(txtUntilname);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(76, 128, 96, 19);
		panel_1.add(txtPrice);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(10, 43, 45, 13);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Type");
		lblNewLabel_1.setBounds(10, 72, 45, 13);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Price");
		lblNewLabel_2.setBounds(10, 102, 45, 13);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Đơn vị");
		lblNewLabel_3.setBounds(10, 131, 45, 13);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblNewLabel_2_1.setBounds(10, 184, 170, 171);
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
				String sql = "select * from Product where(1=1) order by id desc";
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
						vtRow.add(rs.getString("ten"));
						vtRow.add(rs.getString("loai_sp"));
						vtRow.add(rs.getString("gia"));
						vtRow.add(rs.getString("don_vi"));
						vtRow.add(rs.getString("hinh_anh"));

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
		Btnload.setBounds(10, 396, 85, 21);
		panel_1.add(Btnload);
		btnChoose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChoose.setBounds(50, 365, 95, 21);
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
				String perS = d1.getValueAt(select, 2).toString();
				switch (perS) {
				case "1":
					comboBox.setSelectedIndex(0);
					break;
				case "2":
					comboBox.setSelectedIndex(1);
					break;
				case "3":
					comboBox.setSelectedIndex(2);
					break;
				case "4":
					comboBox.setSelectedIndex(3);
					break;
				}
				txtUntilname.setText(d1.getValueAt(select, 3).toString());
				txtPrice.setText(d1.getValueAt(select, 4).toString());

				String img = (String) d1.getValueAt(select, 5);
				ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(
						lblNewLabel_2_1.getWidth(), lblNewLabel_2_1.getHeight(), Image.SCALE_SMOOTH));
				lblNewLabel_2_1.setIcon(imageIcon);
			}
		});

		table.setBounds(10, 58, 541, 457);

		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		load();

	}
}
