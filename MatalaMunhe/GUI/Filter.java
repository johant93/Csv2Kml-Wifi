import java.util.ArrayList;

abstract class Filter {
	
	String Operator = "OR", notOperator =""  ;
	
   abstract ArrayList<Wifi> setFilter (ArrayList<Wifi> dataBase);
   abstract boolean isTrue (Wifi wifi);
   public abstract String toString ();
   
   
   public String getOperator() {
	return Operator;
}
   public void setOperator(String operator) {
	Operator = operator;
}
   public String getNotOperator() {
	return notOperator;
}
   public void setNotOperator(String notOperator) {
	this.notOperator = notOperator;
}
   
 
   
}
