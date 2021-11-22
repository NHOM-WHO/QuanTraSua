package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GDdangnhap extends JFrame {

	protected static final Component GDadmin = null;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField txtUid;
	private JPasswordField txtPwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GDdangnhap frame = new GDdangnhap();
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
	public GDdangnhap() {
		setTitle("\u0110\u0103ng Nh\u1EADp T\u00E0i Kho\u1EA3n");
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 205), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("\u0110\u0102NG NH\u1EACP");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(241, 47, 142, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lbUsername = new JLabel("Username");
		lbUsername.setForeground(new Color(255, 255, 255));
		lbUsername.setFont(new Font("Arial", Font.PLAIN, 16));
		lbUsername.setBounds(51, 125, 100, 44);
		contentPane.add(lbUsername);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setForeground(new Color(255, 255, 255));
		lbPassword.setFont(new Font("Arial", Font.PLAIN, 16));
		lbPassword.setBounds(51, 199, 100, 44);
		contentPane.add(lbPassword);
		
		txtUid = new JTextField();
		txtUid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		lbUsername.setLabelFor(txtUid);
		txtUid.setBounds(161, 133, 263, 30);
		contentPane.add(txtUid);
		txtUid.setColumns(10);
		
		txtPwd = new JPasswordField();
		lbPassword.setLabelFor(txtPwd);
		txtPwd.setBounds(161, 209, 263, 30);
		contentPane.add(txtPwd);
		
		JButton btnDangnhap = new JButton("X\u00E1c Nh\u1EADn");
		btnDangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect cn = new connect();
				Connection connection = null;
				try {
					connection=cn.getConnection();
					String sql = "Select * from Employees Where USERNAME =? and PASSWORD=?";
					PreparedStatement pst =connection.prepareCall(sql);
					pst.setString(1, txtUid.getText());
					pst.setString(2, txtPwd.getText());
					ResultSet rs = pst.executeQuery();
					if(rs.next())
					{
						GDadmin aDadmin = new GDadmin();
						aDadmin.setVisible(true);
						 dispose();
					}else {
						JOptionPane.showMessageDialog(null,"Sai tài khoản mật khẩu");;
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,ex);
				}
			}
		});
		btnDangnhap.setBounds(241, 282, 100, 35);
		contentPane.add(btnDangnhap);
		JFrame jf = new JFrame();
	
		jf.setLocationRelativeTo(null);
	}

	
}
