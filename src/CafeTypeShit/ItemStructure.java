
package CafeTypeShit;

public class ItemStructure {
    private String nameSale;
    private double price;
    private String unit;
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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


    public ItemStructure(String nameSale, String unit, double price){
        this.nameSale = nameSale;
        this.unit = unit;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.nameSale;
    }
   
}
