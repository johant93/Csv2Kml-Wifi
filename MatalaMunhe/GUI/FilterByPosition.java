import java.util.ArrayList;
/**
 * this class filters the Database by position
 * @arguments Lat , Long , Radius (latitude,longitude,radius)
 * @author joh
 *
 */
public class FilterByPosition extends Filter {
	
	String Lat , Long , Radius ;

	
	/**
	 * @param database
	 * this method set the filter on database
	 * @return database filtered
	 */
public  ArrayList<Wifi> setFilter (ArrayList<Wifi> dataBase){
		
		ArrayList<Wifi> dataBasefiltered = new ArrayList<Wifi>();
		
		for ( Wifi wifi : dataBase)
			  if (Radius(wifi,Lat,Long,Radius))
			dataBasefiltered.add(wifi);
		
		return dataBasefiltered ;
		
	}
/**
 * @param wifi
 * @return true if the wifi contain the name selected
 */
	   public boolean isTrue (Wifi wifi){
		   if (Radius(wifi,Lat,Long,Radius))
			   return true ;
		   return false ;
	   }
	
	public FilterByPosition() {
		Lat = null;
		Long = null;
		Radius = null;
	}
	
	public FilterByPosition(String lat, String l, String radius) {
		Lat = lat;
		Long = l;
		Radius = radius;
	}
	

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getLong() {
		return Long;
	}

	public void setLong(String l) {
		Long = l;
	}

	public String getRadius() {
		return Radius;
	}

	public void setRadius(String radius) {
		Radius = radius;
	}
	
	private static boolean Radius (Wifi wifi,String lat,String lon,String radius){
		
		double lonValue = Double.parseDouble(lon)+Double.parseDouble(radius);
		double latValue = Double.parseDouble(lat)+Double.parseDouble(radius);
		double lonWifi = Double.parseDouble(wifi.getLon());
		double latWifi = Double.parseDouble(wifi.getLat());
         if( lonWifi < lonValue && latWifi < latValue )
        	 return true ;
		return false ;
	}

	@Override
	public String toString() {
		return "FilterByPosition: Center = (" + Lat + "," + Long + ") , Radius <" + Radius + ", Operator: "+notOperator+Operator;
	}
	
	
	
	

}
