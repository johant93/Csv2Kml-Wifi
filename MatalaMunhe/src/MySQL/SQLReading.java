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

import Algorithme.Algorithme2;
import Tools.DataBase;
import wifi_tools.Wifi;

public class SQLReading {

	private static Connection connection = null;
	private PreparedStatement preStatement;
	private Statement statement;
	private ResultSet result;

	
	public static boolean isConnected (Identifiers ident){ 
		try {
			connection = DriverManager.getConnection(ident.getUrl().toString(),ident.getUSER(),ident.getPASSWORD());
			System.out.println("Connected to Database.");
			return true ;
		} catch (SQLException e) {
	            System.out.println("ERROR: Unable to Connect to Database.");
	      		return false ;
        }
	}
	public static ArrayList<Wifi> getWifisList (Identifiers ident){
		ArrayList<Wifi> wifisList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		
			try {
				connection = DriverManager.getConnection(ident.getUrl().toString(),ident.getUSER(),ident.getPASSWORD());
			stmt = (Statement) connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM "+ident.getTableName());
			// build an arraylist of wifi for each line 
			while(rs.next()) {
				int nbofwifi = Integer.parseInt(rs.getString(7));
				int nbofColumn = 2*nbofwifi + 7;
				Algorithme2.emptyIn(LineTolist(getline(rs,nbofColumn)), wifisList);
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
			return wifisList ;
		}
	
	public static int getMaxId(Identifiers ident) {
		Statement stmt = null;
		ResultSet rs = null;
		int ind=0;

		try {     
			connection = DriverManager.getConnection(ident.getUrl().toString(),ident.getUSER(),ident.getPASSWORD());
			stmt = (Statement) connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM "+ident.getTableName());
			while (rs.next()) {
				ind++;
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
		return ind;
	}
	public static String getline(ResultSet rs,int nbofColumn) throws SQLException{
		String ans = "";
		for (int i = 1; i <= nbofColumn; i++) {
			ans += rs.getString(i)+",";
		}
		return ans ;
	}
	public static ArrayList<Wifi> LineTolist(String line){
		ArrayList<Wifi> templist = new ArrayList<Wifi>();
		String[] data = line.split(",");
		int nbofwifi,step;
		if(data.length >= 9)	{
			nbofwifi = Integer.parseInt(data[6]);
			step = 0;

			for (int i = 0; i < nbofwifi ; i++) {	
				Wifi wifi = new Wifi(data[1],data[2],data[3],data[4],data[5],null,data[7+step],null,data[8+step]);
				templist.add(wifi);
				step += 2;
			}
		}
		return templist ;	
	}
}