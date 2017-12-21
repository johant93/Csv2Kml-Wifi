import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * the class responsable for the sort of the wifi networks
 *
 */

public class WifiSort {
	/**
	 * sort the signals in the list
	 * @param list
	 */
	
	public void signalAndMacsort (ArrayList <Wifi> list){
		Collections.sort(list,Wifi.ComparatorSignalAndMac);	
	}
	public  void signalAndTimesort (ArrayList <Wifi> list) {
		Collections.sort(list,Wifi.ComparatorSignalAndTime);
	}
	
	/**
	 * checks the amount of wifi for each time
	 * @param wifiarr
	 * @param time
	 * @param limit- limit of Wifis
	 * @return a list of wifi networks according to the limit restriction
	 */
	public ArrayList<Wifi> checkArraybyTime (ArrayList<Wifi> wifiarr,String time,int limit ){
		signalAndTimesort(wifiarr);
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
	
	/**
	 * check if the time is right
	 * @param wifi
	 * @param time
	 * @return true for right false for wrong
	 */
	public boolean checkTime(Wifi wifi,String time ){
		if(wifi.getTime().contains(time))
			return true ;
		else return false ;
	}
	
	/**
	 * 
	 * @param wifiarr- arrays list of wifi networks
	 * @param Id
	 * @return  the amount of wifi networks for each id
	 */
	public ArrayList<Wifi> checkArraybyId (ArrayList<Wifi> wifiarr,String Id,int limit ){
		signalAndTimesort(wifiarr);
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
	/**
	 * check if the id is right
	 * @param wifi
	 * @param Id
	 * @return true for correct id false for wrong id
	 */

	public boolean checkId(Wifi wifi,String Id ){
		if(wifi.getId().contains(Id))
			return true ;
		else return false ;
	}
	
	/**
	 * @param wifiarr- arraylist of wifi networks
	 * @param Lat
	 * @param Lon
	 * @return the amount of wifi networks for each location
	 */

	public ArrayList<Wifi> checkArraybyLocation (ArrayList<Wifi> wifiarr,String Lat,String Lon,int limit ){
		signalAndTimesort(wifiarr);
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
	/**
	 * check if the location is right
	 * @param wifi
	 * @param Lat
	 * @param Lon
	 * @return true for correct location false for wrong location
	 */

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
