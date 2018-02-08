package MySQL;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Statement;

import Tools.Point3D;
import wifi_tools.WiFi_AP;
import wifi_tools.WiFi_Scan;

public class SQLInsert {

	/*

    private static String _ip = "5.29.193.52";	 
	private static String URL = "jdbc:mysql://"+_ip+":3306/oop_course_ariel";
	private static String USER = "oop1";
	private static String PASSWORD = "Lambda1();";
	 */
	
	private static Connection connection = null;
	private PreparedStatement preStatement;
	private Statement statement;
	private ResultSet result;
    SQLReading SQLr = new SQLReading();
	/*
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		//mysql.createTable();
		int max_id = getMaxId(ident);

		System.out.println("max id is:"+max_id);
		//WiFi_Scan ws = new WiFi_Scan(t, id, p);
	}
	*/
	
	/**
	 * @param WifiScanline 
	 * this method insert a table in database from a Wifi Scan line 
	 */
	public void InsertTable (Identifiers ident,String WifiScanline ){
		int max_id = SQLr.getMaxId(ident);
		WiFi_Scan ws = new WiFi_Scan(WifiScanline);
		insert_One_table(ident,max_id, ws);
	}

	public static int testTable (Identifiers ident) {
		Statement stmt = null;
		ResultSet rs = null;
		int max_id = -1;

		try {     
			connection = DriverManager.getConnection(ident.getUrl().toString(),ident.getUSER(),ident.getPASSWORD());
			stmt = (Statement) connection.createStatement();
			rs = stmt.executeQuery("SELECT UPDATE_TIME FROM information_schema.tables WHERE TABLE_SCHEMA ='"+ident.getUrl().getDbName()+"' AND TABLE_NAME = '"+ident.getTableName()+"'");
			if (rs.next()) {
				System.out.println("**** Update: "+rs.getString(1));
			}

			PreparedStatement pst = connection.prepareStatement("SELECT * FROM "+ident.getTableName());
			rs = pst.executeQuery();
			//print the database
			int ind=0;
			while (rs.next()) {
				int size = rs.getInt(7);
				int len = 7+2*size;
				if(ind%100==0) {
					for(int i=1;i<=len;i++){
						System.out.print(ind+") "+rs.getString(i)+",");
					}
					System.out.println();
				}
				ind++;
				if ( ind > max_id) max_id = ind;
			}
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLInsert.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (stmt != null) { stmt.close(); }
				if (connection != null) { connection.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(SQLInsert.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return max_id;
	}

	/** 
	 * this method create a new TABLE in SQL database
	 */
	/*	private void createTable() {
		String myTableName = "CREATE TABLE db_Assignement4 ("
				+ "id INT(64) NOT NULL AUTO_INCREMENT,"
				+ "time VARCHAR(45),"  
				+ "device VARCHAR(64)," 
				+ "lat DOUBLE,"
				+ "lon DOUBLE,"
				+ "alt DOUBLE,"
				+ "mac_0 VARCHAR(20),rssi_0 TINYINT(1),"+"mac_1 VARCHAR(20),rssi_1 TINYINT(1),"+"mac_2 VARCHAR(20),rssi_2 TINYINT(1),"+"mac_3 VARCHAR(20),rssi_3 TINYINT(1),"+"mac_4 VARCHAR(20),rssi_4 TINYINT(1),"
				+ "mac_5 VARCHAR(20),rssi_5 TINYINT(1),"+"mac_6 VARCHAR(20),rssi_6 TINYINT(1),"+"mac_7 VARCHAR(20),rssi_7 TINYINT(1),"+"mac_8 VARCHAR(20),rssi_8 TINYINT(1),"+"mac_9 VARCHAR(20),rssi_9 TINYINT(1),"+"PRIMARY KEY (`id`))";


		try {
			//  Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(URL,USER,PASSWORD);
			statement = (Statement) connection.createStatement();
			//The next line has the issue
			statement.executeUpdate(myTableName);
			System.out.println("Table Created");
		}
		catch (SQLException e ) {
			System.out.println("An error has occurred on Table Creation");
		}    
	}
	 */
	public static void insert_One_table(Identifiers ident,int max_id, WiFi_Scan ws) {
		Statement st = null;
		ResultSet rs = null;

		try {     
			connection = DriverManager.getConnection(ident.getUrl().toString(),ident.getUSER(),ident.getPASSWORD());
			st = (Statement) connection.createStatement();

			int id = 1+max_id;
			String sql = creat_sql(ident,ws, id);
			PreparedStatement pst = connection.prepareStatement(sql);
			System.out.println(sql);
			pst.execute();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(SQLInsert.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
		} finally {
			try {
				if (rs != null) {rs.close();}
				if (st != null) { st.close(); }
				if (connection != null) { connection.close();  }
			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(SQLInsert.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
	}

	private static String creat_sql(Identifiers ident,WiFi_Scan w, int id) {
		String ans = "INSERT INTO"+ident.getTableName()+"(ID,time, device,lat,lon,alt, number_of_ap";
		String str1 = "", str2="";
		Point3D pos = w.get_pos();
		int n = w.size();
		String in = " VALUES ("+id+",'"+w.get_time()+"','"+w.get_device_id()+"',"+pos.x()+","+pos.y()+","+pos.z()+","+n; 
		for(int i=0;i<n;i++) {
			str1+=",mac"+i+",rssi"+i;
			WiFi_AP a = w.get(i);
			str2+=",'"+a.get_mac()+"',"+(int)a.get_rssi();
		}
		ans +=str1+")"+in+str2+")";    	
		return ans;
	}

}

