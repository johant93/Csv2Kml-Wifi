import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.sound.midi.Synthesizer;

public class Algorithme2 {
	Wifi wf = new Wifi();
	private static double norm = 10000;
	private static int power = 2 ;
	private static double sigdiff = 0.4;
	private static int mindiff = 3 ;
	private static String nosignal = "-120";
	private static double diffnosign = 100;

public Coordinate getcoordinate(ArrayList<Wifi> inputemp,ArrayList<Wifi> dataList){
		
		ArrayList<Wifi> WifiPiList = new ArrayList<Wifi>();
		double difference = 0,weight=0;
		boolean macfound = false ;
		ArrayList<Wifi> datatemp = new ArrayList<Wifi>() ;
		datatemp.add(dataList.get(0));
		int index = 0;
		while( index < dataList.size()-1){
			String time = dataList.get(index).getTime();
			String time_next = dataList.get(index+1).getTime();
            if (time.equals(time_next)){
            	datatemp.add(dataList.get(index+1));
            }
            else {
            	 double Pi = 1.0;
					// comparing each wifi of inputemp with each wifi of datatemp 	
					for (Wifi inputwifi : inputemp){
						macfound = false ;
						for (Wifi datawifi : datatemp){	
							if (macfound==false && inputwifi.getMac().equals(datawifi.getMac())) {
								difference = getDiff(inputwifi.getSignal(), datawifi.getSignal());
								weight = getWeight(difference, inputwifi.getSignal());
								macfound = true ;
							}
						}
						if(macfound==false) {
							difference = diffnosign ;
							weight = getWeight(difference, inputwifi.getSignal());
						}
						Pi = Pi*weight ;		
					}
					setPi2Arraylist(datatemp, Pi);
					emptyIn(datatemp,WifiPiList);
				}
			index++;	
			}
		
		Collections.sort(WifiPiList,Wifi.ComparatorPi);
		ArrayList<Wifi> listofwificlose = getfirstDifferentwifi(WifiPiList, 4);
		Coordinate coord = setCoordinates(listofwificlose); 
		     
		return coord ;

	}
	public Coordinate getcoordinate(String samplescan,ArrayList<Wifi> dataList){
		
		ArrayList<Wifi> WifiPiList = new ArrayList<Wifi>();
		double difference = 0,weight=0;
		boolean macfound = false ,end = false;
		ArrayList<Wifi> inputemp = LineToArray(samplescan) ;
		ArrayList<Wifi> datatemp = new ArrayList<Wifi>() ;
		datatemp.add(dataList.get(0));
		int index = 0;
		while( index < dataList.size()-1){
			String time = dataList.get(index).getTime();
			String time_next = dataList.get(index+1).getTime();
            if (time.equals(time_next)){
            	datatemp.add(dataList.get(index+1));
            	if(index+1 == dataList.size()-1) // check if we ran until the last samplescan
            		end = true;
            }
            else {
            	 double Pi = 1.0;
					// comparing each wifi of inputemp with each wifi of datatemp 	
					for (Wifi inputwifi : inputemp){
						macfound = false ;
						for (Wifi datawifi : datatemp){	
							if (macfound==false && inputwifi.getMac().equals(datawifi.getMac())) {
								difference = getDiff(inputwifi.getSignal(), datawifi.getSignal());
								weight = getWeight(difference, inputwifi.getSignal());
								macfound = true ;
							}
						}
						if(macfound==false) {
							difference = diffnosign ;
							weight = getWeight(difference, inputwifi.getSignal());
						}
						Pi = Pi*weight ;		
					}
					setPi2Arraylist(datatemp, Pi);
					emptyIn(datatemp,WifiPiList);
				}
			index++;	
			}
		/*if(!end){ // if last wifi was not include in the program we add it here.
         	datatemp.add(dataList.get(index+1));
         	 double Pi = 1.0;
				// comparing each wifi of inputemp with each wifi of datatemp 	
				for (Wifi inputwifi : inputemp){
					macfound = false ;
					for (Wifi datawifi : datatemp){	
						if (macfound==false && inputwifi.getMac().equals(datawifi.getMac())) {
							difference = getDiff(inputwifi.getSignal(), datawifi.getSignal());
							weight = getWeight(difference, inputwifi.getSignal());
							macfound = true ;
						}
					}
					if(macfound==false) {
						difference = diffnosign ;
						weight = getWeight(difference, inputwifi.getSignal());
					}
					Pi = Pi*weight ;		
				}
				setPi2Arraylist(datatemp, Pi);
				emptyIn(datatemp,WifiPiList);
		}*/
			
		Collections.sort(WifiPiList,Wifi.ComparatorPi);
		ArrayList<Wifi> listofwificlose = getfirstDifferentwifi(WifiPiList, 4);
		Coordinate coord = setCoordinates(listofwificlose); 
		     
		return coord ;

	}
	public void FindUserLocation (File inputfile,File datafile,String newfile){
		try {

			BufferedWriter bw = new BufferedWriter(new FileWriter(newfile));
			ArrayList<Wifi> dataList = new ArrayList<>() ;
			Scanner inputsc = new Scanner(inputfile);
			String inputline = "",dataline = "",inputwithcoordinates="";
			double difference = 0,weight=0; 
			int datalist = 0;
			boolean macfound = false ;
			//checking for each line of input if Wifis' Mac of data correspond to Wifis' Mac of input 
			while (inputsc.hasNextLine()) {
				inputline = inputsc.nextLine();
				ArrayList<Wifi> inputemp = LineToArray(inputline) ;
				Scanner datasc = new Scanner(datafile);
				// checking each line of data 
				while (datasc.hasNextLine()) {
					dataline = datasc.nextLine();
					ArrayList<Wifi> datatemp = LineToArray(dataline) ;
                     double Pi = 1.0;
					// comparing each wifi of inputemp with each wifi of datatemp 	
					for (Wifi inputwifi : inputemp){
						macfound = false ;
						for (Wifi datawifi : datatemp){	
							if (macfound==false && inputwifi.getMac().equals(datawifi.getMac())) {
								difference = getDiff(inputwifi.getSignal(), datawifi.getSignal());
								weight = getWeight(difference, inputwifi.getSignal());
								macfound = true ;
							}
						}
						if(macfound==false) {
							difference = diffnosign ;
							weight = getWeight(difference, inputwifi.getSignal());
						}
						Pi = Pi*weight ;		
					}
					setPi2Arraylist(datatemp, Pi);
					emptyIn(datatemp,dataList);

				}
				datasc.close();
				datalist++;
				Collections.sort(dataList,Wifi.ComparatorPi);
				ArrayList<Wifi> listofwificlose = getfirstDifferentwifi(dataList, 4);
				Coordinate coord = setCoordinates(listofwificlose); 
				inputwithcoordinates = placeCoordinates(inputline,coord);  
				bw.write(inputwithcoordinates);
				bw.newLine();
				inputemp.clear();
				dataList.clear();
			}
			bw.close();
			inputsc.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	public static double getDiff(String Signal1,String Signal2){
		double difference = 0;
		double absoluediff = Math.abs(Double.parseDouble(Signal1)-Double.parseDouble(Signal2));
		difference = Math.max(absoluediff,mindiff);
		return difference;
	}
	public static double getWeight(double diff,String inputsignal){
		double weight = 0;
		weight = norm / (Math.pow(diff, sigdiff)*Math.pow(Double.parseDouble(inputsignal),power));
		return weight ;
	}
	public static void setPi2Arraylist (ArrayList<Wifi> wifiarr,double Pi){
		for ( Wifi wifi : wifiarr){
			wifi.setPi(String.valueOf(Pi));
		}
	}
	public static void emptyIn (ArrayList<Wifi> source,ArrayList<Wifi> dest){
		for ( Wifi wifi : source)
			dest.add(wifi);
		source.clear();
	}
	public static ArrayList<Wifi> getfirstDifferentwifi(ArrayList<Wifi> list,int numbofwifi){
		ArrayList<Wifi> differentswifislist = new ArrayList<>();
		if (list.isEmpty()) System.out.println("error : list is empty");
		else {
			int count = 1;
			differentswifislist.add(list.get(0));
			for(int i = 1; i<list.size() && count<numbofwifi; i++){
				Wifi precedwifi = list.get(i-1);
				Wifi thiswifi = list.get(i);
				if(precedwifi.getTime().equals(thiswifi.getTime())== false ){
					differentswifislist.add(thiswifi);
					count++;
				}

			} 
		}
			return differentswifislist ;
		
	}
		public static String placeCoordinates (String line,Coordinate coord){
			String coordinate = String.valueOf(coord.getLat())+","+String.valueOf(coord.getLon())+","+String.valueOf(coord.getAlt());
			return line.replaceAll("\\?,\\?,\\?", coordinate);
		}
		public static Coordinate setCoordinates (ArrayList<Wifi> list){

			Coordinate coord = new Coordinate() ;

			if (list.isEmpty()){
				System.out.println("your list is empty - error"); 
			}
			else { 
				double Pi_sum=0.0, wAlt_sum=0.0,wLon_sum=0.0,wLat_sum=0.0;
				int nbofwifi = list.size();
				double [] Pi = new double[nbofwifi];
				double [] wAlt = new double[nbofwifi];
				double [] wLon = new double[nbofwifi];
				double [] wLat = new double[nbofwifi];

				for (int i = 0; i < nbofwifi; i++) {
					Pi[i]=Double.parseDouble(list.get(i).getPi());
					wAlt[i]= Double.parseDouble(list.get(i).getAlt())*Pi[i];
					wLon[i]= Double.parseDouble(list.get(i).getLon())*Pi[i];
					wLat[i]= Double.parseDouble(list.get(i).getLat())*Pi[i];
					Pi_sum+=Pi[i];
					wAlt_sum+=wAlt[i];
					wLon_sum+=wLon[i];
					wLat_sum+=wLat[i];
				}
				coord.setAlt(wAlt_sum/Pi_sum);
				coord.setLon(wLon_sum/Pi_sum);
				coord.setLat(wLat_sum/Pi_sum);
			}
			return coord ;
		}
	}
