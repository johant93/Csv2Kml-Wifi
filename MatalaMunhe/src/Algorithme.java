import java.io.File;
import java.util.ArrayList;


public class Algorithme{

	WifiSort Wsort = new WifiSort() ;
	/**
	 * This fonction give us an array list of mac center.
	 * each mac center was calculated from  an arraylist of wifi with commun mac adress.
	 * mac is based on static function algorithme1().
	 * @param list
	 * @param wifisbase
	 * @return new ArrayList<Mac>
	 */
	public ArrayList<Mac> getRouter (ArrayList<Wifi> list,int wifisbase){

		Wsort.signalAndMacsort(list);
		if (list.isEmpty())
			System.out.println("wifi list is empty - error"); 
		ArrayList<Mac> listRouter = new ArrayList<Mac>();
		ArrayList<Wifi> templist = new ArrayList<Wifi>();
		int i = 0, counter = 0;
		boolean full = false, end = false ,jump = false;
		while ( i<list.size()-1){

			String mac =list.get(i).getMac();
			String mac_next =list.get(i+1).getMac();

			if (!jump){
				// if the current wifi has same mac of the next wifi and we didn't exceed maxnumber
				// we add the current wifi to templist
				if ( mac.compareTo(mac_next) == 0 && counter<=wifisbase){
					templist.add(list.get(i));
					// lastwifi 
					if ( i == list.size()-2 ){
						templist.add(list.get(i+1));
						end = true;
						full = true;
					}
					if ( counter == wifisbase){
						full = true ;
						jump = true ;
					}

					counter++; 
				}
				// if only wifi with this mac or if counter < 4
				else if (mac.compareTo(mac_next) != 0){
					templist.add(list.get(i));
					full = true ;
				}

				// adding the mac center of wifis localized in templist 
				if ( full ){
					listRouter.add(Algorithme.algorithme1(templist));
					if (listRouter.isEmpty())
						System.out.println("wifi list is empty - error"); 
					templist.clear();
					full = false ;
					counter = 0;
				}
				//last wifi with unique Mac 
				if ( end == false && i == list.size()-2){
					templist.add(list.get(i+1));
					listRouter.add(Algorithme.algorithme1(templist));
				}

			} else if(mac.compareTo(mac_next) !=0) jump = false ;

			i++;

		}
		if (listRouter.isEmpty())
			System.out.println("wifi list2 is empty - error"); 
		return listRouter ;
	}
	/**
	 * This function find the approximative localization of macAdress
	 * @param list
	 * @param wifisbase
	 * @param macAdress
	 * @return
	 */
	public Mac getMac (ArrayList<Wifi> list,int wifisbase,String macAdress){
		Wsort.signalAndMacsort(list);
		if (list.isEmpty())
			System.out.println("wifi list is empty - error"); 
		ArrayList<Wifi> templist = new ArrayList<Wifi>();
		int i = 0, counter = 0;
		boolean Macfound = false;
		
		while ( i<list.size()-1 && !Macfound){	
			String mac =list.get(i).getMac();
			String mac_next =list.get(i+1).getMac();
			if(macAdress.equals(mac)&& counter < wifisbase){
				templist.add(list.get(i));
				counter++;
				if ( i == list.size()-2 && mac.compareTo(mac_next) == 0 ){
					templist.add(list.get(i+1));
					Macfound = true;
				}
				if(counter == wifisbase || mac.compareTo(mac_next) != 0 )
					Macfound = true ;
			}		
			if ( Macfound == false && i == list.size()-2 && macAdress.equals(mac_next))
				templist.add(list.get(i+1));		
			i++;
		}
		Mac macResult = algorithme1(templist);
      
		return macResult;
		
	}

	public static Mac algorithme1 (ArrayList<Wifi> list){

		if (list.isEmpty()){
			// System.out.println("templist is empty - error"); 
			return new Mac();
		}
		else { 
			double wSig_sum=0.0, wAlt_sum=0.0,wLon_sum=0.0,wLat_sum=0.0;
			double routerAlt=0.0,routerLon=0.0,routerLat=0.0;
			int nbofwifi = list.size();
			double [] wSig = new double[nbofwifi];
			double [] wAlt = new double[nbofwifi];
			double [] wLon = new double[nbofwifi];
			double [] wLat = new double[nbofwifi];


			for (int i = 0; i < nbofwifi; i++) {
				wSig[i]= 1/(Double.parseDouble((list.get(i).getSignal()))*Double.parseDouble((list.get(i).getSignal())));
				wSig_sum+=wSig[i];
				wAlt[i]= Double.parseDouble(list.get(i).getAlt())*wSig[i];
				wLon[i]= Double.parseDouble(list.get(i).getLon())*wSig[i];
				wLat[i]= Double.parseDouble(list.get(i).getLat())*wSig[i];
				wAlt_sum+=wAlt[i];
				wLon_sum+=wLon[i];
				wLat_sum+=wLat[i];
			}
			routerAlt = wAlt_sum/wSig_sum ;
			routerLon = wLon_sum/wSig_sum ;
			routerLat = wLat_sum/wSig_sum ;
			/* Wifi router = new Wifi(list.get(0).getTime(), list.get(0).getId(),
	String.valueOf(routerLat),String.valueOf(routerLon), String.valueOf(routerAlt),
	list.get(0).getSsid(), list.get(0).getMac(), list.get(0).getFreq(),list.get(0).getSignal());*/
			Mac mac = new Mac(list.get(0).getTime(), String.valueOf(routerLat),
					String.valueOf(routerLon), String.valueOf(routerAlt), list.get(0).getSsid(),
					list.get(0).getMac(), list.get(0).getFreq(), list.get(0).getSignal());

			return mac;
		}
	}

   
}
// test
