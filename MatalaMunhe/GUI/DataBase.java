import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * this class create a database with ArrayList for argument
 * @author joh
 *
 */
public class DataBase {

	CsvRead cr = new CsvProcessor() ;
	CsvWrite cw = new CsvProcessor();
	WifiSort Wsort = new WifiSort() ;
	WriteWifiKml wkml = new WriteWifiKml();
	Algorithme algo = new Algorithme();
	Algorithme2 algo2 = new Algorithme2();
	public  ArrayList<Wifi> Database = new ArrayList<Wifi>();	
	int nbofWifi = 0 , nboflist = 0 ;

/**
 * constructor
 */
	public DataBase(){
	}
	/**
	 * 
	 * @param database
	 */
	public DataBase(ArrayList<Wifi> database) {
		super();
		Database = database;
	}
	/**
	 * 
	 * @param database
	 */
	public void setDatabase(ArrayList<Wifi> database) {
		Database = database;
	}
	/**
	 * get database
	 */
	public ArrayList<Wifi> getDatabase(){
		return this.Database;
	}
	/**
	 * @return Database size
	 */
	public int databaseSize() {
		return this.Database.size();
	}
	/**
	 * @return number of wifi in Database
	 */
	public int getNbofWifi() {
		return this.Database.size() ;
	}
	/**
	 * @return number of Sample scan in database
	 */
	public int getNboflist() {
		return nboflist;
	}
/**
 * 
 * @param nboflist
 */
	public void setNboflist(int nboflist) {
		this.nboflist = nboflist;
	}
	
	/**
	 * Check if the Database is empty
	 * @return true if empty, else @return false
	 */
	public boolean isEmpty (){
		if(databaseSize()== 0)
			return true;
		return false;
	}
/**
 * Add a CSV file of wifi to Database
 * @param path of the file
 * @return Database
 */
	public ArrayList<Wifi> addToDatabase(String path){

		if(path.isEmpty()) System.out.println( "path is empty ");
		else {
			File file = new File(path);
			
			ArrayList<Wifi> wifiArr = getDataList(file);
			emptyIn(wifiArr, this.Database);
		}
		nbofWifi = this.Database.size();
		return this.Database ;
	}
/**
 * clear the Database
 */
	public void clearDatabase(){
		this.Database.clear();
		this.nboflist =0;
		this.nbofWifi =0;
	}
	
	/**
	 * this function apply filters on the database 
	 * @param ArrayList<Filter>  myFilter
	 * @return ArrayList<Wifi> DataFiltered
	 */
	public ArrayList<Wifi> getDataFiltered (ArrayList<Filter> myFilter){
		ArrayList<Wifi> DataFiltered = new ArrayList<Wifi>();
		for(Wifi wifi : Database){
			boolean result = false , next ;
		    for(Filter filter : myFilter){
		    	next = filter.isTrue(wifi);
			if (filter.getNotOperator().equals("N")) next = !next;
			if (filter.getOperator().equals("OR")) result = (result||next);
			else result = (result && next);
		    }
		    if (result == true ) DataFiltered.add(wifi);
		 }
		return DataFiltered;
	}
/**
 * transform File in ArrayList of Wifi
 * @param inputfile
 * @return ArrayList of Wifi (dataOrdered)
 */
	public  ArrayList<Wifi> getDataList (File inputfile){
		ArrayList<Wifi> dataOrdered = new ArrayList<Wifi>();
		if(inputfile.getAbsolutePath().contains(" "))
			System.out.println("ERROR FILE NAME (path name contain space)");
		else {
			CsvRead cr = new CsvProcessor() ;
			WifiSort Sortlist = new WifiSort();
			boolean isCombo = true ;
			try {
				Scanner testsc = new Scanner(inputfile);
				//checking the 5 first line of the file
				for (int i = 0; i < 10 && isCombo ; i++) {
					if (testsc.nextLine().contains("MAC,SSID"))
						isCombo = false ;
				}
				testsc.close();
				Scanner inputsc = new Scanner(inputfile);
				if (!isCombo){
					dataOrdered = cr.getArrayList(inputfile);
					nboflist += dataOrdered.size();
				}

				else {

					while (inputsc.hasNextLine()) {
						String inputline = inputsc.nextLine();
						ArrayList<Wifi> inputemp = LineToArray(inputline) ;
						emptyIn(inputemp, dataOrdered);
						nboflist ++ ;
             		}
				}
				inputsc.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Sortlist.signalAndTimesort(dataOrdered);
		}
		
		return dataOrdered ;
	}
	/**
	 * this method transform a data line (Sample scan) in ArrayList of Wifis 
	 * @param line 
	 * @return ArrayList of Wifis
	 */
	public static ArrayList<Wifi> LineToArray(String line){
		ArrayList<Wifi> templist = new ArrayList<Wifi>();
		String[] data = line.split(",");
		int nbofwifi,step;
		if(data.length >= 9 && !line.contains("#Wifi networks"))	{
			nbofwifi = Integer.parseInt(data[5]);
			step = 0;

			for (int i = 0; i < nbofwifi && (step+9)<data.length; i++) {		
				Wifi wifi = new Wifi(data[0],data[1],data[2],data[3],data[4],data[6+step],data[7+step],data[8+step],data[9+step]);
				templist.add(wifi);
				step += 4;
			}
		}
		return templist ;	
	}
	/**
	 * this method empty the source ArrayList into dest ArrayList
	 * clear the source
	 * @param source
	 * @param dest
	 */
	public static void emptyIn (ArrayList<Wifi> source,ArrayList<Wifi> dest){
		for ( Wifi wifi : source)
			dest.add(wifi);
		source.clear();
	}
	/**
	 * this method print the Database
	 */
	public void printDataBase() {
		Wifi wf = new Wifi();
		wf.printWifilist(Database);
	}
	
	/**
	 * this method check if the file is Valid for the Databse
	 * @param Path
	 * @return true if file is valid, else @return false
	 */
	public boolean isGoodFile(String Path){
		File file = new File(Path);
		if (file.exists()&&Path.contains(".csv"))
			return true;
		return false ;
	}
}
