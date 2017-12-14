import java.util.ArrayList;
import java.util.Iterator;

public class Mac {

	public Mac(){
		this.time = null;
		this.lat = null;
		this.lon = null;
		this.alt = null;
		this.ssid = null;
		this.mac = null;
		this.channel = null;
		this.signal = null;
		this.comment = null;
	}



	String time, lat, lon, alt, ssid, mac, channel, signal,comment;
	
	

	public Mac(String time, String lat, String lon, String alt, String ssid, String mac, String channel,
			String signal) {
		this.time = time;
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.ssid = ssid;
		this.mac = mac;
		this.channel = channel;
		this.signal = signal;
		this.comment = "Approx. w-center algo1";
	}

	

	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
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



	public String getChannel() {
		return channel;
	}



	public void setChannel(String channel) {
		this.channel = channel;
	}



	public String getSignal() {
		return signal;
	}



	public void setSignal(String signal) {
		this.signal = signal;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}



	@Override
	public String toString() {
		return "Mac [time=" + time + ", lat=" + lat + ", lon=" + lon + ", alt=" + alt + ", ssid=" + ssid + ", mac="
				+ mac + ", channel=" + channel + ", signal=" + signal + ", comment=" + comment + "]";
	}
	public void printMaclist ( ArrayList <Mac> list) {
		Iterator<Mac> it = list.iterator();
		while (it.hasNext())
			System.out.println(it.next().toString());
	}

	
	

	

}
