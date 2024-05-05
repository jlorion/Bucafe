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
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.EventObject;
import javax.swing.*;
/**
 *
 * @author Arlene Mae Sayson
 */
public class Inventory extends javax.swing.JFrame {
    private DataCom data = new DataCom();
    private SalesStructure structure = new SalesStructure();
    private javax.swing.JComboBox<String> cboxTable;

     public Inventory() {
        setResizable(false);
        initComponents();
        fillUp();
        config();
        
    }
    
    private void fillUp(){
        cboxTable = new javax.swing.JComboBox<>();
        
        cboCategory.removeAllItems();
        cboxTable.removeAllItems();
        
        tblInventory.setModel(structure.getInventoryTable());
        tblCategory.setModel(structure.getCategoryTable());
        structure.getInventoryTable().setRowCount(0);
        structure.getCategoryTable().setRowCount(0);
        Connection conn = data.getConnection();
        ArrayList<ItemStructure> menuData = data.getMenu(conn);
        Object[] menuObjects = new Object[5];
        menuData.forEach(item -> {
            menuObjects[0] = item.getId();
            menuObjects[1] = item.getNameSale();
            menuObjects[2] = item.getQty();
            menuObjects[3] = item.getPrice();
            menuObjects[4] = item.getCategory();
            structure.getInventoryTable().addRow(menuObjects);


        });
        ArrayList<String> categoryList = data.getCategories(conn);
        String[] catArr = new String[1];
        categoryList.forEach(x->{
            if (!x.equals("All Items")) {
                catArr[0] = x;
                structure.getCategoryTable().addRow(catArr);
                cboxTable.addItem(x);
                cboCategory.addItem(x);
            }
        });

    }
    private void config(){
        TableColumn qtyColumn= tblInventory.getColumnModel().getColumn(2);
        TableColumn priceColumn= tblInventory.getColumnModel().getColumn(3);
        try {
            tblInventory.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cboxTable));    
            qtyColumn.setCellRenderer(new SpinnerRenderer());
            qtyColumn.setCellEditor(new SpinnerEditor());
            priceColumn.setCellRenderer(new SpinnerRenderer());
            priceColumn.setCellEditor(new SpinnerEditor());
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    

    

    @SuppressWarnings("unchecked") 
   
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel2 = new javax.swing.JPanel();
                Quantity = new javax.swing.JLabel();
                pcs = new javax.swing.JLabel();
                cboCategory = new javax.swing.JComboBox<>();
                jLabel5 = new javax.swing.JLabel();
                AddMenuItembtn = new javax.swing.JButton();
                jSpinner1 = new javax.swing.JSpinner();
                txtName = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();
                back = new javax.swing.JButton();
                jPanel8 = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();
                jSpinner2 = new javax.swing.JSpinner();
                jPanel3 = new javax.swing.JPanel();
                DashboardCashierLabel1 = new javax.swing.JLabel();
                jPanel5 = new javax.swing.JPanel();
                jPanel7 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                tblInventory = new javax.swing.JTable();
                tblInventory.setRowHeight(30);
                btnClear = new javax.swing.JButton();
                jLabel9 = new javax.swing.JLabel();
                jPanel6 = new javax.swing.JPanel();
                jScrollPane2 = new javax.swing.JScrollPane();
                tblCategory = new javax.swing.JTable();
                txtAddCategory = new javax.swing.JTextField();
                btcAddCategory = new javax.swing.JButton();
                btnRemoveCategory = new javax.swing.JButton();
                btnSaveCategory = new javax.swing.JButton();
                jPanel1 = new javax.swing.JPanel();
                DashboardCashierLabel2 = new javax.swing.JLabel();
                jPanel9 = new javax.swing.JPanel();
                LogOut = new javax.swing.JButton();
                jPanel11 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                jPanel10 = new javax.swing.JPanel();
                jLabel2 = new javax.swing.JLabel();
                jPanel4 = new javax.swing.JPanel();
                SaveAndUpdateBtn = new javax.swing.JButton();
                jLabel7 = new javax.swing.JLabel();

                setUndecorated(true);
                getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                Quantity.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                Quantity.setText("Quantity");

                pcs.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                pcs.setText("pcs");

