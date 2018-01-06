import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
/**
 * Wifi object Class.
 */

public class Wifi   {
	String time, id, lat, lon, alt, ssid, mac, freq, signal,Pi;

/**
 * builds a Wifi object
 */
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

/**
 * builds a Wifi object
 */

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

/**
 * builds a Wifi object
 */

	public Wifi(String ssid, String mac, String freq, String signal) {
		this.ssid = ssid;
		this.mac = mac;
		this.freq = freq;
		this.signal = signal;
	}

/**
 * builds a Wifi object
 */

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

	public String getPi() {
		return Pi;
	}

	public void setPi(String pi) {
		Pi = pi;
	}

	@Override
	public String toString() {
		return "Wifi [time=" + time + ", id=" + id + ", lat=" + lat + ", lon=" + lon + ", alt=" + alt + ", ssid=" + ssid
				+ ", mac=" + mac + ", freq=" + freq + ", signal=" + signal + ", Pi=" + Pi + "]";
	}
	public boolean isSignal(String signal){	
		if (isInt(signal)){
		if(Integer.parseInt(signal)>=-100 && Integer.parseInt(signal)<=-40)
		return true ;
		}
		return false ;
	}
	public boolean isMac(String mac){
		if(mac.length()==17)
			return true ;
		return false ;
	}
	public static boolean isInt(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	 /**
	    * new Comparator class for Signal and Time
	    */
	public static Comparator<Wifi> ComparatorSignalAndTime = new Comparator<Wifi>() {
	
		/**
	     *comparator for Signal and Time
		 *@return if the second object smaller return negative value and if its bigger positive value.
		 *if they are equal return 0;
		 */
        @Override
        public int compare(Wifi w1, Wifi w2) {
        	if (w1.getTime().compareTo(w2.getTime())==0)
    			return w1.getSignal().compareTo(w2.getSignal());
    		return w1.getTime().compareTo(w2.getTime());
        }
    };
   /**
    * new Comparator class for Signal and Mac
    */
    public static Comparator<Wifi> ComparatorSignalAndMac = new Comparator<Wifi>() {
    	
    	/**
    	 *comparator for Signal and Mac
    	 *@return if the second object smaller return negative value and if its bigger positive value.
    	 *if they are equal return 0;
    	 */
        @Override
        public int compare(Wifi w1, Wifi w2) {
        	if (w1.getMac().compareTo(w2.getMac())==0)
    			return w1.getSignal().compareTo(w2.getSignal());
    		return w1.getMac().compareTo(w2.getMac());
        }
        
    };
public static Comparator<Wifi> ComparatorPi = new Comparator<Wifi>() {
    	
    	/**
    	 *comparator for Pi (weight of wifi by location)
    	 *@return if the second object smaller return negative value and if its bigger positive value.
    	 *if they are equal return 0;
    	 */
        @Override
        public int compare(Wifi w1, Wifi w2) {    
    		return w2.getPi().compareTo(w1.getPi()); // decreasing order
        }
        
    };
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


