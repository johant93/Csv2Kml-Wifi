package wifi_tools;

import java.util.ArrayList;
import java.util.Date;

import Tools.Point3D;

import java.text.*;

/**
 * This class represents a simple WiFi scan of beacons in a given time, 
 * with: time, ID, Pos, #of wifi beacons, <ssid, mac. channel, rssi>.
 * 
 * 
  */
public class WiFi_Scan {
	public static final int MAX_APs = 10;
	private ArrayList<WiFi_AP> _aps;
	private boolean _is_wifi_scan;
	private String _time;
	private Point3D _pos;
	private String _device_id;
	
	// 0a:8d:db:6e:71:6d,888Corp,[WPA2-EAP-CCMP][ESS],2017-10-27 16:16:45,1,-79,32.16766121892341,34.80988155918773,39.018065819940986,3,WIFI
	public WiFi_Scan(String l) {
		String line = l.replaceAll(",,", ", ,");
		String[] ll = line.split(",");
		this._aps = new ArrayList<WiFi_AP>(1);
		if(line.endsWith("GSM") || line.endsWith("LTE")){
			this.set_is_wifi_scan(false);
		}
		else {
			this.set_is_wifi_scan(true);
			WiFi_AP ap = new WiFi_AP(ll[0],ll[1],Integer.parseInt(ll[4]),Double.parseDouble(ll[5]));
			_aps.add(ap);
		}
		this.set_time(ll[3]);
		String pos = ll[6]+" "+ll[7]+" "+ll[8];
		this.set_pos(new Point3D(pos));
		this._device_id="";
	}
	public WiFi_Scan(String t, String id, Point3D p) {
		this._time = t;
		this._device_id = id;
		this._pos = p;
		this._aps = new ArrayList<WiFi_AP>();
	}
	/**
	 * This is a sallow copy only to be used by sun-class.
	 * @param ws
	 */
	protected WiFi_Scan(WiFi_Scan ws) {
		this(ws.get_time(), ws.get_device_id(), ws.get_pos());
		this._aps = ws._aps;
	}
	public static WiFi_Scan init_from_46_csv_file(String line) {
		WiFi_Scan ans = null;
		line = line.replaceAll(",,",", ,");
		String[] ar = line.split(",");
		int size = ar.length;
		int n_of_wifi = 0;
		if(size>6) {n_of_wifi  =  new Integer(ar[5]);}
		if(size<5 || size>47){// || n_of_wifi*4+6!=size) {
			throw new RuntimeException("error: WiFi_Scan can not be init from String "+line);
		}
		Point3D p = new Point3D(0,0,0);
		try{
			p = new Point3D(ar[2]+" "+ar[3]+" "+ar[4]);
		}
		catch(Exception e) {;	} // ugly code - bug fix for testing set
		ans= new WiFi_Scan(ar[0], ar[1], p);
		int i=0;
		while (i<n_of_wifi) {
			int ind=6+i*4;
			WiFi_AP w = new WiFi_AP(ar[ind], ar[ind+1], new Integer(ar[ind+2]), new Double(ar[ind+3]));
			ans.add(w);
			i++;
		}
		ans.set_is_wifi_scan(true);
		return ans;
	}
	public String toString() {
		String s = "";
		int num = Math.min(size(), MAX_APs);
		for(int i=0;i<num;i++) {
			s+=","+get(i);
		}
		String pos = "null,null,null";
		if (this.get_pos()!=null){
			pos = this.get_pos().toFile();
		}
		return this.get_time()+","+this._device_id+","+pos+","+num+s;
	}
	
	public int size() {return this._aps.size();}
	public WiFi_AP get(int i) {return this._aps.get(i);}
	public void add(WiFi_AP a) {
		this._aps.add(a);
	}
	public Point3D get_pos() {
		return _pos;
	}

	public String get_time() {
		return _time;
	}
	/**
	 * 
	 * @param ot
	 * @return
	 */
	public boolean combine(WiFi_Scan ot) {
		boolean ans = false;
		if(ot!=null && ot.get_time().equals(this.get_time())) {
			this._aps.add(ot.get(0));
			ans = true;
		}
		return ans;
	}
/*	
  public void sort() {
		this._aps.sort(COMP);
	}
*/
	public String get_device_id() {return this._device_id;}
	public void set_device_id(String id) {this._device_id = id;}
	////////////////////////////////////////////////////////
	public void set_pos(Point3D _pos) {
		this._pos = _pos;
	}
	
	private void set_time(String _time) {
		this._time = _time;
	}
	public boolean is_is_wifi_scan() {
		return _is_wifi_scan;
	}
	public void set_is_wifi_scan(boolean _is_wifi_scan) {
		this._is_wifi_scan = _is_wifi_scan;
	}

}