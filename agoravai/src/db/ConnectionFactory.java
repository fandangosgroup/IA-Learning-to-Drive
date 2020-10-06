package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnetion(){
		Connection conn = null;
		try{
			conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-05.cleardb.net/heroku_b481894670aeac7?user=baaf8787ff1507&password=c9b4bc164303c2a&useTimezone=true&serverTimezone=UTC");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}
