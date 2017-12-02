import java.util.ArrayList;
import java.util.Collections;

public class WifiSort {
	
	public  void signalsort (ArrayList <Wifi> list) {
		Collections.sort(list);
	}
	public ArrayList<Wifi> checkArraybyTime (ArrayList<Wifi> wifiarr,String time,int limit ){
		signalsort(wifiarr);
		ArrayList<Wifi> wifichecked = new ArrayList<Wifi>();
		int index = 0, counter = 0;
		while(index<wifiarr.size() && counter < limit) {
			if(wifiarr.get(index).getTime().contains(time)){
				wifichecked.add(wifiarr.get(index));
				counter++;
			}
			index++;
		}
		return wifichecked ;
	}
	public boolean checkTime(Wifi wifi,String time ){
		if(wifi.getTime().contains(time))
			return true ;
		else return false ;
	}
	public ArrayList<Wifi> checkArraybyId (ArrayList<Wifi> wifiarr,String Id,int limit ){
		signalsort(wifiarr);
		ArrayList<Wifi> wifichecked = new ArrayList<Wifi>();
		int index = 0 , counter = 0;
		while(index<wifiarr.size() && counter < limit) {
			if(wifiarr.get(index).getId().contains(Id)){
				wifichecked.add(wifiarr.get(index));
				counter++;
			}
			index++;
		}
		return wifichecked ;
	}
	public boolean checkId(Wifi wifi,String Id ){
		if(wifi.getId().contains(Id))
			return true ;
		else return false ;
	}
	public ArrayList<Wifi> checkArraybyLocation (ArrayList<Wifi> wifiarr,String Lat,String Lon,int limit ){
		signalsort(wifiarr);
		ArrayList<Wifi> wifichecked = new ArrayList<Wifi>();
		int index = 0, counter = 0;
		while(index<wifiarr.size()&& counter < limit) {
			if(Lat == "" && wifiarr.get(index).getLon().equals(Lon)){
				wifichecked.add(wifiarr.get(index));
				counter++;
			}
			else if(Lon == "" && wifiarr.get(index).getLat().equals(Lat)){
				wifichecked.add(wifiarr.get(index));
				counter++;
			}

			else if(wifiarr.get(index).getLat().equals(Lat) && wifiarr.get(index).getLon().equals(Lon)){
				wifichecked.add(wifiarr.get(index));
				counter++;
			}
			index++;

		}
		return wifichecked ;
	}
	public boolean checkLocation(Wifi wifi,String Lat,String Lon ){
		if(Lat == "" && wifi.getLon().equals(Lon))
			return true ;
		else if(Lon == "" && wifi.getLat().equals(Lat))
			return true ;
		else if( wifi.getLat().equals(Lat) && wifi.getLon().equals(Lon))
			return true ;
		else return false ;
	}
}
