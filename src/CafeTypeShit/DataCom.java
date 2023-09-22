package CafeTypeShit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.ExpandVetoException;

public class DataCom {

  public Connection getConnection(){
    Connection con;
     try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cafeting", "root",  "");
            System.out.println("connected");
            return con;
      }catch(Exception ex){
        System.err.println(ex);
      }


    return null;
      



  }

    public ArrayList<ItemStructure> getMenu(Connection conn){
    ArrayList<ItemStructure> allMenu= new ArrayList<>();
    
    String query = "SELECT * FROM menu";
    try {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
        
        int id = rs.getInt("id");
        String itemName = rs.getString("Name");
        int price = rs.getInt("price");
        int inventory = rs.getInt("inventory");
        String classify = rs.getString("classifier");
        ItemStructure menu = new ItemStructure(itemName, "pcs", price, id, classify);
        allMenu.add(menu);       
      }
      st.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return allMenu;
  }
  
  public int postPurchase(Connection Conn, double total, double cash, double moneyback){

    String query = "INSERT INTO purchases (total, cash, moneyback) VALUES (?, ?, ?)";
    
    int id = 0;
    try (PreparedStatement preparedStatement = Conn.prepareStatement((query), Statement.RETURN_GENERATED_KEYS)) {
      preparedStatement.setDouble(1, total); // Set the value for column1
      preparedStatement.setDouble(2, cash); // Set the value for column2
      preparedStatement.setDouble(3, moneyback); // Set the value for column3

      int rowsAffected = preparedStatement.executeUpdate();
      try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          id = generatedKeys.getInt(1);
          // System.out.println(id);
          return id;
        } else {
          throw new SQLException("Creating user failed, no ID obtained.");
        }
      }
      // System.out.println(rowsAffected + " row(s) inserted.");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return id;

  }


  public void pushOrders(Connection con, int purchase_id, int menu_id, int amount, double item_total){

    String query = "INSERT INTO orders (purchase_id, menu_id, amount, item_total) VALUES (?, ?, ?, ?)";

    try(PreparedStatement ptmt = con.prepareStatement((query))) {
      ptmt.setInt(1, purchase_id);
      ptmt.setInt(2, menu_id);
      ptmt.setInt(3, amount);
      ptmt.setDouble(4, item_total);
      int rowsAffected = ptmt.executeUpdate();
      System.out.println(rowsAffected + " row(s) inserted.");
   
      
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
  public ArrayList<String> getCategories(Connection conn){

    String query = "SELECT * FROM categorization";
    ArrayList<String> categories = new ArrayList<>();

    try {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
        while (rs.next()){
          String category = rs.getString("Class_name");
          categories.add(category);
        }
        return categories;
      }catch(Exception err){
        System.err.println(err);
      }
    return null;
    
  }
  
  //method for pulling latest data for print job
  public ArrayList<Object> getLatestPurchase(Connection con, int purchase_id){

    
    return null;
  }

}