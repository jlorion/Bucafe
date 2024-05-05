 package Cafe_System;

import Cafe_System.ItemStructure;
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
import javax.xml.crypto.Data;

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
        ItemStructure menu = new ItemStructure(itemName, "pcs", price, id, classify, inventory);
        allMenu.add(menu);       
      }
      st.close();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return allMenu;
  }
  
  public int postPurchase(Connection Conn, double total, double cash, double moneyback, int employeeId){
    String query = "INSERT INTO purchases (employeeId,total, cash, moneyback) VALUES (?, ?, ?, ?)";
    int id = 0;
    try (PreparedStatement preparedStatement = Conn.prepareStatement((query), Statement.RETURN_GENERATED_KEYS)) {
	preparedStatement.setInt(1, employeeId);    
      preparedStatement.setDouble(2, total); // Set the value for column1
      preparedStatement.setDouble(3, cash); // Set the value for column2
      preparedStatement.setDouble(4, moneyback); // Set the value for column3
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
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return id;
  }
  public void pushMenu(Connection con, String name, int price, int qty, String category){
    String query = "INSERT INTO menu (name, price, inventory, classifier) VALUES (?, ?, ?, ?)";
    try(PreparedStatement ptmt = con.prepareStatement((query))) {
      ptmt.setString(1, name);
      ptmt.setInt(2, price);
      ptmt.setInt(3, qty);
      ptmt.setString(4, category);
      int rowsAffected = ptmt.executeUpdate();
      System.out.println(rowsAffected + " row(s) inserted.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void pushOrders(Connection con, int purchase_id, int menu_id, int amount, double item_total){
    String query = "INSERT INTO purchase_items (purchase_id, menu_id, amount, item_total) VALUES (?, ?, ?, ?)";
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
  public ArrayList<Map<String, Object>> getLatestPurchase(Connection con, int purchase_id){
    String query = "SELECT p.total, p.cash, p.moneyback, p.datentime, amount, item_total, m.Name,"
	    + " m.price, m.id FROM purchase_items INNER JOIN purchases as p ON purchase_items.purchase_id=p.id "
	    + "LEFT JOIN menu as m On purchase_items.menu_id=m.id WHERE purchase_items.purchase_id="+purchase_id;
    ArrayList<Map<String, Object>> purchaseList = new ArrayList<>();
    try {
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(query);
      while (rs.next()) {
        Map<String, Object> purchaseMap = new HashMap<>();
        purchaseMap.put("total",rs.getInt("total"));
        purchaseMap.put("cash",rs.getInt("cash"));
        purchaseMap.put("change",rs.getInt("moneyback"));
        purchaseMap.put("qty",rs.getInt("amount"));
        purchaseMap.put("price",rs.getInt("price"));
        purchaseMap.put("item_total",rs.getInt("item_total"));
        purchaseMap.put("datentime",rs.getString("datentime"));
        purchaseMap.put("name",rs.getString("Name"));
        purchaseMap.put("menu_id", rs.getString("id"));
        purchaseList.add(purchaseMap);        
      }
      return purchaseList; 
    } catch (Exception e) {
      // TODO: handle exception
      System.out.println(e);
    }
    return null;
  }
  public void updateCategory(Connection con, String preValue, String postValue){
    String updateMenu = "UPDATE menu SET classifier = ? WHERE classifier = ?";
    String updateCategory = "UPDATE categorization SET Class_name = ? WHERE Class_name = ?";
    try { 
      PreparedStatement prstMenu = con.prepareStatement(updateMenu);
      PreparedStatement prstCategory = con.prepareStatement(updateCategory);
      prstMenu.setString(1, postValue);
      prstMenu.setString(2, preValue);
      prstCategory.setString(1, postValue);
      prstCategory.setString(2, preValue);
      prstCategory.executeUpdate();
      prstMenu.executeUpdate();
      System.out.println("we guchi");
    } catch (Exception e) {
      // TODO: handle exception
      System.err.println(e);
    }
  }
  public void postCategory(Connection con, String newCategory){
    String query = "INSERT INTO categorization (Class_name) VALUE (?)";
    try {
      PreparedStatement prst = con.prepareStatement(query);
      prst.setString(1, newCategory);
      prst.executeUpdate();
    } catch (Exception e) {
      System.out.println("error occured");
      System.err.println(e);
    }
  }
  public void removeCategory(Connection con, String item){
    String query = "DELETE FROM categorization WHERE Class_name = ?";
    try {
      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setString(1, item);
      pstmt.execute();
      con.close();
    } catch (Exception e) {
      System.err.println(e);
    }
  }
  public void removeMenu(Connection con, int id){
    String query  = "DELETE FROM menu WHERE id = ?";
    try {
      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setInt(1, id);
      pstmt.execute();
      con.close();
    } catch (Exception e) {
      System.out.println(e);    
    }
  }

  public void updateMenu(Connection conn, int id, String name, int qty, int price, String classifier){
    String query = "UPDATE menu SET Name = ?, price = ?, inventory = ?, classifier = ? WHERE id = ?";
    try(PreparedStatement pstmt = conn.prepareStatement(query)) {
      pstmt.setString(1, name);
      pstmt.setInt(2, price);
      pstmt.setInt(3, qty);
      pstmt.setString(4, classifier);
      pstmt.setInt(5, id);
      pstmt.executeUpdate();
      System.out.println("Done!! updates");
      conn.close();
    } catch (Exception e) {
      System.err.println("error: " + e);
    }
  }
  public void updateInventory(Connection conn, int id, int afterCount){
    String query = "UPDATE menu SET inventory = ? WHERE id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(query)){
      pstmt.setInt(1, afterCount);
      pstmt.setInt(2, id);
      pstmt.executeUpdate();
      System.out.println("Update done!");
    } catch (Exception e) {
        System.err.println(e);
    }
  }
  
  
  public Map<String, Object> verifyEmployee(Connection con, String user, int access_code) throws Exception {
	  
	String query = "Select * from employee where last_name=\""+user+"\" or first_name=\""+user+"\"";
	Map<String, Object> employeeData = new HashMap<String, Object>();
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(query);

	while(rs.next()){
		employeeData.put("employeeId", rs.getInt("id"));
		employeeData.put("adminAccess", rs.getBoolean("admin_access"));
		employeeData.put("accessCode", rs.getInt("access_code"));
	}
	
	if((int)employeeData.get("accessCode")==access_code){
		return employeeData;
	}
      
	return null;
  }
       
}