
package Viewspopup.Table;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Views.*;

public class Add extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add frame = new Add();
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
	public Add() {
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
		
		JLabel lblNewLabel = new JLabel("Thêm bàn ăn");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblNewLabel.setBounds(159, 10, 153, 33);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(49, 64, 421, 315);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tên bàn");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 66, 69, 13);
		panel_1.add(lblNewLabel_1);
		
		JTextPane txtNameTable = new JTextPane();
		txtNameTable.setBounds(97, 60, 185, 19);
		panel_1.add(txtNameTable);
		
		JButton Btnupdate = new JButton("Thêm");
		Btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection=null;
				try {
                    connection=connect.getConnection();
					String query="INSERT INTO BanAn (Name,Status) VALUES (?,?);";
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, txtNameTable.getText());
					ps.setString(2, "Trong");
                    System.out.println(txtNameTable.getText());
					if(ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "saved");
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Hãy thử lại");
                    System.out.print("oh yeah");
					
					dispose();
				} catch (Exception e2) {
					System.out.printf(null,e);
				}
			}
		});
		Btnupdate.setBounds(177, 405, 85, 21);
		contentPane.add(Btnupdate);
	}
}
