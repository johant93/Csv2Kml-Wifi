import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import org.boehn.kmlframework.kml.Document;
import org.boehn.kmlframework.kml.Feature;
import org.boehn.kmlframework.kml.Folder;
import org.boehn.kmlframework.kml.Geometry;
import org.boehn.kmlframework.kml.Icon;
import org.boehn.kmlframework.kml.IconStyle;
import org.boehn.kmlframework.kml.Kml;
import org.boehn.kmlframework.kml.KmlException;
import org.boehn.kmlframework.kml.Placemark;
import org.boehn.kmlframework.kml.Style;
import org.boehn.kmlframework.kml.StyleSelector;
import org.boehn.kmlframework.kml.TimeStamp;

/***
 *  this class WriteWifikml implements the Interface WriteKml.
 */

public class WriteWifiKml {

	private static Document doc = new Document();
	private static Folder folder = new Folder();
	public WriteWifiKml() {

	}
	/**
	 * /  create new Kml file
	 * @param array-Mac arraylist
	 * @param newKmlname- the name of the new file
	 */
	public void createMacKml(ArrayList<Mac> array,String newKmlname) {
		initialisation();
		int i = 0;
		while(i<array.size()) {
			inputMac(array.get(i));
			i++;
		}
		createFile(newKmlname);
	}
	/**
	 * /  create new Kml file
	 * @param array-Wifi arraylist
	 * @param newKmlname- the name of the new file
	 */
	public void createWifiKml(ArrayList<Wifi> array,String newKmlname) {
		initialisation();
		int i = 0;
		while(i<array.size()) {
			inputWifi(array.get(i));
			i++;
		}
		createFile(newKmlname);
	}
	 
	/**
	 * write the style of each placemark on our document
	 * (color/sign to display on the map at the wifis location )
	 * @param color
	 */
	public static void writeStyle (String color){
		Style style = new Style();
		style.setId(color);
		IconStyle iconstyle = new IconStyle();
		style.setIconStyle(iconstyle);
		iconstyle.setIconHref("http://maps.google.com/mapfiles/ms/icons/"+color+"-dot.png");
		doc.addStyleSelector(style);

	}
	
	/**
	 * we are initialize our kml file by writing the sign and creating a new folder (Wifi networks)
	 * 
	 */
	public void initialisation() {

		writeStyle("red");
		writeStyle("green");
		writeStyle("yellow");   
		folder.setName("Wifi Networks");

	}
		/***
	 * the function  choose a color for the Wifi
	 * @param wifi
	 * @return the StyleUrl that correspond to by the signal
	 */
	public static String getstyleUrl (Wifi wifi){
		String red = "#red", green = "#green", yellow = "#yellow";
		int signal = Integer.parseInt(wifi.getSignal());
		if (signal <= -90 ) return red ;
		else if (signal >= -90 && signal <= -70 ) return yellow ;
		else  return green ;
	}
	/**
	 * @param temp- wifi's time
	 * @return the required timestamp's format YYYY-MM-DDTHH:MM:SSZ
	 */
	public static String timestampformat (String temp){
		String timestampf = "",YYYY= "",MM ="",DD="",HH="",mm="",SS="00";
		char [] ch = temp.toCharArray();
		int ascii = 0;
		for (int i = 0; i < ch.length; i++){
			ascii = (int)ch[i];
			if ( ascii < 48 || ascii > 57) ch[i]=','; 
		}
		temp = new String(ch) ;
		String [] date = temp.split(",");
		if ( date[2].length() == 4 ){
		YYYY = date[2];
		DD = date[0];
		}
		else {
			YYYY = date[0];
			DD = date[2];
		}
		MM = date[1];
		HH = date[3];
		mm = date[4];
		if (date.length==6)  SS = date[5];
		timestampf = YYYY+"-"+MM+"-"+DD+"T"+HH+":"+mm+":"+SS+"Z";
		return timestampf;
	}
	
	/**
	 *  here inputWifi create our Placemark with the Wifi data and add him to our folder.
	 * @param o- get a wifi object and create here placemark 
	 */
	public void inputWifi(Wifi wf) {
		Placemark placemark = new Placemark(wf.getSsid());
		TimeStamp timestamp = new TimeStamp(timestampformat(wf.getTime())) ;
		//timestamp.setWhen(timestampformat(wf.time));
		placemark.setTimePrimitive(timestamp);
		placemark.setDescription("![CDATA[BSSID:"+wf.getMac()+"\nId: "+wf.getId()+"\nFrequency:"+wf.getFreq()+"\nsignal: "+wf.getSignal()+"\ndate: "+wf.getTime() );
		placemark.setStyleUrl(getstyleUrl(wf));
		double Lon,Lat ;
		Lon = Double.parseDouble(wf.getLon());
		Lat = Double.parseDouble(wf.getLat());
		placemark.setLocation(Lon,Lat);
		folder.addFeature(placemark);
	}
	/**
	 *   inputMac create our Placemark with the Mac data and add him to our folder.
	 * @param o- get a Mac object and create here placemark 
	 */
	public void inputMac(Mac mac) {
    
		Placemark placemark = new Placemark(mac.getSsid());
		placemark.setDescription("![CDATA[BSSID:"+mac.getMac()+"\nChannel: "+mac.getChannel()+"\nsignal: "+mac.getSignal()+"\ndate: "+mac.getTime() );
		double Lon,Lat ;
		Lon = Double.parseDouble(mac.getLon());
		Lat = Double.parseDouble(mac.getLat());
		placemark.setLocation(Lon,Lat);
		folder.addFeature(placemark);
	}

	
	/**
	 *createFile create a kml file by adding the folder to the doc and the doc to the new Kml file. 
	 * @param Filename
	 */
	public void createFile(String Filename)  {
		Kml kml = new Kml();	
		doc.addFeature(folder); 
		kml.setFeature(doc);
		try {
			kml.createKml(Filename);
		} catch (KmlException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
