
package Viewspopup.Customer;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import Models.CustomerModel;
import Controls.CustomerController;

public class AddCustomerView extends JFrame implements ActionListener {
    
    private JPanel contentPane;
    private JTextPane txtNameTable,textPane_1,textPane_2;
    private JComboBox combo1,combo2,combo3;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCustomerView frame = new AddCustomerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public void actionPerformed(ActionEvent e){

        if(txtNameTable.getText().equals("") || textPane_1.getText().equals("") || textPane_2.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Chưa điền đủ thông tin");
        }
        else{
            
            CustomerModel cm=new CustomerModel(
                -1,
                txtNameTable.getText(),
                textPane_1.getText(),
                textPane_2.getText(),
                combo3.getSelectedItem().toString()+"-"+combo2.getSelectedItem().toString()+"-"+combo1.getSelectedItem().toString()+" 00:00:00"
            );

            CustomerController.add(cm);

            JOptionPane.showMessageDialog(this,"Thành công");
            setVisible(false);
            dispose();
        }
    }
    
	public AddCustomerView() {
		setBounds(100, 100, 481, 473);
		
        contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10,10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
        JPanel panel = new JPanel();
		contentPane.add(panel,BorderLayout.PAGE_START);
		panel.setLayout(new FlowLayout());
		
		JLabel label_0 = new JLabel("Thêm hóa đơn");
		label_0.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		panel.add(label_0);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1,BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0,2));
		
		JLabel label_1 = new JLabel("Họ và tên");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_1);
		
		txtNameTable = new JTextPane();
        JPanel panel_t=new JPanel();
        panel_t.setLayout(null);
        panel_t.add(txtNameTable);
        txtNameTable.setBounds(0,25,200,20);
		panel_1.add(panel_t);

        JLabel label_2 = new JLabel("Số DT:");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_2);

        textPane_1 = new JTextPane();
        JPanel panel_tt=new JPanel();
        panel_tt.setLayout(null);
        panel_tt.add(textPane_1);
        textPane_1.setBounds(0,25,200,20);
		panel_1.add(panel_tt);

        JLabel label_3 = new JLabel("Địa chỉ");
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_3);

        textPane_2 = new JTextPane();
        JPanel panel_ttt=new JPanel();
        panel_ttt.setLayout(null);
        panel_ttt.add(textPane_2);
        textPane_2.setBounds(0,25,200,20);
		panel_1.add(panel_ttt);

        JLabel label_4 = new JLabel("Ngày sinh");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_4);

        String[] date = new String[31];

        for(int i=1;i<=31;++i)
            date[i-1]=Integer.toString(i);
            
        combo1 = new JComboBox(date);
        JPanel panel_tttt=new JPanel();
        panel_tttt.setLayout(null);
        panel_tttt.add(combo1);
        combo1.setBounds(0,25,100,20);
        panel_1.add(panel_tttt);

        JLabel label_5 = new JLabel("Tháng sinh");
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_5);

        String[] month = new String[12];
        for(int i=1;i<=12;++i)
            month[i-1]=Integer.toString(i);

        combo2 = new JComboBox(month);
        JPanel panel_ttttt=new JPanel();
        panel_ttttt.setLayout(null);
        panel_ttttt.add(combo2);
        combo2.setBounds(0,25,100,20);
        panel_1.add(panel_ttttt);

        JLabel label_6 = new JLabel("Năm sinh");
		label_6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_6);

        String[] year = new String[100];
        for(int i=0;i<100;++i)
            year[i]=Integer.toString(2021-i);

        combo3 = new JComboBox(year);
        JPanel panel_tttttt=new JPanel();
        panel_tttttt.setLayout(null);
        panel_tttttt.add(combo3);
        combo3.setBounds(0,25,100,20);
        panel_1.add(panel_tttttt);
		
		JButton Btnupdate = new JButton("Thêm");
        Btnupdate.addActionListener(this);
		JPanel panel_2=new JPanel();
        panel_2.setLayout(new FlowLayout());
        panel_2.add(Btnupdate);

		contentPane.add(panel_2,BorderLayout.PAGE_END);
        setVisible(true);
        // pack();
	}
}
