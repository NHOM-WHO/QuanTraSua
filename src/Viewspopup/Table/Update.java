
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

public class Update extends JFrame {

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
	public Update() {
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
		
		JLabel lblNewLabel = new JLabel("Sửa bàn ăn");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblNewLabel.setBounds(159, 10, 153, 33);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(49, 64, 421, 315);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 66, 69, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên bàn");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 111, 69, 13);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Trạng thái");
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(10, 148, 69, 13);
		panel_1.add(lblNewLabel_1_2);
		
		JTextPane txtID = new JTextPane();
		txtID.setBounds(97, 60, 185, 19);
		panel_1.add(txtID);
		
		JTextPane txtNameTable = new JTextPane();
		txtNameTable.setBounds(97, 105, 185, 19);
		panel_1.add(txtNameTable);
		
		JTextPane txtStatus = new JTextPane();
		txtStatus.setBounds(97, 148, 185, 19);
		panel_1.add(txtStatus);
		
		JButton Btnupdate = new JButton("Sửa");
		Btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection connection=null;
				try {
                    connection=connect.getConnection();
					String query="UPDATE BanAn SET Name=?,Status=? WHERE Id=?";
					PreparedStatement ps=connection.prepareStatement(query);
					ps.setString(1, txtNameTable.getText());
					ps.setString(2, txtStatus.getText());
					ps.setString(3, txtID.getText());
                    
					
                    if(ps.executeUpdate() > 0){
                        JOptionPane.showMessageDialog(null, "saved");
                    }else
                    JOptionPane.showMessageDialog(null, "Sai ID, Hãy thử lại");
                    
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
