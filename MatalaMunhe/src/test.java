import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class test {

	public static int nboflist = 0 ;

	public static void main(String[] args) {
		Algorithme2 algo2 = new Algorithme2();
		String samplescam ="12/05/17 11:48 AM,model=SM-G950F_device=dreamlte,?,?,?,3,IT-MNG,1c:b9:c4:15:ed:b8,1,-81, ,8c:0c:90:ae:16:83,11,-86,Ariel_University,1c:b9:c4:16:ed:3c,44,-91,,,,,,,,,,,,,,,,,,,,";
      File file = new File("/Users/joh/git/Csv2Kml-Wifi/MatalaMunhe/input/WigleWifi_20171027164517.csv");
	ArrayList<Wifi> wifiarr = getDataList(file);
	Coordinate coord = algo2.getcoordinate(samplescam, wifiarr);
	System.out.println(coord);
	Wifi wf = new Wifi();
	boolean result = false ;
	String mac = "1c:b9:c4:15:ed:b8" ;
	String wrongmac = ";1c:b9:c4:15:ed:b8";
	String signal = "200";
	String signal2 = "-45";
	System.out.println("wrong mac:"+wf.isMac(wrongmac));
	System.out.println("mac:"+wf.isMac(mac));
	System.out.println("wring signal:"+wf.isSignal(signal));
	System.out.println("signal:"+wf.isSignal(signal2));
	System.out.println("signal is int :"+isInt(signal));
	System.out.println("mac is int :"+isInt(mac));

	}
	public static boolean isInt(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public static  ArrayList<Wifi> getDataList (File inputfile){
		ArrayList<Wifi> dataOrdered = new ArrayList<Wifi>();
		if(inputfile.getAbsolutePath().contains(" "))
			System.out.println("ERROR FILE NAME (path name contain space)");
		else {
			CsvRead cr = new CsvProcessor() ;
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
					nboflist = dataOrdered.size();
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
		}
		return dataOrdered ;
	}
	public static ArrayList<Wifi> LineToArray(String line){
		ArrayList<Wifi> templist = new ArrayList<Wifi>();
		String[] data = line.split(",");
		int nbofwifi,step;
		if(data.length >= 9)	{
			nbofwifi = Integer.parseInt(data[5]);
			step = 0;

			for (int i = 0; i < nbofwifi ; i++) {		
				Wifi wifi = new Wifi(data[0],data[1],data[2],data[3],data[4],data[6+step],data[7+step],data[8+step],data[9+step]);
				templist.add(wifi);
				step += 4;
			}
		}
		return templist ;	
	}
	public static void emptyIn (ArrayList<Wifi> source,ArrayList<Wifi> dest){
		for ( Wifi wifi : source)
			dest.add(wifi);
		source.clear();
	}

}
