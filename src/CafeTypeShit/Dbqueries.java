package CafeTypeShit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Dbqueries {
    
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


    
}

