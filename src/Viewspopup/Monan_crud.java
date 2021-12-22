package Viewspopup;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import Views.connect;
import javax.swing.border.MatteBorder;
import javax.tools.FileObject;

import java.awt.Color;

public class Monan_crud extends JFrame {

	private JPanel contentPane;
	private String anh=null;
	private File file;
	/**
	 * Launch the application.
	 */
	private byte[] ConvertFile(String filename) {
		FileInputStream fileInputStream=null;
		File file=new File(filename);
		byte[] bFile=new byte[(int) file.length()];
		try {
			fileInputStream=new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
			bFile=null;
		}
		return bFile;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monan_crud frame = new Monan_crud();
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
	public Monan_crud() {
		setBounds(100, 100, 680, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.setBounds(144, 29, 382, 53);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Thêm món ăn");
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblNewLabel.setBounds(136, 10, 153, 33);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(31, 105, 625, 398);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 35, 69, 13);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Description");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 80, 69, 13);
		panel_1.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_3 = new JLabel("UntilName");
		lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(318, 35, 94, 13);
		panel_1.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel("Price");
		lblNewLabel_1_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(318, 80, 83, 13);
		panel_1.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_5 = new JLabel("IDCate");
		lblNewLabel_1_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1_5.setBounds(10, 125, 83, 13);
		panel_1.add(lblNewLabel_1_5);

		JTextPane txtName = new JTextPane();
		txtName.setBounds(97, 29, 185, 19);
		panel_1.add(txtName);

		JTextPane txtDescription = new JTextPane();
		txtDescription.setBounds(97, 74, 185, 19);
		panel_1.add(txtDescription);

		JTextPane txtUntilname = new JTextPane();
		txtUntilname.setBounds(393, 35, 185, 19);
		panel_1.add(txtUntilname);

		JTextPane txtPrice = new JTextPane();
		txtPrice.setBounds(393, 80, 185, 19);
		panel_1.add(txtPrice);

		JTextPane txtIdcate = new JTextPane();
		txtIdcate.setBounds(103, 125, 185, 19);
		panel_1.add(txtIdcate);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblNewLabel_2.setBounds(131, 205, 353, 183);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Lấy ảnh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser= new JFileChooser();
				jFileChooser.setDialogTitle("ADD IMAGE");
				jFileChooser.setMultiSelectionEnabled(false);
				jFileChooser.setFileFilter(new FileTypeFilter(".jpg","JPG"));
				jFileChooser.setFileFilter(new FileTypeFilter(".gif","GIF"));
				jFileChooser.setFileFilter(new FileTypeFilter(".png","PNG"));
				int result =jFileChooser.showOpenDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					 file=jFileChooser.getSelectedFile();
					lblNewLabel_2.setIcon(new ImageIcon(file.getAbsolutePath()));
					String anh=file.getAbsolutePath().replace("/", "//");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(0, 304, 93, 21);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("Ảnh món");
		lblNewLabel_1_5_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNewLabel_1_5_1.setBounds(10, 179, 83, 24);
		panel_1.add(lblNewLabel_1_5_1);
		
		JButton btnNewButton_1 = new JButton("Thêm");
		btnNewButton_1.addActionListener(new ActionListener() {
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
					dispose();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(521, 304, 94, 21);
		panel_1.add(btnNewButton_1);

	}
}
