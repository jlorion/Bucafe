package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import CafeTypeShit.DataCom;

public class tessting {
    public static void main(String[] args) {
        DataCom db = new DataCom();
        
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root",  "");
            System.out.println(db.getMenu(con));
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
