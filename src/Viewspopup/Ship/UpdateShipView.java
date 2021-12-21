
package Viewspopup.Ship;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import Models.BillModel;
import Models.CustomerModel;
import Models.ShipCustomerModel;
import Models.ShipModel;
import Controls.BillController;
import Controls.CustomerController;
import Controls.ShipController;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class UpdateShipView extends JFrame implements ActionListener {
    
    private JPanel contentPane;
    private JTextPane textPane_1,textPane_2;
    private JComboBox combo1,combo2,combo3;
    private Map<String,Integer> hsCustomer;
    private JSpinner spinner;
    private ShipCustomerModel mod;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// UpdateShipView frame = new UpdateShipView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public void actionPerformed(ActionEvent e){

        if(textPane_1.getText().equals("") || textPane_2.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Chưa điền đủ thông tin");
        }
        else{

            if(combo2.getSelectedItem().toString().equals("Hoàn thành")){

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();  
                ShipModel cm=new ShipModel(
                    mod.getId(),
                    mod.getBill(),
                    hsCustomer.get(combo1.getSelectedItem().toString()),
                    (int)spinner.getValue(),
                    textPane_1.getText(),
                    textPane_2.getText(),
                    combo2.getSelectedItem().toString(),
                    "",
                    dtf.format(now)
                );

                ShipController.update(cm);

            }
            else{
 
                ShipModel cm=new ShipModel(
                    mod.getId(),
                    mod.getBill(),
                    hsCustomer.get(combo1.getSelectedItem().toString()),
                    (int)spinner.getValue(),
                    textPane_1.getText(),
                    textPane_2.getText(),
                    combo2.getSelectedItem().toString(),
                    "",
                    ""
                );

                ShipController.update(cm);
            }

            

            JOptionPane.showMessageDialog(this,"Thành công");
            setVisible(false);
            dispose();
        }
    }
    
	public UpdateShipView(ShipCustomerModel _sm) {
        mod=_sm;
		setBounds(100, 100, 481, 473);
		
        contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10,10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
        JPanel panel = new JPanel();
		contentPane.add(panel,BorderLayout.PAGE_START);
		panel.setLayout(new FlowLayout());
		
		JLabel label_0 = new JLabel("Thông tin giao hàng");
		label_0.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		panel.add(label_0);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1,BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0,2));
		

		JLabel label_1 = new JLabel("Khách hàng");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_1);
		
        ArrayList<CustomerModel> acm=CustomerController.getAll();
        String[] customer=new String[acm.size()];
        hsCustomer=new HashMap<String,Integer>();
        for(int i=0;i<acm.size();++i ){

            customer[i]=acm.get(i).getName();
            hsCustomer.put(customer[i],acm.get(i).getId());
        }

		combo1 = new JComboBox(customer);
        combo1.setSelectedItem(mod.getCustomerName());
        JPanel panel_t=new JPanel();
        panel_t.setLayout(null);
        panel_t.add(combo1);
        combo1.setBounds(0,25,200,20);
        panel_1.add(panel_t);

        JLabel label_2 = new JLabel("Tên shipper");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_2);

        textPane_1 = new JTextPane();
        textPane_1.setText(mod.getShipper());
        JPanel panel_tt=new JPanel();
        panel_tt.setLayout(null);
        panel_tt.add(textPane_1);
        textPane_1.setBounds(0,25,200,20);
		panel_1.add(panel_tt);

        JLabel label_3 = new JLabel("SDT Shipper");
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_3);

        textPane_2 = new JTextPane();
        textPane_2.setText(mod.getPhone());
        JPanel panel_ttt=new JPanel();
        panel_ttt.setLayout(null);
        panel_ttt.add(textPane_2);
        textPane_2.setBounds(0,25,200,20);
		panel_1.add(panel_ttt);

        JLabel label_4 = new JLabel("Giá ship");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_4);

        SpinnerModel model = new SpinnerNumberModel(mod.getGia(), 0, Integer.MAX_VALUE, 1);
        spinner = new JSpinner(model);
        JPanel panel_tttt=new JPanel();
        panel_tttt.setLayout(null);
        panel_tttt.add(spinner);
        spinner.setBounds(0,35,200,20);
        panel_1.add(panel_tttt);


        JLabel label_5 = new JLabel("Trạng thái");
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_5);

        String[] status={"Chờ xác nhận","Chờ lấy hàng","Đang giao","Hoàn thành","Đã hủy"};
        combo2 = new JComboBox(status);
        combo2.setSelectedItem(mod.getStatus());
        JPanel panel_ttttt=new JPanel();
        panel_ttttt.setLayout(null);
        panel_ttttt.add(combo2);
        combo2.setBounds(0,25,200,20);
        panel_1.add(panel_ttttt);
		
		JButton Btnupdate = new JButton("Sửa");
        Btnupdate.addActionListener(this);
		JPanel panel_2=new JPanel();
        panel_2.setLayout(new FlowLayout());
        panel_2.add(Btnupdate);

		contentPane.add(panel_2,BorderLayout.PAGE_END);
        setVisible(true);
        // pack();
	}
}
