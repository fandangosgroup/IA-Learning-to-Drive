package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnetion(){
		Connection conn = null;
		try{
			/**
			 * Essa merda aqui, eu setei o endereço do meu mysql pra testar 
			 * Preciso saber qual o endereço que vai estar hospedado o banco :p
			 */
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dbprojeto?user=root&useTimezone=true&serverTimezone=UTC");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}
