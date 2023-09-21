
package CafeTypeShit;

public class ItemStructure {
    private String nameSale;
    private double price;
    private String unit;
    private int id;
    private String category;
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


    public ItemStructure(String nameSale, String unit, double price, int id, String category){
        this.nameSale = nameSale;
        this.unit = unit;
        this.price = price;
        this.id = id;
        this.category = category;

    }

    @Override
    public String toString() {
        return this.nameSale;
    }
   
}
