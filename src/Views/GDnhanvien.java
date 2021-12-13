package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class GDnhanvien extends JFrame {
	private Danhmuc dm;
	private Monan mn;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GDnhanvien frame = new GDnhanvien();
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
	public GDnhanvien() {
		setTitle("Giao Diện nhân viên");
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
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 5, 124, 40);
		lblNewLabel.setPreferredSize(new Dimension(150, 30));
		PanelHeader.add(lblNewLabel);

		JButton Btnlogout = new JButton("Thoát");
		Btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		Btnlogout.setFont(new Font("Segoe UI", Font.BOLD, 10));
		Btnlogout.setBounds(141, 13, 69, 28);
		PanelHeader.add(Btnlogout);

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
		Btnhanghoa.setBounds(0, 56, 220, 55);
		PanelLeft.add(Btnhanghoa);

		JButton Btndathang = new JButton("Quản lý đặt hàng");
		Btndathang.setBackground(Color.LIGHT_GRAY);
		Btndathang.setFont(new Font("Segoe UI", Font.BOLD, 14));
		Btndathang.setBounds(0, 114, 220, 50);
		PanelLeft.add(Btndathang);

	}

}
