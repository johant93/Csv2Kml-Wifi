package Tools;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import wifi_tools.Wifi;
import wifi_tools.WifiSort;

import java.util.Collections;
import java.util.Comparator;


/**
 * this class is used to process Csv files.
 * @author joh
 */
public class CsvProcessor implements CsvWrite, CsvRead{

	WifiSort Wsort = new WifiSort() ;


	/**
	 * This function receive a file or a doc file
	 * @param file csv doc or file
	 * @return An arraylist of the file's objects
	 * @exception Exception file not found
	 * @see Scanner
	 */
	public ArrayList<Wifi> getArrayList(File doc){
		ArrayList<Wifi> wifiArr = new ArrayList<Wifi>();

		if (doc.exists()){
			if (!doc.isFile()){
				try {
					String[] files = doc.list();
					for (String csv : files) {
						String csvLoc = doc.toString() + "/" + csv;
						File x = new File(csvLoc);
						if(x.getName().contains(".csv")) {
							Scanner sc = new Scanner(x);
							String line = "";
							String id="";
							boolean delfirstline = false ;
							String[] arr=new String[11];
							while (sc.hasNextLine()) {
								line = sc.nextLine();
								if (line.contains("&")) line = line.replace("&","AND"); // replace "&" by "AND" allowing to read the kml convert file on google earth.
								if(!(line.contains("SSID")||line.contains("GSM"))) { // deleting the useless line
									if(delfirstline) {
										arr=line.split(",");
										wifiArr.add(new Wifi(arr,id));
									}
									else{
										arr=line.split(",");
										id=arr[2];
									}
									delfirstline = true;
								}
							}
							sc.close();
						}
					}
				} catch (Exception ex) {
					Logger.getLogger(CsvProcessor.class.getName()).log(Level.SEVERE, null, ex);
					System.out.println("File no found");
				}
			}
			else {
				try {
					if(doc.getName().contains(".csv")) {
						Scanner sc = new Scanner(doc);
						String line = "";
						String id="";
						boolean delfirstline = false ;
						String[] arr=new String[11];
						while (sc.hasNextLine()) {
							line = sc.nextLine();
							if (line.contains("&")) line = line.replace("&","AND"); // replace "&" by "AND" allowing to read the kml convert file on google earth.
							if(!(line.contains("SSID")||line.contains("GSM"))) { // deleting the useless line
								if(delfirstline) {
									arr=line.split(",");
									wifiArr.add(new Wifi(arr,id));
								}
								else{
									arr=line.split(",");
									id=arr[2];
								}
								delfirstline = true;
							}
						}
						sc.close();
					}

				} catch (Exception ex) {
					Logger.getLogger(CsvProcessor.class.getName()).log(Level.SEVERE, null, ex);
					System.out.println("File no found");
				}

			}

		}
		return wifiArr ;

	}

	public ArrayList<Wifi> combo2Array (File file){
		ArrayList<Wifi> wifiArr = new ArrayList<Wifi>();
		try {
			Scanner sc = new Scanner(file);
			String line = "";
			int nbofwifi,step;
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				String[] data = line.split(",");
				
		if(data.length >= 9)	{
				nbofwifi = Integer.parseInt(data[5]);
				step = 0;

				for (int i = 0; i < nbofwifi ; i++) {		
					Wifi wifi = new Wifi(data[0],data[1],data[2],data[3],data[4],data[6+step],data[7+step],data[8+step],data[9+step]);
					wifiArr.add(wifi);
					step += 4;
				}
		}
			}
			sc.close();

		} catch (Exception ex) {
			Logger.getLogger(CsvProcessor.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("File no found");
		}


		return wifiArr ;
	}	

