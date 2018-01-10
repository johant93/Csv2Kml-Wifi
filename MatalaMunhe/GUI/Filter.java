import java.util.ArrayList;

/**
 * this abstract class create the object Filter
 * this object allow us to combine many filters and to use Operators 
 * @author joh
 *
 */
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
