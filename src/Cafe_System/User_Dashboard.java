/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Cafe_System;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.Connection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Map;
/**
 *
 * @author Arlene Mae Sayson
 */
public class User_Dashboard extends javax.swing.JFrame  {
    ItemStructure goods;
    SalesStructure salesStructure=new SalesStructure();
    private Object lblkembalian;
    private double subTotal=0;
    private double ppn=0;
    private double total=0;
    private DefaultTableModel tabel = new DefaultTableModel();
    private double tunai;
    DataCom datacoms = new DataCom();
    Connection conn = datacoms.getConnection();
    private int purchase_id;
    private Map<String, Object> employeeData = loginform.employeeData;

    public User_Dashboard() {
	
	
        setResizable(false);
        initComponents();
        PrintReceiptbtn.setEnabled(false);
        fillComboGoods();
        tblGoods.setModel(salesStructure.getTabel());
        tblGoods.removeColumn(tblGoods.getColumnModel().getColumn(5));
        tblGoods.removeColumn(tblGoods.getColumnModel().getColumn(3));
        
    }
    
    private void fillComboGoods(){
	
        try {
            ArrayList<ItemStructure> menuData = datacoms.getMenu(conn);
            menuData.forEach(x -> {
                ItemStructure goods = x;
                System.out.println(x.getNameSale());
                cboGoods.addItem(goods);   
            });
            
            categories.addActionListener(e -> {
                cboGoods.removeAllItems();
                menuData.forEach(x->{
                    if (categories.getSelectedItem().equals(x.getCategory())) {
                        System.out.println(x.getNameSale());
                        cboGoods.addItem(x);
                    }else if (categories.getSelectedItem().equals("All Items")) {
                        cboGoods.addItem(x);
                    }{

                    }
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );  
            
    pf.setOrientation(PageFormat.PORTRAIT);           
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}


public class BillPrintable implements Printable {
    
   
   public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
  throws PrinterException 
  {    
      
                
      ArrayList<Map<String, Object>> purchaseList = datacoms.getLatestPurchase(conn, purchase_id);
      Map<String, Object> c = purchaseList.get(0);
      LocalDate today = LocalDate.now();

      DayOfWeek dayOfWeek = today.getDayOfWeek();
      int result = NO_SUCH_PAGE;    
        if (pageIndex == 0) {                    
        
            Graphics2D g2d = (Graphics2D) graphics;                    

            double width = pageFormat.getImageableWidth();                    
           
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 



            FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

            int idLength=metrics.stringWidth("000");
            int amtLength=metrics.stringWidth("000000");
            int qtyLength=metrics.stringWidth("00000");
            int priceLength=metrics.stringWidth("000000");
            int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;


            
            int productPosition = 0;
            int discountPosition= prodLength+5;
            int pricePosition = discountPosition +idLength+10;
            int qtyPosition=pricePosition + priceLength + 4;
            int amtPosition=qtyPosition + qtyLength;
            
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
            
            
            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawString("             Green Stub           ",12,y);y+=yShift;
            g2d.drawString("=====================================",12,y);y+=yShift;
            g2d.drawString("           Official Reciept           ",12,y);y+=yShift;
            g2d.drawString("             09123456789              ",12,y);y+=yShift;
            g2d.drawString("=====================================",12,y);y+=headerRectHeight;
            g2d.drawString("Counter #                           ",12,y);y+=yShift;
            g2d.drawString("======================================",10,y);y+=yShift;
            g2d.drawString("Qty   Orders        Price        Total ",10,y);y+=yShift;
             
            for(int i = 0; i <= purchaseList.size()-1; i++){
                Map<String, Object> x = purchaseList.get(i);
                
                String confString = x.get("name").toString() + "       ";
                g2d.drawString(x.get("qty")+"    "+confString.substring(0,7)+"("+x.get("menu_id")+")"+"     "+x.get("price")+"         "+x.get("item_total"),10,y);y+=yShift;
               
            }
            g2d.drawString("======================================",10,y);y+=yShift;
            g2d.drawString(" Total                          "+c.get("total"),10,y);y+=yShift;
            g2d.drawString("    Cash                        "+c.get("cash"),10,y);y+=yShift;
            g2d.drawString("    Change                      "+c.get("change"),10,y);y+=yShift;
            g2d.drawString(dayOfWeek+"    "+c.get("datentime")+"         ",10,y);y+=yShift;//modify date
            g2d.drawString("#"+purchase_id+"                                ",10,y);y+=yShift;//modify recirpt num
            g2d.drawString("         G R E E N  S T U B             ",10,y);y+=yShift;
            

                           
          

    }
    catch(Exception r){
    r.printStackTrace();
    }

              result = PAGE_EXISTS;    
          }    
          return result;    
      }
   }
    

    @SuppressWarnings("unchecked") 
   
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel2 = new javax.swing.JPanel();
                Quantity = new javax.swing.JLabel();
                DashboardCashierLabel = new javax.swing.JLabel();
                pcs = new javax.swing.JLabel();
                PricePanel = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();
                lblPrice = new javax.swing.JLabel();
                categories = new javax.swing.JComboBox<>();
                ArrayList<String> category = datacoms.getCategories(datacoms.getConnection());
                category.forEach(x->categories.addItem(x));
                cboGoods = new javax.swing.JComboBox();
                jLabel5 = new javax.swing.JLabel();
                txtQuantity = new javax.swing.JTextField();
                btnSave = new javax.swing.JButton();
                jPanel3 = new javax.swing.JPanel();
                DashboardCashierLabel1 = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                jPanel7 = new javax.swing.JPanel();
                REMOVEbtn = new javax.swing.JButton();
                jScrollPane1 = new javax.swing.JScrollPane();
                tblGoods = new javax.swing.JTable();
                jLabel9 = new javax.swing.JLabel();
                jPanel6 = new javax.swing.JPanel();
                TaxCheckBox = new javax.swing.JCheckBox();
                taxPhp = new javax.swing.JLabel();
                TaxAmount = new javax.swing.JLabel();
                Cash = new javax.swing.JTextField();
                cashLbl = new javax.swing.JLabel();
                SubtotalLbl = new javax.swing.JLabel();
                TotalLbl = new javax.swing.JLabel();
                ChangeLbl = new javax.swing.JLabel();
                SubTotalPhp = new javax.swing.JLabel();
                Subtotal = new javax.swing.JLabel();
                TotalPhp = new javax.swing.JLabel();
                Total = new javax.swing.JLabel();
                ChangePhp = new javax.swing.JLabel();
                Change = new javax.swing.JLabel();
                jPanel1 = new javax.swing.JPanel();
                DashboardCashierLabel2 = new javax.swing.JLabel();
                jPanel8 = new javax.swing.JPanel();
                Paybtn = new javax.swing.JButton();
                PrintReceiptbtn = new javax.swing.JButton();
                jPanel9 = new javax.swing.JPanel();
                jPanel11 = new javax.swing.JPanel();
                jButton1 = new javax.swing.JButton();
                jPanel10 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                InventoryBtn = new javax.swing.JButton();
                NewSalebtn = new javax.swing.JButton();

                setLocationByPlatform(true);
                getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                Quantity.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                Quantity.setText("Quantity");

                DashboardCashierLabel.setBackground(new java.awt.Color(194, 202, 149));
                DashboardCashierLabel.setFont(new java.awt.Font("Source Han Sans CN Bold", 1, 16)); // NOI18N
                DashboardCashierLabel.setText(">     Cashier");

                pcs.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                pcs.setText("pcs");

                PricePanel.setBackground(new java.awt.Color(194, 202, 149));
                PricePanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel6.setText("Price");

                jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                jLabel7.setText("Php.");

                lblPrice.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                lblPrice.setText("00");

                javax.swing.GroupLayout PricePanelLayout = new javax.swing.GroupLayout(PricePanel);
                PricePanel.setLayout(PricePanelLayout);
                PricePanelLayout.setHorizontalGroup(
                        PricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PricePanelLayout.createSequentialGroup()
                                .addGap(0, 16, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                PricePanelLayout.setVerticalGroup(
                        PricePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                categories.setBackground(new java.awt.Color(194, 202, 149));
                categories.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                categories.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                categoriesActionPerformed(evt);
                        }
                });

                cboGoods.setBackground(new java.awt.Color(194, 202, 149));
                cboGoods.setToolTipText("");
                cboGoods.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cboGoodsActionPerformed(evt);
                        }
                });

                jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel5.setText("Menu");

                txtQuantity.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtQuantityActionPerformed(evt);
                        }
                });
                txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                                txtQuantityKeyPressed(evt);
                        }
                });

                btnSave.setBackground(new java.awt.Color(146, 149, 105));
                btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/Add.png"))); // NOI18N
                btnSave.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSaveActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(DashboardCashierLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(34, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(36, 36, 36)
                                                .addComponent(categories, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addComponent(cboGoods, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(296, 296, 296))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(pcs, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(PricePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(21, 21, 21))))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(DashboardCashierLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(categories, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cboGoods, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(pcs, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(PricePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(21, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 820, 180));

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                DashboardCashierLabel1.setBackground(new java.awt.Color(194, 202, 149));
                DashboardCashierLabel1.setFont(new java.awt.Font("Source Han Sans CN Bold", 1, 21)); // NOI18N
                DashboardCashierLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                DashboardCashierLabel1.setText("BILLS & PAYMENT");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(34, Short.MAX_VALUE)
                                .addComponent(DashboardCashierLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(31, Short.MAX_VALUE)
                                .addComponent(DashboardCashierLabel1)
                                .addGap(15, 15, 15))
                );

                getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, 230, 80));

                jPanel5.setBackground(new java.awt.Color(255, 255, 255));
                jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                jPanel7.setBackground(new java.awt.Color(146, 149, 105));
                jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                REMOVEbtn.setBackground(new java.awt.Color(146, 149, 105));
                REMOVEbtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                REMOVEbtn.setForeground(new java.awt.Color(255, 255, 255));
                REMOVEbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/Remove.png"))); // NOI18N
                REMOVEbtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                REMOVEbtn.setBorderPainted(false);
                REMOVEbtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                REMOVEbtnActionPerformed(evt);
                        }
                });

                tblGoods.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null}
                        },
                        new String [] {
                                "Menu", "Price", "Amount", "Unit", "Total Price", "id"
                        }
                ));
                tblGoods.setRowHeight(30);
                jScrollPane1.setViewportView(tblGoods);

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(REMOVEbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(14, Short.MAX_VALUE))
                );
                jPanel7Layout.setVerticalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(REMOVEbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                );

                jLabel9.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
                jLabel9.setText(">      Orders");

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addContainerGap(37, Short.MAX_VALUE)
                                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(7, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 6, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 820, 370));

                jPanel6.setBackground(new java.awt.Color(194, 202, 149));
                jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(152, 160, 101))); // NOI18N

                TaxCheckBox.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                TaxCheckBox.setText("Tax");
                TaxCheckBox.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                TaxCheckBoxActionPerformed(evt);
                        }
                });

                taxPhp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                taxPhp.setForeground(new java.awt.Color(255, 255, 255));
                taxPhp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                taxPhp.setText("Php.");

                TaxAmount.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                TaxAmount.setForeground(new java.awt.Color(255, 255, 255));
                TaxAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                TaxAmount.setText("0.00");

                Cash.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                Cash.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                CashActionPerformed(evt);
                        }
                });

                cashLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                cashLbl.setText("Cash");

                SubtotalLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                SubtotalLbl.setText("Subtotal:");

                TotalLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                TotalLbl.setText("Total:");

                ChangeLbl.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                ChangeLbl.setText("Change:");

                SubTotalPhp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                SubTotalPhp.setForeground(new java.awt.Color(255, 255, 255));
                SubTotalPhp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                SubTotalPhp.setText("Php.");

                Subtotal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                Subtotal.setForeground(new java.awt.Color(255, 255, 255));
                Subtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                Subtotal.setText("0.00");

                TotalPhp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                TotalPhp.setForeground(new java.awt.Color(255, 255, 255));
                TotalPhp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                TotalPhp.setText("Php.");

                Total.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                Total.setForeground(new java.awt.Color(255, 255, 255));
                Total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                Total.setText("0.00");

                ChangePhp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                ChangePhp.setForeground(new java.awt.Color(255, 255, 255));
                ChangePhp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                ChangePhp.setText("Php.");

                Change.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                Change.setForeground(new java.awt.Color(255, 255, 255));
                Change.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                Change.setText("0.00");

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap(29, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(ChangeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ChangePhp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Change, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(TotalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TotalPhp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(SubtotalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(SubTotalPhp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cashLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(TaxCheckBox))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                                .addComponent(taxPhp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(TaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(Cash, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(24, 24, 24))
                );
                jPanel6Layout.setVerticalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TaxCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(taxPhp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(SubtotalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(SubTotalPhp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(TotalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TotalPhp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(ChangeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ChangePhp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Change, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cashLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Cash, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(61, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 150, 230, 360));

                jPanel1.setBackground(new java.awt.Color(146, 149, 105));

                DashboardCashierLabel2.setBackground(new java.awt.Color(194, 202, 149));
                DashboardCashierLabel2.setFont(new java.awt.Font("Swis721 Cn BT", 1, 36)); // NOI18N
                DashboardCashierLabel2.setForeground(new java.awt.Color(255, 255, 255));
                DashboardCashierLabel2.setText("GREEN STUB");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(389, Short.MAX_VALUE)
                                .addComponent(DashboardCashierLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(178, 178, 178))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(DashboardCashierLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(17, 17, 17))
                );

                getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 820, 70));

                jPanel8.setBackground(new java.awt.Color(255, 255, 255));
                jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP));

                Paybtn.setBackground(new java.awt.Color(146, 149, 105));
                Paybtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                Paybtn.setForeground(new java.awt.Color(22, 31, 13));
                Paybtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/cash-payment-bill-3-7.png"))); // NOI18N
                Paybtn.setText("PAY");
                Paybtn.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
                Paybtn.setBorderPainted(false);
                Paybtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                PaybtnActionPerformed(evt);
                        }
                });

                PrintReceiptbtn.setBackground(new java.awt.Color(146, 149, 105));
                PrintReceiptbtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                PrintReceiptbtn.setForeground(new java.awt.Color(255, 255, 255));
                PrintReceiptbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/Receipt.png"))); // NOI18N
                PrintReceiptbtn.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
                PrintReceiptbtn.setBorderPainted(false);
                PrintReceiptbtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                PrintReceiptbtnActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(Paybtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PrintReceiptbtn)
                                .addContainerGap(45, Short.MAX_VALUE))
                );
                jPanel8Layout.setVerticalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(PrintReceiptbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Paybtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(26, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 510, 230, 110));

                jPanel9.setBackground(new java.awt.Color(146, 149, 105));

                jPanel11.setBackground(new java.awt.Color(146, 149, 105));

                jButton1.setText("Sign-out");
                jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton1ActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                jPanel11.setLayout(jPanel11Layout);
                jPanel11Layout.setHorizontalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addContainerGap(66, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                );
                jPanel11Layout.setVerticalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                                .addContainerGap())
                );

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60))
                );
                jPanel9Layout.setVerticalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                );

                getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 230, 70));

                jPanel10.setBackground(new java.awt.Color(194, 202, 149));
                jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/Cafe'Logo.png"))); // NOI18N
                jLabel2.setText("jLabel2");

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addContainerGap(28, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                jPanel10Layout.setVerticalGroup(
                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(40, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 250));

                jPanel4.setBackground(new java.awt.Color(194, 202, 149));
                jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                jLabel1.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setText("INVENTORY");

                jLabel3.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
                jLabel3.setText("NEW SALE");

                InventoryBtn.setBackground(new java.awt.Color(146, 149, 105));
                InventoryBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                InventoryBtn.setForeground(new java.awt.Color(255, 255, 255));
                InventoryBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/InventoryF.png"))); // NOI18N
                InventoryBtn.setBorderPainted(false);
                InventoryBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                InventoryBtnActionPerformed(evt);
                        }
                });

                NewSalebtn.setBackground(new java.awt.Color(146, 149, 105));
                NewSalebtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                NewSalebtn.setForeground(new java.awt.Color(255, 255, 255));
                NewSalebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/layers-grid-add-8.png"))); // NOI18N
                NewSalebtn.setBorderPainted(false);
                NewSalebtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                NewSalebtnActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(NewSalebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(InventoryBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))))
                                        .addComponent(jLabel1))
                                .addContainerGap(50, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(NewSalebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(60, 60, 60)
                                .addComponent(InventoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addContainerGap(54, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, -1, 370));

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void REMOVEbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_REMOVEbtnActionPerformed
        salesStructure.getTabel().removeRow(tblGoods.getSelectedRow());
        Subtotal.setText(NumberFormat.getNumberInstance().format(salesStructure.countSubtotal()));
        TaxCheckBoxActionPerformed(null);
    }//GEN-LAST:event_REMOVEbtnActionPerformed

    private void NewSalebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewSalebtnActionPerformed
                int tableLength = salesStructure.getTabel().getRowCount();
        for (int i = 0; i < tableLength; i++) {

            salesStructure.getTabel().removeRow(0);
        }
        PrintReceiptbtn.setEnabled(false);
    }//GEN-LAST:event_NewSalebtnActionPerformed

    private void PaybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaybtnActionPerformed
         double cash = Integer.parseInt(Cash.getText());
                total = salesStructure.countTotal();
                double changeCash = cash - total;
                Change.setText(""+ changeCash);
                if (cash < total) {
                    JOptionPane.showMessageDialog(null, "Cash Is Less Than Total", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else { 
                    PrintReceiptbtn.setEnabled(true);
                    try {
                        
                        purchase_id = datacoms.postPurchase(conn, total, cash,
				changeCash, (int)employeeData.get("employeeId"));
                        ArrayList<ItemStructure> menu = datacoms.getMenu(conn);

                        
                        for (int i = 0; i <= salesStructure.getTabel().getRowCount() -1; i++) {
                            //push dis data and assign each id values for id make a func on datacom to get id of the last added purchasess store it in an int 
                            int menu_id = Integer.parseInt(salesStructure.getTabel().getValueAt(i, 5).toString());
                            int qty = Integer.parseInt(salesStructure.getTabel().getValueAt(i, 1).toString());
                            double item_total = Double.parseDouble(salesStructure.getTabel().getValueAt(i, 4).toString());
                            menu.forEach(x-> {
                                if (x.getId() == menu_id){ 
                       
                                    int inventory = x.getQty();
                                    int newInventory = inventory - qty;
                                    datacoms.updateInventory(conn, menu_id, newInventory);
                                }
                            });
                            datacoms.pushOrders(conn, purchase_id, menu_id, qty, item_total);
                        }
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
    
    }//GEN-LAST:event_PaybtnActionPerformed

    private void CashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CashActionPerformed

    private void TaxCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TaxCheckBoxActionPerformed
        if (TaxCheckBox.isSelected())
            TaxAmount.setText(NumberFormat.getNumberInstance().format(salesStructure.countPPN()));
        else{
            
            TaxAmount.setText("0");
            salesStructure.setPpn(0);
        }
        
        Total.setText(NumberFormat.getNumberInstance().format(salesStructure.countTotal()));
    }//GEN-LAST:event_TaxCheckBoxActionPerformed

    private void PrintReceiptbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintReceiptbtnActionPerformed
         PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
    }//GEN-LAST:event_PrintReceiptbtnActionPerformed

    private void InventoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InventoryBtnActionPerformed
        
        
            
            
	if ((boolean)employeeData.get("adminAccess")) {
	    Inventory items = new Inventory();
		    items.show();
		    setVisible(false);
		    dispose();
	}else{
	JOptionPane.showMessageDialog(null,
		"YOu do not have access to this menu!!", getTitle()+" admin",
		JOptionPane.WARNING_MESSAGE);
	}
         
    
    }//GEN-LAST:event_InventoryBtnActionPerformed

    private void cboGoodsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGoodsActionPerformed
        // TODO add your handling code here:
        try {
            goods = (ItemStructure)cboGoods.getSelectedItem();
            lblPrice.setText(NumberFormat.getNumberInstance().format(goods.getPrice()));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cboGoodsActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed

    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        btnSave.requestFocus();
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String[] data = new String[6];
        double price, amount=0;
        int qty=0;
        
        if(goods.getQty() >= Integer.parseInt(txtQuantity.getText())){
            try {
                data[0]=goods.getNameSale();
                price=goods.getPrice();
                data[1]=txtQuantity.getText();
                qty=Integer.parseInt(txtQuantity.getText());
                data[2]=String.valueOf(goods.getPrice());
                data[3]=goods.getUnit();
                amount=price*qty;
                data[4]=String.valueOf(amount);
                data[5]= String.valueOf(goods.getId());
                salesStructure.getTabel().addRow(data);
                Subtotal.setText(NumberFormat.getNumberInstance().format(salesStructure.countSubtotal()));
                TaxCheckBoxActionPerformed(null);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(cboGoods, "Error", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(cboGoods, "Quantity exceeds Inventory", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        

        cboGoods.requestFocus();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void categoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriesActionPerformed

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                // TODO add your handling code here:
		loginform loginform = new loginform();
		loginform.show();
		
		setVisible(false);
		dispose();
        }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) { 
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    
                }
                System.out.println(info.getName());
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(User_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(User_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(User_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(User_Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new User_Dashboard().setVisible(true);
            }
        });
	
	
    }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JTextField Cash;
        private javax.swing.JLabel Change;
        private javax.swing.JLabel ChangeLbl;
        private javax.swing.JLabel ChangePhp;
        private javax.swing.JLabel DashboardCashierLabel;
        private javax.swing.JLabel DashboardCashierLabel1;
        private javax.swing.JLabel DashboardCashierLabel2;
        private javax.swing.JButton InventoryBtn;
        private javax.swing.JButton NewSalebtn;
        private javax.swing.JButton Paybtn;
        private javax.swing.JPanel PricePanel;
        private javax.swing.JButton PrintReceiptbtn;
        private javax.swing.JLabel Quantity;
        private javax.swing.JButton REMOVEbtn;
        private javax.swing.JLabel SubTotalPhp;
        private javax.swing.JLabel Subtotal;
        private javax.swing.JLabel SubtotalLbl;
        private javax.swing.JLabel TaxAmount;
        private javax.swing.JCheckBox TaxCheckBox;
        private javax.swing.JLabel Total;
        private javax.swing.JLabel TotalLbl;
        private javax.swing.JLabel TotalPhp;
        private javax.swing.JButton btnSave;
        private javax.swing.JLabel cashLbl;
        private javax.swing.JComboBox<String> categories;
        private javax.swing.JComboBox cboGoods;
        private javax.swing.JButton jButton1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel10;
        private javax.swing.JPanel jPanel11;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JPanel jPanel4;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPanel jPanel6;
        private javax.swing.JPanel jPanel7;
        private javax.swing.JPanel jPanel8;
        private javax.swing.JPanel jPanel9;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JLabel lblPrice;
        private javax.swing.JLabel pcs;
        private javax.swing.JLabel taxPhp;
        private javax.swing.JTable tblGoods;
        private javax.swing.JTextField txtQuantity;
        // End of variables declaration//GEN-END:variables
}
