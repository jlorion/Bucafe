
package Cafe_System;

public class ItemStructure {
    private String nameSale;
    private int price;
    private String unit;
    private int id;
    private String category;
    private int qty;
    public void setQty(int qty) {
        this.qty = qty;
    }
    public int getQty() {
        return qty;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getNameSale() {
        return nameSale;
    }
    public void setNameSale(String nameSale) {
        this.nameSale = nameSale;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public ItemStructure(String nameSale, String unit, int price, int id, String category, int qty){
        this.nameSale = nameSale;
        this.unit = unit;
        this.price = price;
        this.id = id;
        this.category = category;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return this.nameSale;
    }
   
}
