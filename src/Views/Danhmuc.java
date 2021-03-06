package Views;

import java.awt.Color;
import java.awt.Font;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Danhmuc extends JPanel {
	private JScrollPane scrollPane;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	ResultSetMetaData rsmd = null;
	Statement st = null;
	Vector vtCol = null;
	Vector vtData = null;
	private JTable table;
	private JTextField txtName;
	private JTextField txtSlug;

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
		String sql = "select * from ProductType where(1=1) order by id desc";
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

				vtRow.add(rs.getString("id"));
				vtRow.add(rs.getString("ten"));
				vtRow.add(rs.getString("slug"));

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

	public Danhmuc() {
		{
			setBackground(new Color(32, 178, 170));
			setLayout(null);

			JPanel panel = new JPanel();
			panel.setBackground(new Color(32, 178, 170));
			panel.setBounds(0, 0, 755, 59);
			add(panel);
			panel.setLayout(null);

			JTextField Txtsearch = new JTextField();
			JTextField jTextField = new JTextField();
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

			JButton Btnthem = new JButton("Th??m");
			Btnthem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Connection connection = null;
					try {

						connection = connect.getConnection();
						String query = "INSERT INTO ProductType VALUES(?,?)";
						PreparedStatement ps = connection.prepareStatement(query);

						ps.setString(1, txtName.getText());
						ps.setString(2, txtSlug.getText());

						ps.execute();
						JOptionPane.showMessageDialog(null, "saved");
						load();
					} catch (Exception e2) {
						System.out.printf(null, e);
					}
				}
			});
			Btnthem.setBounds(117, 361, 85, 21);
			panel_1.add(Btnthem);

			JButton Btnsua = new JButton("S???a");
			Btnsua.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Connection connection = null;

					int ret = JOptionPane.showConfirmDialog(null, "B???n c?? ch???c ch???c mu???n s???a!", "yes",
							JOptionPane.YES_NO_OPTION);
					if (ret != JOptionPane.YES_OPTION) {
						return;
					}
					try {

						DefaultTableModel d1 = (DefaultTableModel) table.getModel();
						int select = table.getSelectedRow();
						String ID = d1.getValueAt(select, 0).toString();
						connection = connect.getConnection();
						String query = "UPDATE  ProductType SET ten=?,slug=? WHERE id=?";

						PreparedStatement ps = connection.prepareStatement(query);

						ps.setString(1, txtName.getText());
						ps.setString(2, txtSlug.getText());

						ps.setString(3, ID);

						int k = ps.executeUpdate();

						JOptionPane.showMessageDialog(null, "???? s???a th??nh c??ng!");

					} catch (Exception e2) {

						JOptionPane.showMessageDialog(null, "H??y chon ID!");
					}
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.getDataVector();
					load();
				}
			});
			Btnsua.setBounds(117, 405, 85, 21);
			panel_1.add(Btnsua);

			JButton Btnxoa = new JButton("X??a");
			Btnxoa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int ix = table.getSelectedRow();
					int ret = JOptionPane.showConfirmDialog(null, "B???n c?? ch???c ch???c mu???n x??a!", "yes",
							JOptionPane.YES_NO_OPTION);
					if (ret != JOptionPane.YES_OPTION) {
						return;
					}
					try {

						conn = connect.getConnection();
						pst = conn.prepareStatement("Delete From ProductType where id= ?");
						TableModel model = table.getModel();
						String id = model.getValueAt(ix, 0).toString();
						pst.setString(1, id);
						ret = pst.executeUpdate();
						if (ret != -1) {
							JOptionPane.showMessageDialog(null, "Danh m???c ???? ???????c x??a!");
						}
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "H??y chon ID!");
					}
					load();
				}
			});
			Btnxoa.setBounds(10, 405, 85, 21);
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
					String sql = "select * from ProductType where(1=1) order by id desc";
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

							vtRow.add(rs.getString("id"));
							vtRow.add(rs.getString("ten"));
							vtRow.add(rs.getString("slug"));

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
			Btnload.setBounds(10, 361, 85, 21);
			panel_1.add(Btnload);

			JLabel label1 = new JLabel("Name");
			label1.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
			label1.setBounds(10, 54, 67, 13);
			panel_1.add(label1);

			txtName = new JTextField();
			txtName.setColumns(10);
			txtName.setBounds(87, 52, 96, 19);
			panel_1.add(txtName);

			txtSlug = new JTextField();
			txtSlug.setColumns(10);
			txtSlug.setBounds(87, 86, 96, 19);
			panel_1.add(txtSlug);

			JLabel label2 = new JLabel("Slug");
			label2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
			label2.setBounds(10, 88, 67, 13);
			panel_1.add(label2);

		}

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
				txtSlug.setText(d1.getValueAt(select, 2).toString());

			}
		});
		table.setBounds(10, 58, 541, 457);

		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		load();

	}
}
