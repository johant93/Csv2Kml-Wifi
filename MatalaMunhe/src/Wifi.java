import java.util.ArrayList;
import java.util.Iterator;

public class Wifi implements Comparable  {
	String time, id, lat, lon, alt, ssid, mac, freq, signal;
	
	

	public Wifi() {
		this.time = null;
		this.id = null;
		this.lat = null;
		this.lon=null;
		this.alt = null;
		this.ssid = null;
		this.mac = null;
		this.freq=null;
		this.signal=null;
		
	}

	public  Wifi(String time,String id,String lat,String lon,String alt,String ssid,String mac,String freq,String signal) {
		this.time=time;
		this.id=id;
		this.lat=lat;
		this.lon=lon;
		this.alt=alt;
		this.ssid=ssid;
		this.mac=mac;
		this.freq=freq;
		this.signal=signal;
	}

	public Wifi(String ssid, String mac, String freq, String signal) {
		this.ssid = ssid;
		this.mac = mac;
		this.freq = freq;
		this.signal = signal;
	}

	public  Wifi(String[]s,String id) {
		this.time=s[3];
		this.lat=s[6];
		this.lon=s[7];
		this.alt=s[8];
		this.ssid=s[1];
		this.mac=s[0];
		this.freq=s[4];
		this.signal=s[5];
		this.id=id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	public String getSignal() {
		return signal;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	@Override
	public String toString() {
		return "Wifi [time=" + time + ", id=" + id + ", lat=" + lat + ", lon=" + lon + ", alt=" + alt + ", ssid=" + ssid
				+ ", mac=" + mac + ", freq=" + freq + ", signal=" + signal + "]";
	}
	//@Override
	public int compareTo(Object o) {
		if (o.getClass().equals(Wifi.class)){
			Wifi wf = (Wifi)o;
			// if the 2 wifi have the same time, we sort the signal
			if (this.time.compareTo(wf.getTime())==0)
				return this.signal.compareTo(wf.getSignal());
			return this.time.compareTo(wf.getTime());
		}
		return -1;
	}
	/**
	 * this function print the wifi Arraylist
	 * @param list
	 */
	public void printWifilist ( ArrayList <Wifi> list) {
		Iterator<Wifi> it = list.iterator();
		while (it.hasNext())
			System.out.println(it.next().toString());
	}
}


