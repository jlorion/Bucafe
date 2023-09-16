package CafeTypeShit;

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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
public class MainCafeApp extends javax.swing.JFrame {
    ItemStructure barang;
    SalesStructure penjualan=new SalesStructure();
    private Object lblkembalian;
    private double subTotal=0;
    private double ppn=0;
    private double total=0;
    private DefaultTableModel tabel = new DefaultTableModel();
    private double tunai;
    DataCom datacoms = new DataCom();
    private String[] category = {"All Items", "espresso", "cake", "bread"};
    

    public MainCafeApp() {
        ArrayList<Map<String, Object>> menuData = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeting", "root",  "");
            System.out.println("connected");
            
            menuData = datacoms.getMenu(con);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	setResizable(false);
        initComponents();
        fillComboBarang(menuData);
        tblBarang.setModel(penjualan.getTabel());
        tblBarang.removeColumn(tblBarang.getColumnModel().getColumn(5));


        // tblBarang.getColumnModel().getColumn(5).setMaxWidth(0);
        // tblBarang.getColumnModel().getColumn(5).setMinWidth(0);
        
    }
   //DONE: make it so that it integrates with the database and you can add and remove menu itemes 
    private void fillComboBarang(ArrayList<Map<String, Object>> menuData){
        try {
            
            
            // menuData.forEach(x -> {
            //     ItemStructure barang = new ItemStructure(x.get("Name").toString() , "pcs", (int)x.get("Price"), (int) x.get("ID"));
            //     cboBarang.addItem(barang);
                
            // });
            // cboBarang.setSelectedItem(null);
            categories.addActionListener(e -> {

                cboBarang.removeAllItems();
                if (categories.getSelectedItem().equals(category[0])){
                    menuData.forEach(x -> {
                        ItemStructure barang = new ItemStructure(x.get("Name").toString() , "pcs", (int)x.get("Price"), (int) x.get("ID"));
                        cboBarang.addItem(barang);
                    });
                    
                }else if (categories.getSelectedItem().equals(category[1])) {
                    
                    menuData.forEach(x-> {
                        if (x.get("class").equals(category[1])) {
                            ItemStructure barang = new ItemStructure(x.get("Name").toString() , "pcs", (int)x.get("Price"), (int) x.get("ID"));
                            cboBarang.addItem(barang);
                        }
                    });
                    
                }else if (categories.getSelectedItem().equals(category[2])) {
                    
                    menuData.forEach(x-> {
                        if (x.get("class").equals(category[2])) {
                            ItemStructure barang = new ItemStructure(x.get("Name").toString() , "pcs", (int)x.get("Price"), (int) x.get("ID"));
                            cboBarang.addItem(barang);
                        }
                    });
                    
                }else if (categories.getSelectedItem().equals(category[3])) {
                   
                    menuData.forEach(x-> {
                        if (x.get("class").equals(category[3])) {
                            ItemStructure barang = new ItemStructure(x.get("Name").toString() , "pcs", (int)x.get("Price"), (int) x.get("ID"));
                            cboBarang.addItem(barang);
                        }
                    });
                    
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PageFormat getPageFormat(PrinterJob pj){
    
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
    
   
    
     // iplement this so that it should pay first beofre you could print the receiprt 

    public int print(Graphics graphics, PageFormat pageFormat,int pageIndex)  throws PrinterException {    
      
                
        
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
            
                // TODO: make this resposive to query on recent sale frome the db 
            g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            g2d.drawString("            Quick Chicken           ",12,y);y+=yShift;
            g2d.drawString("=====================================",12,y);y+=yShift;
            g2d.drawString("            C Puseur Jaya           ",12,y);y+=yShift;
            g2d.drawString("            08112571383             ",12,y);y+=yShift;
            g2d.drawString("=====================================",12,y);y+=headerRectHeight;
            g2d.drawString("Counter #                           ",12,y);y+=yShift;
            g2d.drawString("======================================",10,y);y+=yShift;
            g2d.drawString("   Tamu                             1",10,y);y+=yShift;
            g2d.drawString("1 Paha Bawah                    8.182",10,y);y+=yShift;
            g2d.drawString("1 Nasi + Paha Bawah             10.909",10,y);y+=yShift;
            g2d.drawString("1 Nasi + Sayap                  10.000",10,y);y+=yShift;
            g2d.drawString("1 Nasi + Paha Bawah Marathon    25.454",10,y);y+=yShift;
            g2d.drawString("1 Nasi + Sayap Marathon         23.636",10,y);y+=yShift;
            g2d.drawString("1 Orange Quash                  4.545",10,y);y+=yShift;
            g2d.drawString("1 Iced Green Tea Latter         7.273",10,y);y+=yShift;
            g2d.drawString("======================================",10,y);y+=yShift;
            g2d.drawString(" Subtotal                       89.999",10,y);y+=yShift;
            g2d.drawString(" P.Rest 10%                      9.000",10,y);y+=yShift;
            g2d.drawString(" 9 Total                        98.999",10,y);y+=yShift;
            g2d.drawString(" Ella                                  ",10,y);y+=yShift;
            g2d.drawString(" Dine In                               ",10,y);y+=yShift;
            g2d.drawString("    Rounding                          1",10,y);y+=yShift;
            g2d.drawString("    Bayar                       100.000",10,y);y+=yShift;
            g2d.drawString("    Cash                         99.000",10,y);y+=yShift;
            g2d.drawString("    Kembali                       1.000",10,y);y+=yShift;
            g2d.drawString("Selasa      5- 6-2018 20:01:19         ",10,y);y+=yShift;
            g2d.drawString("#006771                                ",10,y);y+=yShift;
            g2d.drawString("         T e r i m a  K a s i h        ",10,y);y+=yShift;
            g2d.drawString("           Atas Kunjungannya           ",10,y);y+=yShift;
            g2d.drawString("      Kritik dan saran  0811-250-006   ",10,y);y+=yShift;
            g2d.drawString("      Info Catering     0811-2572-955  ",10,y);y+=yShift;

                           
          

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboBarang = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSatuan = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBarang = new javax.swing.JTable();
        btnSimpan = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        chkPPN = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        lblPPN = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnHapus = new javax.swing.JButton();
        txtTunai = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnCetak = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnBayar = new javax.swing.JButton();
        lblKembali = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        categories = new JComboBox();
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cafe Cashiering and inventory system");
        setBackground(new java.awt.Color(255, 255, 153));
        setForeground(new java.awt.Color(153, 255, 51));
        setIconImages(null);

        jLabel1.setFont(new java.awt.Font("Source Sans Pro", 1, 24)); // NOI18N
        jLabel1.setText("Cafe Cashiering And Inventory System");

        jLabel2.setText("Menu");

        cboBarang.setToolTipText("");
        cboBarang.setNextFocusableComponent(txtQuantity);
        cboBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBarangActionPerformed(evt);
            }
        });

        jLabel3.setText("Purchase Amount");

        jLabel4.setText("Price");

        lblHarga.setText("00");

        jLabel5.setText("Php.");

        lblSatuan.setText("pcs");

        txtQuantity.setNextFocusableComponent(btnSimpan);
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

        tblBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Menu", "Price", "Amount", "Unit", "Total Price", "ID"

            }
        ));
        

        jScrollPane1.setViewportView(tblBarang);

        btnSimpan.setText("Save");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        jLabel6.setText("Subtotal :");

        jLabel7.setText("Php.");

        lblSubtotal.setText("00");

        chkPPN.setText("PPN");
        chkPPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPPNActionPerformed(evt);
            }
        });

        jLabel8.setText("Php.");

        lblPPN.setText("00");

        jLabel9.setText("Total");

        jLabel10.setText("Php.");

        lblTotal.setText("00");

        btnHapus.setText("wipe");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        txtTunai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTunaiKeyTyped(evt);
            }
        });

        jLabel11.setText("Cash Pay");

        btnCetak.setText("Print Reciept");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {//TODO: add listener if pay is clicked else show jjopoptionpane warning 
            public void actionPerformed(java.awt.event.ActionEvent evt) {//SOL: add flag(boolean) on Pay button so that receipt button is only activitad when pay button is clicked
                btnCetakActionPerformed(evt);
            }
        });
        btnCetak.setEnabled(false);

        jLabel13.setText("Change");

        btnBayar.setText("Pay");
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);//ToDO: add flag and eroor event when total payable > cash pay
                btnCetak.setEnabled(true);
                double cash = Integer.parseInt(txtTunai.getText());
                total = penjualan.countTotal();
                double changeCash = cash - total;
                try {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeting", "root", "");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    int purchase_id = datacoms.postPurchase(con, total, cash, changeCash);
                      

                    for (int i = 0; i <= penjualan.getTabel().getRowCount() -1; i++) {
                        //push dis data and assign each id values for id make a func on datacom to get id of the last added purchasess store it in an int 
                        int menu_id = Integer.parseInt(penjualan.getTabel().getValueAt(i, 5).toString());
                        int amount = Integer.parseInt(penjualan.getTabel().getValueAt(i, 2).toString());
                        double item_total = Double.parseDouble(penjualan.getTabel().getValueAt(i, 4).toString());
                        datacoms.pushOrders(con, purchase_id, menu_id, amount, item_total);
                    }
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //SUTODO:make a function with paameters 
            }
        });
        btnBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBayarKeyPressed(evt);
            }
        });

        lblKembali.setText("00");

        jLabel14.setFont(new java.awt.Font("Yu Gothic", 1, 16)); // NOI18N
        jLabel14.setText("Powered By Group 9");

        jLabel15.setText("Php.");
        
        JButton btnNewButton = new JButton("Inventory");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Inventory items = new Inventory();
        		items.show();
        		
        		dispose();
        		
        		
        	}
        });
        
        btnNewButton_1 = new JButton("New Sale");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int tableLength = penjualan.getTabel().getRowCount();
        		for (int i = 0; i < tableLength; i++) {
                    
        	        penjualan.getTabel().removeRow(0);
        		}
        		
        		btnCetak.setEnabled(false);
        		
        		
        	}
        }); 
       
       
       for(String s : category) {
       categories.addItem(s);
       }
        
       
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\User\\Downloads\\LOGO.png"));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jLabel14))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(15)
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jLabel2)
        								.addComponent(jLabel4)
        								.addComponent(jLabel3))
        							.addGap(65)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(cboBarang, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE)
        									.addGap(35)
        									.addComponent(categories, 0, 115, Short.MAX_VALUE)
        									.addGap(39))
        								.addGroup(layout.createSequentialGroup()
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addGroup(layout.createSequentialGroup()
        											.addGap(10)
        											.addComponent(jLabel5)
        											.addGap(58)
        											.addComponent(lblHarga, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
        										.addGroup(layout.createSequentialGroup()
        											.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
        											.addGap(18)
        											.addComponent(lblSatuan)))
        									.addPreferredGap(ComponentPlacement.RELATED, 169, Short.MAX_VALUE)))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
        							.addGap(31))
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(chkPPN)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(jLabel8)
        									.addGap(18)
        									.addComponent(lblPPN, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
        									.addGap(30)
        									.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        										.addGroup(layout.createSequentialGroup()
        											.addComponent(jLabel9)
        											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        											.addComponent(jLabel15)
        											.addGap(18)
        											.addComponent(lblTotal, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
        										.addGroup(layout.createSequentialGroup()
        											.addComponent(jLabel6)
        											.addGap(18)
        											.addComponent(jLabel7)
        											.addGap(18)
        											.addComponent(lblSubtotal, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
        									.addGap(77))
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
        									.addGap(25)))
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(btnBayar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        									.addGap(18)
        									.addComponent(btnCetak))
        								.addGroup(layout.createSequentialGroup()
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
        										.addComponent(jLabel13))
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addGroup(layout.createParallelGroup(Alignment.LEADING)
        										.addGroup(layout.createSequentialGroup()
        											.addComponent(jLabel10)
        											.addGap(18)
        											.addComponent(lblKembali))
        										.addComponent(txtTunai, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))))
        							.addGap(99))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(21)
        							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE)
        							.addGap(54)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(btnHapus, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        								.addComponent(btnSimpan, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))))))
        			.addGap(44))
        		.addGroup(layout.createSequentialGroup()
        			.addGap(25)
        			.addComponent(jLabel1)
        			.addContainerGap(412, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(jLabel1)
        					.addGap(8)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(28)
        							.addComponent(jLabel2)
        							.addGap(12)
        							.addComponent(jLabel4)
        							.addGap(12)
        							.addComponent(jLabel3))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(25)
        							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(cboBarang, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addComponent(categories, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addGap(9)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jLabel5)
        								.addComponent(lblHarga))
        							.addGap(9)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        								.addGroup(layout.createSequentialGroup()
        									.addGap(3)
        									.addComponent(lblSatuan))))))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(34)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))
        			.addGap(10)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(15)
        					.addComponent(btnSimpan)
        					.addGap(18)
        					.addComponent(btnHapus)
        					.addGap(31)
        					.addComponent(btnNewButton_1))
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(chkPPN)
        					.addComponent(jLabel8)
        					.addComponent(lblPPN))
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel7)
        					.addComponent(lblSubtotal)
        					.addComponent(jLabel6)
        					.addComponent(jLabel11)
        					.addComponent(txtTunai, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
        			.addGap(13)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(lblTotal)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jLabel9)
        					.addComponent(jLabel10)
        					.addComponent(jLabel13)
        					.addComponent(lblKembali)
        					.addComponent(jLabel15)))
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(28)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnCetak)
        						.addComponent(btnBayar)
        						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
        					.addGap(0, 9, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
        					.addComponent(jLabel14)))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            btnSimpan.requestFocus();
    }//GEN-LAST:event_txtQuantityKeyPressed

    private void cboBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBarangActionPerformed
        barang = (ItemStructure)cboBarang.getSelectedItem();
        lblHarga.setText(NumberFormat.getNumberInstance().format(barang.getPrice()));
        lblSatuan.setText(barang.getUnit()); 
    }//GEN-LAST:event_cboBarangActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String[] data = new String[6];
        double harga, jumlah=0;
        int qty=0;
        try {
            data[0]=barang.getNameSale();
            harga=barang.getPrice();
            data[1]=String.valueOf(barang.getPrice());
            qty=Integer.parseInt(txtQuantity.getText());
            data[2]=txtQuantity.getText();
            data[3]=barang.getUnit();
            jumlah=harga*qty;
            data[4]=String.valueOf(jumlah);
            data[5]= String.valueOf(barang.getId());
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(cboBarang, "Error", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
        
        penjualan.getTabel().addRow(data);
        lblSubtotal.setText(NumberFormat.getNumberInstance().format(penjualan.countSubtotal()));
        chkPPNActionPerformed(null);
        
        cboBarang.requestFocus();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void chkPPNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPPNActionPerformed
        if (chkPPN.isSelected())
            lblPPN.setText(NumberFormat.getNumberInstance().format(penjualan.countPPN()));
        else{
            
            lblPPN.setText("0");
            penjualan.setPpn(0);
        }
        
        lblTotal.setText(NumberFormat.getNumberInstance().format(penjualan.countTotal()));
    }//GEN-LAST:event_chkPPNActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        
            penjualan.getTabel().removeRow(tblBarang.getSelectedRow());
        // for (int i = 0; i <= penjualan.getTabel().getRowCount()+1; i++) {
        //     penjualan.getTabel().removeRow(0);
        // TODO: make a clear button to clear everything add it in the bottom part beside print receipt!!

        // }

        lblSubtotal.setText(NumberFormat.getNumberInstance().format(penjualan.countSubtotal()));
        chkPPNActionPerformed(null);
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed

                        
        PrinterJob pj = PrinterJob.getPrinterJob();        
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
             pj.print();
          
        }
         catch (PrinterException ex) {
                 ex.printStackTrace();
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed

    }//GEN-LAST:event_txtQuantityActionPerformed

    private void txtTunaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTunaiKeyTyped

    }//GEN-LAST:event_txtTunaiKeyTyped

    private void btnBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBayarKeyPressed

    }//GEN-LAST:event_btnBayarKeyPressed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed

        
    double tunai = Integer.parseInt(txtTunai.getText());
    total = penjualan.countTotal();
    double kembali = tunai-total;
    lblKembali.setText(NumberFormat.getNumberInstance().format(kembali));
    }//GEN-LAST:event_btnBayarActionPerformed

    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainCafeApp().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox cboBarang;
    private javax.swing.JCheckBox chkPPN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblPPN;
    private javax.swing.JLabel lblSatuan;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tblBarang;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtTunai;
    private JButton btnNewButton_1;
    private JComboBox categories;
    // End of variables declaration//GEN-END:variables

    private double getTotal(String format) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
