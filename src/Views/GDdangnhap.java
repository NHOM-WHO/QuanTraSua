package Views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class GDdangnhap extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextField textField;
	private JPasswordField passwordField;

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
		
		textField = new JTextField();
		lbUsername.setLabelFor(textField);
		textField.setBounds(161, 133, 263, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		lbPassword.setLabelFor(passwordField);
		passwordField.setBounds(161, 209, 263, 30);
		contentPane.add(passwordField);
		
		JButton btnDangnhap = new JButton("X\u00E1c Nh\u1EADn");
		btnDangnhap.setBounds(241, 282, 100, 35);
		contentPane.add(btnDangnhap);
		JFrame jf = new JFrame();
	
		jf.setLocationRelativeTo(null);
	}
}
