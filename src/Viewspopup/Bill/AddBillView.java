
package Viewspopup.Bill;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import java.util.*;
import Controls.BanAnController;
import Models.BanAn;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import Models.BillModel;
import Controls.BillController;

public class AddBillView extends JFrame implements ActionListener {
    
    private JPanel contentPane;
    private Map<String,Integer> hsBanAn;
    private JComboBox combo1,combo2;
    private JSpinner spinner;
    private JTextPane txtNameTable;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBillView frame = new AddBillView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public void actionPerformed(ActionEvent e){

        if(txtNameTable.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Chưa điền đủ thông tin");
        }
        else{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            BillModel mod=new BillModel(
                -1,
                txtNameTable.getText(),
                Integer.toString(hsBanAn.get(combo1.getSelectedItem().toString())),
                combo2.getSelectedItem().toString(),
                "Chưa thanh toán",
                dtf.format(now),
                "",
                0,
                0,
                (int)spinner.getValue()
            );

            BillController.add(mod);

            JOptionPane.showMessageDialog(this,"Thành công");
            setVisible(false);
            dispose();
        }
    }
    
	public AddBillView() {
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
		
		JLabel label_1 = new JLabel("Người lập");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_1);
		
		txtNameTable = new JTextPane();
        JPanel panel_ttt=new JPanel();
        panel_ttt.setLayout(null);
        panel_ttt.add(txtNameTable);
        txtNameTable.setBounds(0,35,200,20);
		panel_1.add(panel_ttt);

        JLabel label_2 = new JLabel("Bàn ăn");
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_2);

        ArrayList<BanAn> temp=BanAnController.getAll();
        String[] table = new String[temp.size()];   
        hsBanAn=new HashMap<String,Integer>();
        for(int i=0;i<temp.size();++i){
            table[i]=temp.get(i).getName();
            hsBanAn.put(table[i],temp.get(i).getId());
        }

        combo1 = new JComboBox(table);
        JPanel panel_t=new JPanel();
        panel_t.setLayout(null);
        panel_t.add(combo1);
        combo1.setBounds(0,35,100,20);
        panel_1.add(panel_t);

        JLabel label_3 = new JLabel("Loại");
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_3);

        String[] typeBill = { "Tại quán", "Đặt hàng" };
        combo2 = new JComboBox(typeBill);
        JPanel panel_tt=new JPanel();
        panel_tt.setLayout(null);
        panel_tt.add(combo2);
        combo2.setBounds(0,35,100,20);
        panel_1.add(panel_tt);

        JLabel label_4 = new JLabel("Giảm giá");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_4);

        SpinnerModel model = new SpinnerNumberModel(0, 0, 100, 1);
        spinner = new JSpinner(model);
        JPanel panel_tttt=new JPanel();
        panel_tttt.setLayout(null);
        panel_tttt.add(spinner);
        spinner.setBounds(0,35,100,20);
        panel_1.add(panel_tttt);
		
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
