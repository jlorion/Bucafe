
package Cafe_System;

import javax.swing.table.DefaultTableModel;
public class SalesStructure {
    private double subTotal=0;
    private double ppn=0;
    private double total=0;
    private DefaultTableModel inventoryTable = new DefaultTableModel();
    private DefaultTableModel categoryTable = new DefaultTableModel();
    public void setCategoryTable(DefaultTableModel categoryTable) {
        this.categoryTable = categoryTable;
    }
    public DefaultTableModel getCategoryTable() {
        return categoryTable;
    }
    public void setInventoryTable(DefaultTableModel inventoryTable) {
        this.inventoryTable = inventoryTable;
    }
    public DefaultTableModel getInventoryTable() {
        return inventoryTable;
    }
    private DefaultTableModel tabel = new DefaultTableModel();  
    public SalesStructure(){
        getInventoryTable().addColumn("ID");
        getInventoryTable().addColumn("Product");
        getInventoryTable().addColumn("Qty.");
        getInventoryTable().addColumn("Price");
        getInventoryTable().addColumn("Category");
        getCategoryTable().addColumn("Categories");
        getTabel().addColumn("Menu");
        getTabel().addColumn("Quantity");
        getTabel().addColumn("Price");
        getTabel().addColumn("Unit");
        getTabel().addColumn("Total Price");
        getTabel().addColumn("ID");
    }
    public double countSubtotal(){
        subTotal=0;
        for (int i=0;i<tabel.getRowCount();i++){
            subTotal=subTotal+Double.parseDouble(tabel.getValueAt(i, 4).toString());
        }
        return subTotal;
    } 
    public double countPPN(){
        ppn=subTotal*0.1;
        ppn = Math.ceil(ppn);
        return ppn;
    }   
    public double countTotal(){
        total=subTotal+ppn;
        return total;
    }
    public double getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    public double getPpn() {
        return ppn;
    }
    public void setPpn(double ppn) {
        this.ppn = ppn;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public DefaultTableModel getTabel() {
        return tabel;
    }
    public void setTabel(DefaultTableModel tabel) {
        this.tabel = tabel;
    }
}