                cboCategory.setBackground(new java.awt.Color(194, 202, 149));
                cboCategory.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                cboCategory.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                cboCategoryActionPerformed(evt);
                        }
                });

                jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel5.setText("Menu");

                AddMenuItembtn.setBackground(new java.awt.Color(146, 149, 105));
                AddMenuItembtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/Add.png"))); // NOI18N
                AddMenuItembtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                AddMenuItembtnActionPerformed(evt);
                        }
                });

                txtName.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtNameActionPerformed(evt);
                        }
                });

                jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel1.setText("Add");

                back.setBackground(new java.awt.Color(146, 149, 105));
                back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/backbtn.png"))); // NOI18N
                back.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                backActionPerformed(evt);
                        }
                });

                jPanel8.setBackground(new java.awt.Color(194, 202, 149));

                jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                jLabel6.setText("Price");

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
                );
                jPanel8Layout.setVerticalGroup(
                        jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jSpinner2, javax.swing.GroupLayout.Alignment.TRAILING)
                );

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(55, 55, 55)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addGap(52, 52, 52)
                                                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(27, 27, 27)
                                                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(pcs, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(38, 38, 38)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(cboCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(37, 37, 37)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(AddMenuItembtn, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(123, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(21, 21, 21)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(AddMenuItembtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(cboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGap(43, 43, 43))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(2, 2, 2)
                                                                .addComponent(jLabel1)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(110, 110, 110)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(pcs, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18))))
                );

                getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 820, 180));

                jPanel3.setBackground(new java.awt.Color(255, 255, 255));
                jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                DashboardCashierLabel1.setBackground(new java.awt.Color(194, 202, 149));
                DashboardCashierLabel1.setFont(new java.awt.Font("Source Han Sans CN Bold", 1, 18)); // NOI18N
                DashboardCashierLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                DashboardCashierLabel1.setText("Categories");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(136, Short.MAX_VALUE)
                                .addComponent(DashboardCashierLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(DashboardCashierLabel1)
                                .addContainerGap(17, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 70, 250, 60));

                jPanel5.setBackground(new java.awt.Color(255, 255, 255));
                jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                jPanel7.setBackground(new java.awt.Color(146, 149, 105));
                jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                tblInventory.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null}
                        },
                        new String [] {
                                "Id", "Product", "Qty.", "Price", "Classifier"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, true, true, true, true
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                tblInventory.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
                tblInventory.setRowHeight(30);
                jScrollPane1.setViewportView(tblInventory);

                btnClear.setBackground(new java.awt.Color(146, 149, 105));
                btnClear.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                btnClear.setForeground(new java.awt.Color(255, 255, 255));
                btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/Remove.png"))); // NOI18N
                btnClear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                btnClear.setBorderPainted(false);
                btnClear.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnClearActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE))
                                .addContainerGap())
                );
                jPanel7Layout.setVerticalGroup(
                        jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))
                );

                jLabel9.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
                jLabel9.setText(">      Stocks");

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(696, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 30, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 820, 360));

                jPanel6.setBackground(new java.awt.Color(194, 202, 149));
                jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_BOTTOM, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(152, 160, 101))); // NOI18N

                tblCategory.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null},
                                {null},
                                {null},
                                {null},
                                {null},
                                {null}
                        },
                        new String [] {
                                "Categories"
                        }
                ));
                tblCategory.setRowHeight(30);
                jScrollPane2.setViewportView(tblCategory);

                txtAddCategory.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtAddCategoryActionPerformed(evt);
                        }
                });

                btcAddCategory.setBackground(new java.awt.Color(146, 149, 105));
                btcAddCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/Add.png"))); // NOI18N
                btcAddCategory.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btcAddCategoryActionPerformed(evt);
                        }
                });

                btnRemoveCategory.setBackground(new java.awt.Color(146, 149, 105));
                btnRemoveCategory.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                btnRemoveCategory.setForeground(new java.awt.Color(255, 255, 255));
                btnRemoveCategory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/Remove.png"))); // NOI18N
                btnRemoveCategory.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                btnRemoveCategory.setBorderPainted(false);
                btnRemoveCategory.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnRemoveCategoryActionPerformed(evt);
                        }
                });

                btnSaveCategory.setBackground(new java.awt.Color(146, 149, 105));
                btnSaveCategory.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                btnSaveCategory.setText("Save");
                btnSaveCategory.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnSaveCategoryActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(txtAddCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btcAddCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(btnSaveCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnRemoveCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13)))
                                .addContainerGap(17, Short.MAX_VALUE))
                );
                jPanel6Layout.setVerticalGroup(
                        jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtAddCategory)
                                        .addComponent(btcAddCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnSaveCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnRemoveCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                                .addGap(31, 31, 31))
                );

                getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 130, 250, 480));

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
                                .addContainerGap(565, Short.MAX_VALUE)
                                .addComponent(DashboardCashierLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(DashboardCashierLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 820, 70));

                jPanel9.setBackground(new java.awt.Color(146, 149, 105));

                LogOut.setBackground(new java.awt.Color(146, 149, 105));
                LogOut.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                LogOut.setForeground(new java.awt.Color(255, 255, 255));
                LogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/logout.png"))); // NOI18N
                LogOut.setBorderPainted(false);
                LogOut.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                LogOutMouseClicked(evt);
                        }
                });
                LogOut.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                LogOutActionPerformed(evt);
                        }
                });

                jPanel11.setBackground(new java.awt.Color(146, 149, 105));

                jLabel4.setBackground(new java.awt.Color(146, 149, 105));
                jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
                jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/UserAdmin.png"))); // NOI18N
                jLabel4.setText("Admin");

                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                jPanel11.setLayout(jPanel11Layout);
                jPanel11Layout.setHorizontalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addContainerGap(122, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addContainerGap())
                );
                jPanel11Layout.setVerticalGroup(
                        jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(147, 147, 147)
                                .addComponent(LogOut)
                                .addGap(26, 26, 26))
                );
                jPanel9Layout.setVerticalGroup(
                        jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addGap(13, 13, 13)
                                                .addComponent(LogOut)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
                );

                getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 0, 250, 70));

                jPanel10.setBackground(new java.awt.Color(194, 202, 149));
                jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/Cafe'Logo.png"))); // NOI18N
                jLabel2.setText("jLabel2");

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                        jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
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

                SaveAndUpdateBtn.setBackground(new java.awt.Color(146, 149, 105));
                SaveAndUpdateBtn.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                SaveAndUpdateBtn.setForeground(new java.awt.Color(255, 255, 255));
                SaveAndUpdateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cafe_System/PNG/SaveUpdate.png"))); // NOI18N
                SaveAndUpdateBtn.setBorderPainted(false);
                SaveAndUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                SaveAndUpdateBtnActionPerformed(evt);
                        }
                });

                jLabel7.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
                jLabel7.setText("SAVE & UPDATE");

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(SaveAndUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel7))
                                .addContainerGap(23, Short.MAX_VALUE))
                );
                jPanel4Layout.setVerticalGroup(
                        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(SaveAndUpdateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addContainerGap(131, Short.MAX_VALUE))
                );

                getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 180, 360));

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void btnRemoveCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveCategoryActionPerformed
        String selectedItem = structure.getCategoryTable().getValueAt(tblCategory.getSelectedRow(), 0).toString();
        System.out.println(selectedItem);
        structure.getCategoryTable().removeRow(tblCategory.getSelectedRow());
        data.removeCategory(data.getConnection(), selectedItem);
        fillUp();
        config();
    }//GEN-LAST:event_btnRemoveCategoryActionPerformed

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutActionPerformed
       
    }//GEN-LAST:event_LogOutActionPerformed

    private void LogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogOutMouseClicked
       
    }//GEN-LAST:event_LogOutMouseClicked

    private void AddMenuItembtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddMenuItembtnActionPerformed
        String slctCategory = cboCategory.getSelectedItem().toString();
        String name = txtName.getText();
        int qty = (int)jSpinner1.getValue();
        int price = (int)jSpinner2.getValue();
        System.out.println(slctCategory + " " + name + qty + price);
        data.pushMenu(data.getConnection(), name, price, qty, slctCategory);
        fillUp();

    }//GEN-LAST:event_AddMenuItembtnActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void cboCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCategoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCategoryActionPerformed

    private void btcAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcAddCategoryActionPerformed
       String addTxt = txtAddCategory.getText();
        System.out.println(addTxt);
        data.postCategory(data.getConnection(), addTxt);
        fillUp();
        config();
    }//GEN-LAST:event_btcAddCategoryActionPerformed

    private void txtAddCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddCategoryActionPerformed
        // TODO add your handling code here:
        String slctCategory = cboCategory.getSelectedItem().toString();
        String name = txtName.getText();
        int qty = (int)jSpinner1.getValue();
        int price = (int)jSpinner2.getValue();
        System.out.println(slctCategory + " " + name + qty + price);
        data.pushMenu(data.getConnection(), name, price, qty, slctCategory);
        fillUp();

    }//GEN-LAST:event_txtAddCategoryActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
         int selectedRowId = (int) structure.getInventoryTable().getValueAt(tblInventory.getSelectedRow(), 0);
        System.out.println(selectedRowId);
        structure.getInventoryTable().removeRow(tblInventory.getSelectedRow());
        data.removeMenu(data.getConnection(), selectedRowId);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSaveCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveCategoryActionPerformed
            ArrayList<String> categoryArr = data.getCategories(data.getConnection());
        int rowCount = structure.getCategoryTable().getRowCount();
        categoryArr.remove("All Items");
        System.out.println(rowCount);
        System.out.println(0<rowCount);
        String preValue, postValue;
        for (int i = 0; i < rowCount;i++){
            preValue = categoryArr.get(i);
            postValue = structure.getCategoryTable().getValueAt(i, 0).toString();
            if(!preValue.equals(postValue)){
                data.updateCategory(data.getConnection(), preValue, postValue);
                fillUp();
                config();
            }
            String category = categoryArr.get(i);
            String category2 = structure.getCategoryTable().getValueAt(i, 0).toString();
            System.out.println(category + " " + category2);
        }
        
    }//GEN-LAST:event_btnSaveCategoryActionPerformed

    private void SaveAndUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveAndUpdateBtnActionPerformed
        // TODO add your handling code here:
            int totalRowNum = structure.getInventoryTable().getRowCount();
        for (int i = 0; i < totalRowNum; i++) {
            Object id = structure.getInventoryTable().getValueAt(i, 0);
            Object name =  structure.getInventoryTable().getValueAt(i, 1).toString();
            Object qty =  structure.getInventoryTable().getValueAt(i, 2);
            Object price =  structure.getInventoryTable().getValueAt(i, 3);
            String classify = structure.getInventoryTable().getValueAt(i, 4).toString();
            System.out.println(""+id);  
            System.out.println(""+name);
            System.out.println(""+qty);
            System.out.println(""+price);
            System.out.println(""+classify);           
            
            data.updateMenu(data.getConnection(), (int) id, name.toString(), (int) qty, (int) price, classify);
        }
        fillUp();
        config();
    }//GEN-LAST:event_SaveAndUpdateBtnActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        new User_Dashboard().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backActionPerformed
    class SpinnerRenderer extends DefaultTableCellRenderer {
        private final JSpinner spinner = new JSpinner();

        public SpinnerRenderer() {
            spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1)); // Customize the spinner as needed
            spinner.setBorder(null);
            spinner.setFocusable(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            spinner.setValue(value);
            return spinner;
        }
    }
    class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
        private final JSpinner spinner = new JSpinner();

        public SpinnerEditor() {
            spinner.setModel(new SpinnerNumberModel(0, 0, 1000, 1)); // Customize the spinner as needed
            spinner.setBorder(null);
            spinner.setFocusable(true);
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            spinner.setValue(value);
            return spinner;
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            return true;
        }

        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            return true;
        }
    }
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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                System.out.println(info.getName());
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inventory().setVisible(true);
            }
        });
    }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton AddMenuItembtn;
        private javax.swing.JLabel DashboardCashierLabel1;
        private javax.swing.JLabel DashboardCashierLabel2;
        private javax.swing.JButton LogOut;
        private javax.swing.JLabel Quantity;
        private javax.swing.JButton SaveAndUpdateBtn;
        private javax.swing.JButton back;
        private javax.swing.JButton btcAddCategory;
        private javax.swing.JButton btnClear;
        private javax.swing.JButton btnRemoveCategory;
        private javax.swing.JButton btnSaveCategory;
        private javax.swing.JComboBox<String> cboCategory;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel4;
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
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JSpinner jSpinner1;
        private javax.swing.JSpinner jSpinner2;
        private javax.swing.JLabel pcs;
        private javax.swing.JTable tblCategory;
        private javax.swing.JTable tblInventory;
        private javax.swing.JTextField txtAddCategory;
        private javax.swing.JTextField txtName;
        // End of variables declaration//GEN-END:variables
}
