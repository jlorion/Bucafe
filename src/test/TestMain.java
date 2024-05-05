/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package test;

import Cafe_System.DataCom;
import java.sql.Connection;
import java.util.Map;
/**
 *
 * @author jlorion
 */


public class TestMain {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		DataCom datacom = new DataCom();
		
		
		Connection con = datacom.getConnection();
		
		try{
			
			Map<String, Object> result = datacom.verifyEmployee(con, "Orion", 69420);
			
			System.out.println(result.get("employeeId"));
			
		}catch (Exception e){
			
			System.err.println("Error: "+e);
			
		}
		
	}
	
}
