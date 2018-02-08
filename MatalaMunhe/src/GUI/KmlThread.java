package GUI;

import java.util.ArrayList;

import Tools.DataBase;
import Tools.WriteWifiKml;
import wifi_tools.Wifi;

/**
 * this class create KML files
 * @author joh
 *
 */
public class KmlThread extends Thread{
	private DataBase database ;
	private String name;
	
	public KmlThread (DataBase database,String name){
		 this.database = database;
		 this.name = name;
	}

	@Override
	public void run() {
		
		synchronized (database) {
			
		
		String newFileName = this.name+".kml" ;
      WriteWifiKml wkml = new WriteWifiKml();
      ArrayList <Wifi> wifiarr = database.getDatabase() ;
		if(wifiarr.isEmpty()) System.out.println("your database is empty");
		else 
		wkml.createWifiKml(wifiarr, newFileName);
		}
	}
}
