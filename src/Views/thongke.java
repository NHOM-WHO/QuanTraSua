package Views;

import javax.swing.*;
import java.awt.*;

public class thongke extends JPanel {
	/**

	 */
	private final JTable table = new JTable();
	private JTable table_1;
	private JTable table_2;

	/**
	 * Create the panel.
	 */
	public thongke() {
		setBackground(new Color(32, 178, 170));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(0, 0, 755, 59);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thống Kê Doanh Thu");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(282, 10, 186, 39);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 178, 170));
		panel_1.setBounds(553, 57, 202, 458);
		add(panel_1);
		panel_1.setLayout(null);
		
		JButton Btnsua = new JButton("Thống Kê");
		Btnsua.setBounds(10, 10, 85, 21);
		panel_1.add(Btnsua);
		
		JButton Btnload = new JButton("Thoát");
		Btnload.setBounds(105, 10, 85, 21);
		panel_1.add(Btnload);
		
		table_2 = new JTable();
		table_2.setBounds(10, 57, 545, 460);
		add(table_2);
	}
}