	/**
	 * 
	 * @param list- arraylists of wifi
	 * @param newCsvDirection- file name
	 * @param wifilimit- number of wifi by object in our project is 10
	 *  @return File with one or many wifis sorted, by until the limited amount. in our case wifilimit=10,
	 * all wifi by object have the same time,id,coordonates.
	 * @throws IOException 
	 */
	public void Array2csvSorted (ArrayList <Wifi> list,String newCsvDirection,int wifilimit) {

		Wsort.signalAndTimesort(list);
		//Wsort.signalAndMacsort(list);
		int i = 1, wifiFound = 0;
		FileWriter fw;
		try {
			fw = new FileWriter(newCsvDirection);

			BufferedWriter bw = new BufferedWriter(fw);
			boolean fullStack= false;
			bw.write("Time");
			bw.write(",");
			bw.write("ID");
			bw.write(",");
			bw.write("Lat");
			bw.write(",");
			bw.write("Lon");
			bw.write(",");
			bw.write("Alt");
			bw.write(",");
			bw.write("#Wifi networks");
			bw.write(",");
			for (int j = 1; j <= wifilimit; j++) {
				bw.write("SSID" + j + "");
				bw.write(",");
				bw.write("MAC" + j + "");
				bw.write(",");
				bw.write("Frequncy" + j + "");
				bw.write(",");
				if (j<wifilimit) bw.write("Signal" + j + ",");
				else  bw.write("Signal" + j );
			}
			int	netCounter=1;
			bw.newLine();
			bw.write(list.get(0).getTime());
			bw.write(",");
			bw.write(list.get(0).getId());
			bw.write(",");
			bw.write(list.get(0).getLat());
			bw.write(",");
			bw.write(list.get(0).getLon());
			bw.write(",");
			bw.write(list.get(0).getAlt());
			bw.write(",");
			bw.write(wifiCounter(list,0,wifilimit)+"");
			bw.write(",");
			bw.write(list.get(0).getSsid());
			bw.write(",");
			bw.write(list.get(0).getMac());
			bw.write(",");
			bw.write(list.get(0).getFreq());
			bw.write(",");
			if(timeisequal(list,1)) bw.write(list.get(0).getSignal()+",");
			else bw.write(list.get(0).getSignal());

			while (i < list.size()-1) {
				if(fullStack==true) i++;
				if (timeisequal(list, i)) {

					if(i==1)netCounter = 2;
					else netCounter++;
					if(netCounter>wifilimit) {
						fullStack=true;
					}
					else {
						bw.write(list.get(i).getSsid());
						bw.write(",");
						bw.write(list.get(i).getMac());
						bw.write(",");
						bw.write(list.get(i).getFreq());
						bw.write(",");
						if(timeisequal(list, i+1) && wifiFound < wifilimit  ) bw.write(list.get(i).getSignal()+",");
						else bw.write(list.get(i).getSignal());
						i++;
						wifiFound++;
					}
				} 
				else {
					fullStack =false;
					netCounter=1;
					wifiFound = 1;
					bw.newLine();
					bw.write(list.get(i).getTime());
					bw.write(",");
					bw.write(list.get(i).getId());
					bw.write(",");
					bw.write(list.get(i).getLat());
					bw.write(",");
					bw.write(list.get(i).getLon());
					bw.write(",");
					bw.write(list.get(i).getAlt());
					bw.write(",");
					bw.write(wifiCounter(list,i,wifilimit)+"");
					bw.write(",");
					bw.write(list.get(i).getSsid());
					bw.write(",");
					bw.write(list.get(i).getMac());
					bw.write(",");
					bw.write(list.get(i).getFreq());
					bw.write(",");
					if(timeisequal(list, i+1)&& i<list.size()-1) bw.write(list.get(i).getSignal()+",");
					else bw.write(list.get(i).getSignal());
					i++;
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void Array2csv (ArrayList <Mac> list,String newCsvDirection) {

		int i = 1 ;
		FileWriter fw;
		try {
			fw = new FileWriter(newCsvDirection);

			BufferedWriter bw = new BufferedWriter(fw);

			bw.write("Mac,");
			bw.write("Ssid,");
			bw.write("Channel,");
			bw.write("Signal,");
			bw.write("Lat,");
			bw.write("Lon,");
			bw.write("Alt,");
			bw.write("Time,");
			bw.write("comment");

			while (i < list.size()-1) {
				bw.newLine();
				bw.write(list.get(i).getMac()+",");
				bw.write(list.get(i).getSsid()+",");
				bw.write(list.get(i).getChannel()+",");
				bw.write(list.get(i).getSignal()+",");
				bw.write(list.get(i).getLat()+",");
				bw.write(list.get(i).getLon()+",");
				bw.write(list.get(i).getAlt()+",");
				bw.write(list.get(i).getTime()+",");
				bw.write(list.get(i).getComment());
				i++;
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param list
	 * @param index
	 * @param wifilimit
	 * @return the number of wifis
	 */
	private int wifiCounter(ArrayList<Wifi> list,int index, int wifilimit ) {
		int counter=1;
		for(int i=index;i<list.size()-1;i++) {
			if(list.get(i).getTime().equals(list.get(i+1).getTime())) {
				counter++;
				if(counter==wifilimit)return wifilimit;	
			}
			else return counter;
		}
		return counter;
	}
	/**
	 * checks if times are equals in the list for the index and one before it
	 * @param list- Wifi arraylist
	 * @param i- index
	 * @return true for equal and false for not equal
	 */
	private boolean timeisequal(ArrayList<Wifi> list,int i){
		if (list.get(i).getTime().equals(list.get(i - 1).getTime())) return true ;
		else
			return false ;
	}


}




