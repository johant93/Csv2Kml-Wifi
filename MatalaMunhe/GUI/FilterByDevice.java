import java.util.ArrayList;

public class FilterByDevice extends Filter {
	
	String name ;
	
	public  ArrayList<Wifi> setFilter (ArrayList<Wifi> dataBase){
		
		ArrayList<Wifi> dataBasefiltered = new ArrayList<Wifi>();
		
		for ( Wifi wifi : dataBase)
			   if (wifi.getId().contains(name))
            dataBasefiltered.add(wifi);
		
		return dataBasefiltered ;
		
	}
	
	   public boolean isTrue (Wifi wifi){
		   if (wifi.getId().contains(name))
			   return true ;
		   return false ;
	   }

	   public FilterByDevice() {
			this.name = null;
		}
	
	   public FilterByDevice(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "FilterByDevice: Name of Device: " + name +", Operator: "+notOperator+Operator ;
	}
	   
	   
	   

}
