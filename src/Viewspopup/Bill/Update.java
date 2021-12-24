
package Viewspopup.Bill;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

import org.w3c.dom.events.MouseEvent;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.*;
import java.awt.event.*;

import Controls.BanAnController;
import Controls.BillController;
import Controls.BillDetailController;
import Controls.ProductController;
import Controls.ProductTypeController;
import Models.ProducTypeModel;
import Models.ProductModel;
import Models.BanAn;
import Models.BillDetailProductToppingModel;
import Models.BillModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    

public class Update extends JFrame implements ActionListener, MouseListener,ChangeListener {

    private JPanel firstCom, secondCom, thirdCom, fourthCom, mainCom;
    private Map<String, Integer> hs = null;
    private int id,total,discount,paymentPrice;
    private ArrayList<BillDetailProductToppingModel> initialState,currentState;
    private BillModel mod;
    private JSpinner spinner;
    private JLabel discountLabel,mustPayLabel,paymentLabel,totalLabel,statusLabel;
    private JComboBox combo1,combo2;
    private Map<String,Integer> hsBanAn;
    private String paymentDate;
    
    private int calDiscountPrice(){

        int price=(int)(total-total*(double)discount/100);
        return price;
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("do_an") ||
                e.getActionCommand().equals("tra_sua") ||
                e.getActionCommand().equals("ca_phe") ||
                e.getActionCommand().equals("topping")) {

            id = hs.get(e.getActionCommand());

            thirdComponent();

            thirdCom.revalidate();
            thirdCom.repaint();
            mainCom.revalidate();
            mainCom.repaint();
            this.pack();
        }
        else if(e.getActionCommand().equals("update")){
            BillDetailController.update(initialState);
            BillDetailController.add(currentState);

            BillModel bm=new BillModel(
                mod.getId(),
                "",
                Integer.toString(hsBanAn.get(combo1.getSelectedItem().toString())),
                combo2.getSelectedItem().toString(),
                statusLabel.getText(),
                "",
                paymentDate,
                total,
                paymentPrice,
                (int)spinner.getValue());

            
            BillController.update(bm);

            JOptionPane.showMessageDialog(this, "Thành công");
            
        }
        else if(e.getActionCommand().equals("pay")){

            if (total == 0){
                JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm trước");
            }
            else{
                String m = JOptionPane.showInputDialog("Nhập số tiền");
                if(Integer.parseInt(m) >= calDiscountPrice()){
                    statusLabel.setText("Đã thanh toán");
                    paymentPrice=Integer.parseInt(m);
                    paymentLabel.setText(m+" VND");

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();  

                    paymentDate=dtf.format(now);

                    firstCom.repaint();
                    firstCom.revalidate();
                    mainCom.repaint();
                    mainCom.revalidate();
                    this.pack();
                }
                else{
                    JOptionPane.showMessageDialog(this, "Số tiền không đủ");
                }
            }
        }   
        else if(e.getActionCommand().equals("close")){

            setVisible(false);
            dispose();
        }   
    }
   
    private Integer[] checkState(int idProduct,int idTopping){

        Integer[] result=new Integer[2];

        for (int i=0;i<initialState.size();++i){

            if(initialState.get(i).getIdProduct() == idProduct){

                if(idTopping == -1){

                    if(initialState.get(i).getNameTopping().equals("")){
                        result[0]=1;
                        result[1]=i;
                        return result;
                    }
                }
                else{
                    if(idTopping == 0){

                        if(initialState.get(i).getNameTopping().equals("No Topping")){
                            result[0]=1;
                            result[1]=i;
                            return result;
                        }   
                    }
                    else{
                        if(initialState.get(i).getIdTopping()==idTopping){
                            result[0]=1;
                            result[1]=i;
                            return result;
                        }
                    }
                }
            }
        }

        for (int i=0;i<currentState.size();++i){

            if(currentState.get(i).getIdProduct() == idProduct){

                if(idTopping == -1){

                    if(currentState.get(i).getNameTopping().equals("")){
                        result[0]=2;
                        result[1]=i;
                        return result;
                    }
                }
                else{
                    if(idTopping == 0){

                        if(currentState.get(i).getNameTopping().equals("No Topping")){
                            result[0]=2;
                            result[1]=i;
                            return result;
                        }   
                    }
                    else{
                        if(currentState.get(i).getIdTopping()==idTopping){
                            result[0]=2;
                            result[1]=i;
                            return result;
                        }
                    }
                }
            }
        }

        result[0]=0;
        result[1]=-1;

        return result;
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

        JPanel jp = (JPanel) e.getSource();
        int _id = (int) jp.getClientProperty("addBillDetail");
        ProductModel pm = ProductController.getByPk(_id);

        ArrayList<ProductModel> ap = ProductController.getTopping(_id);

        Integer[] value = AddDetailBillView.showDialog(this, mod.getId(), pm.getTen(), pm.getGia(), ap);

        if(value[0] != null && value[1] != null){

            Integer[] result=checkState(pm.getId(), value[1]);

            if(value[1] > -1){

                if(value[1] > 0){

                    int priceTopping=0;
                    String nameTopping="";
                    for (ProductModel item : ap)
                        if(item.getId() == value[1]){
                            priceTopping=item.getGia();
                            nameTopping=item.getTen();
                            break;
                        }
                    
                        
                    if(result[0]==0){
                        BillDetailProductToppingModel temp=new BillDetailProductToppingModel(
                            -1,
                            _id,
                            value[1],
                            mod.getId(),
                            pm.getGia(),
                            priceTopping,
                            value[0],
                            pm.getTen(),
                            nameTopping,
                            pm.getHinhAnh(),
                            pm.getGia()+priceTopping
                        );

                        total=total+value[0]*pm.getGia()+value[0]*priceTopping;
                        totalLabel.setText(Integer.toString(total));
                        int price=(int)(total-total*(double)discount/100);
                        mustPayLabel.setText(Integer.toString(price)+" VND");
                        currentState.add(temp);    
                    }
                    else{
                        
                        if (result[0] == 1){
                            total=total+
                            (value[0]-initialState.get(result[1]).getAmount())*pm.getGia()+(value[0]-initialState.get(result[1]).getAmount())*priceTopping;
                            totalLabel.setText(Integer.toString(total));
                            int price=(int)(total-total*(double)discount/100);
                            mustPayLabel.setText(Integer.toString(price)+" VND");
                            initialState.get(result[1]).setAmount(value[0]);
                        }
                        else{
                            total=total+
                            (value[0]-currentState.get(result[1]).getAmount())*pm.getGia()+(value[0]-currentState.get(result[1]).getAmount())*priceTopping;
                            totalLabel.setText(Integer.toString(total));
                            int price=(int)(total-total*(double)discount/100);
                            mustPayLabel.setText(Integer.toString(price)+" VND");
                            currentState.get(result[1]).setAmount(value[0]);
                        }
                    }

            
                    fourthComponent();
                }
                else{
                    
                    if (result[0] == 0){
                        BillDetailProductToppingModel temp=new BillDetailProductToppingModel(
                            -1,
                            _id,
                            -1,
                            mod.getId(),
                            pm.getGia(),
                            -1,
                            value[0],
                            pm.getTen(),
                            "No topping",
                            pm.getHinhAnh(),
                            pm.getGia()
                        );
                        currentState.add(temp);
                        total=total+value[0]*pm.getGia();
                        totalLabel.setText(Integer.toString(total));
                        int price=(int)(total-total*(double)discount/100);
                        mustPayLabel.setText(Integer.toString(price)+" VND");
                    }
                    else{
                        if(result[0] == 1){

                            total=total+(value[0]-initialState.get(result[1]).getAmount())*pm.getGia();
                            totalLabel.setText(Integer.toString(total));
                            int price=(int)(total-total*(double)discount/100);
                            mustPayLabel.setText(Integer.toString(price)+" VND");
                            initialState.get(result[1]).setAmount(value[0]);
                        }
                        else{
                            total=total+(value[0]-currentState.get(result[1]).getAmount())*pm.getGia();
                            totalLabel.setText(Integer.toString(total));
                            int price=(int)(total-total*(double)discount/100);
                            mustPayLabel.setText(Integer.toString(price)+" VND");
                            currentState.get(result[1]).setAmount(value[0]);
                        }
                    }
                    fourthComponent();
                }
            }
            else{
                
                if(result[0] == 0){
                    BillDetailProductToppingModel temp=new BillDetailProductToppingModel(
                        -1,
                        _id,
                        -1,
                        mod.getId(),
                        pm.getGia(),
                        -1,
                        value[0],
                        pm.getTen(),
                        "",
                        pm.getHinhAnh(),
                        pm.getGia()
                    );
                    currentState.add(temp);
                    total=total+value[0]*pm.getGia();
                    totalLabel.setText(Integer.toString(total));
                    int price=(int)(total-total*(double)discount/100);
                    mustPayLabel.setText(Integer.toString(price)+" VND");
                }
                else{

                    if(result[0] == 1){
                        total=total+(value[0]-initialState.get(result[1]).getAmount())*pm.getGia();
                        totalLabel.setText(Integer.toString(total));
                        int price=(int)(total-total*(double)discount/100);
                        mustPayLabel.setText(Integer.toString(price)+" VND");
                        initialState.get(result[1]).setAmount(value[0]);
                    }
                    else{
                        total=total+(value[0]-currentState.get(result[1]).getAmount())*pm.getGia();
                        totalLabel.setText(Integer.toString(total));
                        int price=(int)(total-total*(double)discount/100);
                        mustPayLabel.setText(Integer.toString(price)+" VND");
                        currentState.get(result[1]).setAmount(value[0]);
                    }
                }
                fourthComponent();
                
            }
            firstCom.revalidate();
            firstCom.repaint();
            fourthCom.revalidate();
            fourthCom.repaint();
            mainCom.revalidate();
            mainCom.repaint();
            this.pack();
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {

    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {

    }

    @Override
	public void stateChanged(ChangeEvent e) {
		
        JSpinner js=(JSpinner)e.getSource();

        if((int)js.getClientProperty("spinnerComponent") != -1){

            int currentValue=(int)js.getValue();

            int value=(int)js.getClientProperty("spinnerComponent");

            js.putClientProperty("spinnerComponent", currentValue);
            int price=(int)js.getClientProperty("price");
            total+=(currentValue-value)*price;

            totalLabel.setText(Integer.toString(total)+" VND");

            mustPayLabel.setText(Integer.toString(calDiscountPrice())+" VND");

            int state=(int)js.getClientProperty("state");
            int index=(int)js.getClientProperty("index");

            if(state == 1){
                initialState.get(index).setAmount(currentValue);
            }
            else{
                currentState.get(index).setAmount(currentValue);
            }

            fourthCom.repaint();
            fourthCom.revalidate();
            firstCom.repaint();
            firstCom.revalidate();
            mainCom.revalidate();
            mainCom.repaint();
            this.pack();
        }
        else{

            int value=(int)spinner.getValue();

            discount=value;

            discountLabel.setText(Integer.toString(value)+" %");

            int price=(int)(mod.getTongCong()-mod.getTongCong()*(double)value/100);

            mustPayLabel.setText(Integer.toString(price)+" VND");

            firstCom.repaint();
            firstCom.revalidate();
            mainCom.revalidate();
            mainCom.repaint();
            this.pack();
        }

		
	}

    public Update(BillModel m) {

        id = 1;
        mod=m;
        total=m.getTongCong();
        discount=m.getGiamGia();
        paymentDate="";
        firstCom = secondCom = thirdCom = fourthCom = null;
        initialState = BillDetailController.getByHero(mod.getId());
        currentState=new ArrayList<BillDetailProductToppingModel>();
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setTitle("Menu");
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainCom = new JPanel();
        mainCom.setLayout(new BoxLayout(mainCom, BoxLayout.LINE_AXIS));
        // panel.setLayout(new FlowLayout());

        firstCom = firstComponent();
        secondCom = secondComponent();
        thirdComponent();
        fourthComponent();

        mainCom.add(firstCom);

        mainCom.add(secondCom);

        mainCom.add(thirdCom);

        mainCom.add(fourthCom);

        this.setContentPane(mainCom);

        // this.setPreferredSize(new Dimension(400, 500));

        this.pack();

        this.setVisible(true);
    }

    public JPanel firstComponent() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(firstChildFirstComponent());
        panel.add(secondChildFirstComponent());
        panel.add(thirdChildFirstComponent());

        return panel;
    }

    public JPanel secondChildFirstComponent() {

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5, 2, 20, 20));

        Border border = BorderFactory.createTitledBorder("Thông tin hóa đơn");
        panel1.setBorder(border);

        // left
        JLabel label1 = new JLabel("Trạng thái");
        JLabel label2 = new JLabel("Tổng");
        JLabel label3 = new JLabel("Giảm giá");
        JLabel label4 = new JLabel("Phải trả");
        JLabel label5 = new JLabel("Đã thanh toán");

        // right
        statusLabel = new JLabel(mod.getTrangThai());
        totalLabel = new JLabel(Integer.toString(mod.getTongCong())+" VND");
        discountLabel = new JLabel(Integer.toString(mod.getGiamGia())+" %");
        System.out.println((int)(mod.getTongCong()-mod.getTongCong()*(double)mod.getGiamGia()/100));
        int price=(int)(mod.getTongCong()-mod.getTongCong()*(double)mod.getGiamGia()/100);
        mustPayLabel = new JLabel(Integer.toString(price)+" VND");
        paymentLabel = new JLabel(Integer.toString(mod.getThanhToan()));

        panel1.add(label1);
        panel1.add(statusLabel);
        panel1.add(label2);
        panel1.add(totalLabel);
        panel1.add(label3);
        panel1.add(discountLabel);
        panel1.add(label4);
        panel1.add(mustPayLabel);
        panel1.add(label5);
        panel1.add(paymentLabel);

        return panel1;

    }

    public JPanel firstChildFirstComponent() {

        ArrayList<BanAn> temp=BanAnController.getAll();
        String[] table = new String[temp.size()];   
        hsBanAn=new HashMap<String,Integer>();
        for(int i=0;i<temp.size();++i){
            table[i]=temp.get(i).getName();
            hsBanAn.put(table[i],temp.get(i).getId());
        }
        
        String[] typeBill = { "Tại quán", "Đặt hàng" };

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5, 2, 20, 20));

        Border border = BorderFactory.createTitledBorder("Chỉnh sửa thông tin");
        panel1.setBorder(border);

        // left
        JLabel label1 = new JLabel("Mã HD");
        JLabel label2 = new JLabel("Nhân viên");
        JLabel label3 = new JLabel("Bàn");
        JLabel label4 = new JLabel("Loại hóa đơn");
        JLabel label5 = new JLabel("Giảm giá(%)");

        // right
        JLabel label6 = new JLabel(Integer.toString(mod.getId()));
        JLabel label7 = new JLabel(mod.getNguoiLap());
        combo1 = new JComboBox(table);
        combo1.setSelectedItem(mod.getBanAn());
        combo2 = new JComboBox(typeBill);
        combo2.setSelectedItem(mod.getLoai());

        SpinnerModel model = new SpinnerNumberModel(mod.getGiamGia(), 0, 100, 1);
        spinner = new JSpinner(model);
        spinner.addChangeListener(this);
        spinner.putClientProperty("spinnerComponent", -1);
        
        panel1.add(label1);
        panel1.add(label6);
        panel1.add(label2);
        panel1.add(label7);
        panel1.add(label3);
        panel1.add(combo1);
        panel1.add(label4);
        panel1.add(combo2);
        panel1.add(label5);
        panel1.add(spinner);

        return panel1;
    }

    public JPanel thirdChildFirstComponent() {

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5, 2, 20, 20));

        Border border = BorderFactory.createTitledBorder("Thao tác");
        panel1.setBorder(border);
        JButton button2 = new JButton("Thanh toán");
        JButton button3 = new JButton("Cập nhật");
        JButton button4 = new JButton("Đóng");

        button3.setActionCommand("update");
        button3.addActionListener(this);

        button2.setActionCommand("pay");
        button2.addActionListener(this);

        button4.setActionCommand("close");
        button4.addActionListener(this);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);

        return panel1;

    }

    public JPanel secondComponent() {

        ArrayList<ProducTypeModel> pList = ProductTypeController.getAll();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        if (hs != null)
            hs.clear();
        else
            hs = new HashMap<String, Integer>();

        for (int i = 0; i < pList.size(); ++i) {

            hs.put(pList.get(i).getSlug(), pList.get(i).getId());
            JButton jbtn = new JButton(pList.get(i).getTen());
            jbtn.setActionCommand(pList.get(i).getSlug());
            jbtn.addActionListener(this);

            JPanel jpan = new JPanel();
            jpan.setLayout(new BorderLayout());
            jpan.add(jbtn, BorderLayout.CENTER);

            panel.add(jpan);
        }

        JPanel mainpan = new JPanel();
        // mainpan.setLayout(new BorderLayout());
        mainpan.setLayout(new FlowLayout());
        // mainpan.add(panel, BorderLayout.PAGE_START);
        mainpan.add(panel);
        // mainpan.setBorder(BorderFactory.createEtchedBorder());
        // mainpan.setBounds(0, 0, 100, 100);
        return mainpan;
    }

    public JPanel childFourthComponent(int state,int index,String img, String name, String topping, int price, int amount) {
        JPanel imagepanel = new JPanel();
        imagepanel.setOpaque(false);
        imagepanel.setLayout(new FlowLayout());
        try {

            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(60, 60));

            BufferedImage myPicture = ImageIO.read(new File(img));
            Image dimg = myPicture.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);

            label.setIcon(imageIcon);

            // label.setBorder(BorderFactory.createEtchedBorder());
            imagepanel.add(label);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel(name);
        JLabel label2 = new JLabel(topping);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(120,0));
        panel.setLayout(new GridLayout(2, 0));
        // panel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(label1);
        panel.add(label2);

        JLabel label3 = new JLabel(Integer.toString(price) + " VND");
        JPanel panel2 = new JPanel();
        panel2.setOpaque(false);
        // panel2.setBorder(BorderFactory.createEtchedBorder());
        panel2.setPreferredSize(new Dimension(100, 0));
        panel2.setLayout(new FlowLayout());
        // panel2.add(label3,BorderLayout.SOUTH);
        panel2.add(label3);

        SpinnerModel model = new SpinnerNumberModel(amount, 0, 100, 1);
        JSpinner _spinner = new JSpinner(model);
        _spinner.putClientProperty("spinnerComponent", _spinner.getValue());
        _spinner.addChangeListener(this);
        _spinner.putClientProperty("price", price);
        _spinner.putClientProperty("state", state);
        _spinner.putClientProperty("index", index);

        JPanel panel3 = new JPanel();
        panel3.setOpaque(false);
        panel3.setLayout(new FlowLayout());
        panel3.add(_spinner);

        JPanel mainpan = new JPanel();
        // mainpan.setLayout(new FlowLayout());
        mainpan.setLayout(new BoxLayout(mainpan, BoxLayout.X_AXIS));

        mainpan.add(imagepanel);
        mainpan.add(panel);
        mainpan.add(panel2);
        mainpan.add(panel3);
        // mainpan.setBorder(BorderFactory.createEtchedBorder());
        mainpan.setBackground(new Color(179, 255, 230));
        mainpan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return mainpan;
    }

    public void fourthComponent() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 0, 5));

        if (initialState == null)
            initialState = BillDetailController.getByHero(mod.getId());

        for (int i=0;i<initialState.size();++i) {

            JPanel jp = childFourthComponent(
                    1,
                    i,
                    initialState.get(i).getImage(),
                    initialState.get(i).getNameProduct(),
                    initialState.get(i).getNameTopping(),
                    initialState.get(i).getPrice(),
                    initialState.get(i).getAmount());

            panel.add(jp);
        }

        for (int i=0;i<currentState.size();++i) {

            JPanel jp = childFourthComponent(
                    2,
                    i,
                    currentState.get(i).getImage(),
                    currentState.get(i).getNameProduct(),
                    currentState.get(i).getNameTopping(),
                    currentState.get(i).getPrice(),
                    currentState.get(i).getAmount());

            panel.add(jp);
        }

        if (fourthCom == null) {
            fourthCom = new JPanel();
            fourthCom.setLayout(new BorderLayout());
            fourthCom.setBorder(BorderFactory.createTitledBorder("Danh sách món đã đặt"));
        } else {
            fourthCom.removeAll();
        }

        int row = initialState.size()+currentState.size();
        
        if (row * 75 > 750) {
            JScrollPane js = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            js.setPreferredSize(new Dimension(600, 75 * 10));
            fourthCom.add(js, BorderLayout.PAGE_START);
            fourthCom.add(js);
        } else {
            panel.setPreferredSize(new Dimension(600, 75 * row));
            fourthCom.add(panel, BorderLayout.PAGE_START);
        }

    }

    public JPanel childThirdComponent(String img, String ten, int gia, String don_vi) {
        JPanel imagepanel = new JPanel();
        imagepanel.setOpaque(false);
        imagepanel.setLayout(new FlowLayout());
        try {

            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(60, 60));

            BufferedImage myPicture = ImageIO.read(new File(img));
            Image dimg = myPicture.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);

            label.setIcon(imageIcon);

            // label.setBorder(BorderFactory.createEtchedBorder());
            imagepanel.add(label);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label1 = new JLabel(ten);
        JLabel label2 = new JLabel(Integer.toString(gia) + "/ VND " + don_vi);
        // label1.setPreferredSize(new Dimension(0,50));
        // label2.setPreferredSize(new Dimension(0,50));

        // JLabel label1=new JLabel("Blah");
        // JLabel label2=new JLabel("50000 / VND Ly");

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(200, 0));
        panel.setLayout(new GridLayout(2, 0));
        // panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        // panel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(label1);
        panel.add(label2);

        JPanel mainpan = new JPanel();
        // mainpan.setLayout(new GridLayout(0,2));
        // mainpan.setLayout(new FlowLayout());
        mainpan.setLayout(new BoxLayout(mainpan, BoxLayout.LINE_AXIS));

        mainpan.add(imagepanel);
        mainpan.add(panel);
        mainpan.setBackground(new Color(255, 230, 255));
        // mainpan.setMaximumSize(new Dimension(0,100));
        mainpan.setBorder(BorderFactory.createLineBorder(Color.black));
        return mainpan;
    }

    public void thirdComponent() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5));

        ArrayList<ProductModel> ap = ProductController.getByProductType(id);

        for (ProductModel item : ap) {
            JPanel it = childThirdComponent(item.getHinhAnh(), item.getTen(), item.getGia(), item.getDonVi());

            it.putClientProperty("addBillDetail", item.getId());
            it.addMouseListener(this);
            panel.add(it);

        }
        if (thirdCom == null) {
            thirdCom = new JPanel();
            thirdCom.setPreferredSize(new Dimension(600, 1));
            thirdCom.setLayout(new BorderLayout());
        } else {
            thirdCom.removeAll();
        }
        // JPanel mainpan=new JPanel();

        // mainpan.setPreferredSize(new Dimension(600,1));
        // mainpan.setLayout(new BorderLayout());
        int row = (int) Math.round((double) ap.size() / 2);

        if (row * 70 > 700) {
            JScrollPane js = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            js.setPreferredSize(new Dimension(600, 70 * 10));
            thirdCom.add(js, BorderLayout.PAGE_START);
            thirdCom.add(js);
        } else {
            panel.setPreferredSize(new Dimension(600, 75 * row));
            thirdCom.add(panel, BorderLayout.PAGE_START);
        }

    }

    public static void main(String[] args) {
        
    }

}
