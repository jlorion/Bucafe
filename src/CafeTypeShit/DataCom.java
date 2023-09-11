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
  
  public void postPurchase(Connection Conn, double total, double cash, double moneyback){

    String query = "INSERT INTO purchases (total, cash, moneyback) VALUES (?, ?, ?)";

    try (PreparedStatement preparedStatement = Conn.prepareStatement(query)){
    preparedStatement.setDouble(1, total); // Set the value for column1
    preparedStatement.setDouble(2, cash); // Set the value for column2
    preparedStatement.setDouble(3, moneyback); // Set the value for column3
    
    int rowsAffected = preparedStatement.executeUpdate();
 
    System.out.println(rowsAffected + " row(s) inserted.");
} catch (SQLException e) {
    e.printStackTrace();
}

  }
}