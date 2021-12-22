
package Viewspopup.Bill;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import Models.ProductModel;
import java.util.*;

public class AddDetailBillView extends JDialog implements ActionListener,ChangeListener {
    
	private static AddDetailBillView dialog;
    private static Integer[] value=new Integer[2];
    private JSpinner spinner;
	private JComboBox jcom=null;
	private JLabel tong_tien;
	private Map<String,Integer> hs,hsPriceTopping;
	private int price;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// AddDetailBillView frame = new AddDetailBillView(1,"Trà sữa trân châu",50000,{"Blah blah","Blah blah"});
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Integer[] showDialog(Component frameComp,
                                    int id,
                                    String name,
                                    int price,
                                    ArrayList<ProductModel> topping) {
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        dialog = new AddDetailBillView(frame,id, name,price,topping);
        dialog.setVisible(true);
        return value;
    }

	/**
	 * Create the frame.
	 */
	public AddDetailBillView(Frame frame,int id,String name,int price,ArrayList<ProductModel> topping) {
		super(frame, "Hóa đơn", true);
		this.price=price;
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		JLabel title = new JLabel("hóa đơn "+Integer.toString(id)+" - Thêm món");
		title.setFont(new Font("Segoe UI Black", Font.BOLD, 14));

		panel.add(title);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(49, 64, 421, 315);
		panel_1.setLayout(new GridLayout(0,2));
		
		JLabel label_1 = new JLabel("Tên món");
		label_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_1);

		JLabel label_2 = new JLabel(name);
		label_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Số lượng");
		label_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_3);

		SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);
		spinner = new JSpinner(model);
		spinner.addChangeListener(this);
		JPanel pan=new JPanel();
		pan.setLayout(null);
		spinner.setBounds(0,25,200,20);
		pan.add(spinner);
		panel_1.add(pan);
		
		JLabel label_4 = new JLabel("Giá món");
		label_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_4);

		JLabel label_5 = new JLabel(Integer.toString(price)+" VND");
		label_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));

		panel_1.add(label_5);

		if(topping.size() > 0){

			JLabel label_6 = new JLabel("Topping");
			label_6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			// label_6.setBounds(10, 185, 69, 13);
			panel_1.add(label_6);

			String[] top=new String[topping.size()+1];
			hs=new HashMap<String,Integer>();
			hsPriceTopping=new HashMap<String,Integer>();


			top[0]="No Topping";
			hs.put(top[0],0);
			hsPriceTopping.put(top[0],0);
			for(int i=0;i<topping.size();++i){
				top[i+1]=topping.get(i).getTen()+" / "+Integer.toString(topping.get(i).getGia());
				hs.put(top[i+1],topping.get(i).getId());
				hsPriceTopping.put(top[i+1],topping.get(i).getGia());
			}

			jcom=new JComboBox(top);
			jcom.setActionCommand("topping");
			jcom.addActionListener(this);
			JPanel jp=new JPanel();
			jp.setLayout(null);
			jcom.setBounds(0,22,200,20);
			jp.add(jcom);
			panel_1.add(jp);
		}

		JLabel label_6 = new JLabel("Tổng tiền");
		label_6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(label_6);

		tong_tien = new JLabel(Integer.toString(price)+" VND");
		tong_tien.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_1.add(tong_tien);
		
		
		JButton Btnupdate = new JButton("Sửa");
		Btnupdate.addActionListener(this);
		Btnupdate.setActionCommand("update");
		JPanel jp=new JPanel();
		jp.setLayout(new FlowLayout());
		jp.add(Btnupdate);
		getRootPane().setDefaultButton(Btnupdate);
		
		JPanel mainpan=new JPanel();
		mainpan.setLayout(new BorderLayout());
		mainpan.add(panel,BorderLayout.PAGE_START);
		mainpan.add(panel_1,BorderLayout.CENTER);
		mainpan.add(jp,BorderLayout.PAGE_END);
		mainpan.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		Container contentPane = getContentPane();
		contentPane.add(mainpan,BorderLayout.CENTER);
		setBounds(400, 200, 1000, 400);
		setPreferredSize(new Dimension(500,400));
		
		pack();
	}

	public void actionPerformed(ActionEvent e) {
        if ("update".equals(e.getActionCommand())) {
            AddDetailBillView.value[0]=(Integer)spinner.getValue();
			if(jcom != null)
				AddDetailBillView.value[1]=hs.get(jcom.getSelectedItem().toString());
			else{
				AddDetailBillView.value[1]=-1;
			}
			AddDetailBillView.dialog.setVisible(false);
		}
		else if(e.getActionCommand().equals("topping")){

			int value=(int)spinner.getValue();
			tong_tien.setText(Integer.toString(value*price+hsPriceTopping.get(jcom.getSelectedItem().toString())*value)+" VND");

			this.repaint();
			this.revalidate();
		}
    }


	@Override
	public void stateChanged(ChangeEvent e) {
		
		int value=(int)spinner.getValue();

		if(jcom == null){

			tong_tien.setText(Integer.toString(value*price)+" VND");
		}
		else{

			tong_tien.setText(Integer.toString(value*price+hsPriceTopping.get(jcom.getSelectedItem().toString())*value)+" VND");
		}

		this.repaint();
		this.revalidate();

	}
}
