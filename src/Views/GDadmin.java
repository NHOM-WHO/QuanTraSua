package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import java.awt.Panel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GDadmin extends JFrame {
	private Monan mn;
	private Danhmuc dm;
	private Quanlytaikhoan qltk;
	private JPanel contentPane;
	private thongke tkThongke;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GDadmin frame = new GDadmin();
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
	public GDadmin() {
		setTitle("Giao Diện Admin");
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setName("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JTabbedPane tabMain = new JTabbedPane(JTabbedPane.TOP);
		tabMain.setBounds(237, 5, 749, 611);
		contentPane.add(tabMain);

		Panel PanelLeft = new Panel();
		PanelLeft.setBounds(5, 5, 226, 611);
		PanelLeft.setPreferredSize(new Dimension(200, 10));
		contentPane.add(PanelLeft);
		PanelLeft.setLayout(null);

		Panel PanelHeader = new Panel();
		PanelHeader.setBackground(Color.PINK);
		PanelHeader.setBounds(0, 0, 220, 55);
		PanelHeader.setMinimumSize(new Dimension(500, 50));
		PanelLeft.add(PanelHeader);
		PanelHeader.setLayout(null);

		JLabel lblNewLabel = new JLabel("NGUYỄN CAO THÁI");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 5, 115, 40);
		lblNewLabel.setPreferredSize(new Dimension(150, 30));
		PanelHeader.add(lblNewLabel);

		JButton Btnlogout = new JButton("Thoát");
		Btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Btnlogout.setFont(new Font("Segoe UI", Font.BOLD, 10));
		Btnlogout.setBounds(128, 0, 92, 28);
		PanelHeader.add(Btnlogout);

		JButton btnngXut = new JButton("Đăng xuất");
		btnngXut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int ret = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắc muốn đăng xuất!", "Đăng xuất",
						JOptionPane.YES_NO_OPTION);
				if (ret != JOptionPane.YES_OPTION) {
					return;
				}
				GDdangnhap dnDdangnhap = new GDdangnhap();
				dnDdangnhap.setVisible(true);
				dispose();
			}
		});
		btnngXut.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnngXut.setBounds(128, 27, 92, 28);
		PanelHeader.add(btnngXut);

		JButton Btntaikhoan = new JButton("Quản lý tài khoản");
		Btntaikhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (qltk == null) {
					qltk = new Quanlytaikhoan();

					tabMain.addTab("Quản lý tài khoản", qltk);
				}
				tabMain.setSelectedComponent(qltk);

			}
		});
		Btntaikhoan.setBackground(Color.LIGHT_GRAY);
		Btntaikhoan.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Btntaikhoan.setBounds(0, 58, 220, 55);
		PanelLeft.add(Btntaikhoan);

		JButton Btnhanghoa = new JButton("Quản lý hàng hóa");
		Btnhanghoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dm == null && mn == null) {
					dm = new Danhmuc();
					mn = new Monan();
					tabMain.addTab("Danh mục", dm);
					tabMain.addTab("Món ăn", mn);

				}
				tabMain.setSelectedComponent(dm);
				tabMain.setSelectedComponent(mn);

			}
		});
		Btnhanghoa.setBackground(Color.LIGHT_GRAY);
		Btnhanghoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Btnhanghoa.setBounds(0, 116, 220, 50);
		PanelLeft.add(Btnhanghoa);

		JButton Btndathang = new JButton("Quản lý đặt hàng");
		Btndathang.setBackground(Color.LIGHT_GRAY);
		Btndathang.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Btndathang.setBounds(0, 168, 220, 50);
		PanelLeft.add(Btndathang);

		JButton Btnthongkee = new JButton("Thống kê");
		Btnthongkee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tkThongke == null) {
					tkThongke = new thongke();

					tabMain.addTab("Thống kê", tkThongke);
				}
				tabMain.setSelectedComponent(tkThongke);

			}
		});
		Btnthongkee.setBackground(Color.LIGHT_GRAY);
		Btnthongkee.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Btnthongkee.setBounds(0, 220, 220, 50);
		PanelLeft.add(Btnthongkee);

		JButton Btnthietlap = new JButton("Thiết lập");
		Btnthietlap.setBackground(Color.LIGHT_GRAY);
		Btnthietlap.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Btnthietlap.setBounds(0, 273, 220, 50);
		PanelLeft.add(Btnthietlap);

	}
}
