package MySQL;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;


public class testMySQL {

	private static Connection connection = null;
	private PreparedStatement preStatement;
	private Statement statement;
	private ResultSet result;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URL url = new URL("localhost","8889", "Wifi_scan");
		Identifiers ident = new Identifiers(url,"root","root","wifi");
		printmyDb(ident);
		System.out.println(SQLReading.isConnected(ident));
	}
	public static void printmyDb (Identifiers ident) {
		Statement stmt = null;
		ResultSet rs = null;

		try {     
			connection = DriverManager.getConnection(ident.getUrl().toString(),ident.getUSER(),ident.getPASSWORD());
			stmt = (Statement) connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM "+ident.getTableName());
			while (rs.next()) {
				System.out.println(getline(rs, 6));
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLReading.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (stmt != null) { stmt.close(); }
				if (connection != null) { connection.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(SQLReading.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		
	}
	public static String getline(ResultSet rs,int nbofColumn) throws SQLException{
		String ans = "";
		for (int i = 1; i <= nbofColumn; i++) {
			ans += rs.getString(i)+",";
		}
		return ans ;
	}

}
