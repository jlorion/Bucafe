package CafeTypeShit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataCom {

    public ArrayList<Map<String, Object>> getMenu(Connection conn){
    ArrayList<Map<String, Object>> allMenu= new ArrayList<>();
    
    String query = "SELECT * FROM menu";
    try {
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(query);
        while (rs.next())
        {
        Map<String, Object> menu = new HashMap<>();
        int id = rs.getInt("id");
        String itemName = rs.getString("Name");
        int price = rs.getInt("price");
        menu.put("ID", id);
        menu.put("Name", itemName);
        menu.put("Price", price);
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
}