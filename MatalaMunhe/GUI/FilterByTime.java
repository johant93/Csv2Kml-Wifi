import java.util.ArrayList;

public class FilterByTime extends Filter {

	String From, To ;

	public  ArrayList<Wifi> setFilter (ArrayList<Wifi> dataBase){

		ArrayList<Wifi> dataBasefiltered = new ArrayList<Wifi>();

		for ( Wifi wifi : dataBase)
			if ( this.From.compareTo(wifi.getTime()) < 0 && wifi.getTime().compareTo(this.To) < 0)
				dataBasefiltered.add(wifi);

		return dataBasefiltered ;

	}
	
	public  boolean isTrue (Wifi wifi){		

		if ( this.From.compareTo(wifi.getTime()) < 0 && wifi.getTime().compareTo(this.To) < 0)
			return true ;
		return false ;
	}

	public FilterByTime(){
		From = null;
		To = null;
	}
	public FilterByTime(String from, String to) {
		From = from;
		To = to;
	}

	public String getFrom() {
		return From;
	}

	public void setFrom(String from) {
		From = from;
	}

	public String getTo() {
		return To;
	}

	public void setTo(String to) {
		To = to;
	}

	@Override
	public String toString() {
		return "FilterByTime: " + From + " < Data < " + To + ", Operator: "+notOperator+Operator;
	}



}
