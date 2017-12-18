import java.util.ArrayList;

public class Algorithme{

	WifiSort Wsort = new WifiSort() ;
  public ArrayList<Mac> getRouter (ArrayList<Wifi> list,int wifisbase){
		
	  Wsort.signalAndMacsort(list);
	  ArrayList<Mac> listRouter = new ArrayList<Mac>();
	  ArrayList<Wifi> templist = new ArrayList<Wifi>();
      int i = 0, counter = 0;
      boolean full = false, end = false ,jump = false;
	 while ( i<list.size()-1){
		 
		  String mac =list.get(i).getMac();
		  String mac_next =list.get(i+1).getMac();
		  
		  if (!jump){
       // if the current wifi has same mac of the next wifi and we didn't exceed maxnumber
		  // we add the current wifi in templist
          if ( mac.compareTo(mac_next) == 0 && counter<=wifisbase){
			  templist.add(list.get(i));
			  // lastwifi 
			  if ( i == list.size()-2 ){
				  templist.add(list.get(i+1));
				  end = true;
                  full = true;
			  }
			  if ( counter == 4){
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
         
          if ( full == true ){
        	  listRouter.add(Algorithme.getrouter(templist));
               templist.clear();
        	  full = false ;
        	  counter = 0;
          }
          //lastwifi with unique Mac
          if ( end == false && i == list.size()-2)
        	  templist.add(list.get(i+1));
        	  listRouter.add(Algorithme.getrouter(templist));
		  }
		  
		  if(mac.compareTo(mac_next) !=0) jump = false ;
          i++;
          
	 }
	  
	 return listRouter ;
  }
  
  public static Mac getrouter (ArrayList<Wifi> list){
	  
	  if (list.isEmpty()){
		  System.out.println("templist is empty - error"); 
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

