
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// Main class
public class Main {

	public static void main(String[] args) throws IOException {
		CsvRead cr = new CsvProcessor() ;
		CsvWrite cw = new CsvProcessor();
		WifiSort Wsort = new WifiSort() ;
		WriteWifiKml wkml = new WriteWifiKml();
		Algorithme algo = new Algorithme();
		Algorithme2 algo2 = new Algorithme2();

		Scanner sc = new Scanner(System.in);
		Wifi wifi = new Wifi();
		Mac mac = new Mac() ;
		int select = 0, selcheck = 0;
		String check = "";
		boolean input = false, loop = false ;
		

		while ( !input ){

			if (loop) sc.nextLine();
			String filename = "",newfile = "";
			System.out.println("- Welcome to the main application -");
			System.out.println("Please, enter the Path name of your Csv file : ");
			filename = sc.nextLine();
			System.out.println("new file's name : ");
			newfile = sc.nextLine();
			File file = new File(filename);
			ArrayList<Wifi> wifiArr = new ArrayList<Wifi>();
			if ( filename.contains("comb"))  wifiArr = cr.combo2Array(file);
			else  wifiArr = cr.getArrayList(file);
			
			
			ArrayList<Mac> Wifismac = algo.getRouter(wifiArr, 4); 
             
			System.out.println();
			while ( !input ){
				System.out.println(" 0: create a csv file of Macs");
				System.out.println(" 1: create a csv file sorted by date and signal");
				System.out.println(" 2: create a kml file ");
				System.out.println(" 3: create a kml file by data ");
				System.out.println(" 4: create a kml file of centers ");
				System.out.println(" 5: create a csv file with location of the user");

				System.out.println("make a choice : ");
				select = sc.nextInt();
				switch (select){
				case 0:
					cw.Array2csv(Wifismac, newfile);
					System.out.println("print the list ?  1:yes 2:no  ");
					int print = sc.nextInt();
					if ( print == 1 ) mac.printMaclist(Wifismac);
					input = true ;
					break;
				case 1:
					System.out.println(" choose the max number of wifi per list: ");
					int maxnumber = sc.nextInt();
					cw.Array2csvSorted(wifiArr,newfile, maxnumber);
					System.out.println("print the list ?  1:yes 2:no  ");
					print = sc.nextInt();
					if ( print == 1 ) wifi.printWifilist(wifiArr);

					input = true ;

					break;
				case 2:
					wkml.createWifiKml(wifiArr,newfile);
					input = true ;
					break;
				case 3:
					System.out.println("input the maximum number of wifi : ");
					int limit = sc.nextInt();
					while (!input){
						System.out.println(" 1:by time  2:by Id   3:by location"); 
						selcheck = sc.nextInt();
						switch ( selcheck){
						case 1:
							sc.nextLine(); // get the sc.getInt() that precede
							System.out.println("input the time :");
							check = sc.nextLine();
							wkml.createWifiKml(Wsort.checkArraybyTime(wifiArr, check, limit), newfile);
							input = true ;
							break ;
						case 2 :
							sc.nextLine();
							System.out.println("input the id :");
							check = sc.nextLine();
							wkml.createWifiKml(Wsort.checkArraybyId(wifiArr, check, limit), newfile);
							input = true ;
							break;
						case 3 :
							sc.nextLine();
							System.out.println("input the latitude :");
							String Lat = sc.nextLine();
							System.out.println("input the longitude :");
							String Lon = sc.nextLine();
							wkml.createWifiKml(Wsort.checkArraybyLocation(wifiArr, Lat, Lon,limit), newfile);
							input = true ;
							break;

						default : System.out.println("wrong input");	
						}	
					}	
					break;
				case 4 :
					wkml.createMacKml(Wifismac,newfile);
					System.out.println("print the list ?  1:yes 2:no  ");
					print = sc.nextInt();
					if ( print == 1 ) mac.printMaclist(Wifismac);
					input = true ;
					break;
				case 5 :
					sc.nextLine(); // get the sc.getInt() that precede
					System.out.println("Please, enter the Path name of the input file : ");
					filename = sc.nextLine();
					File inputfile = new File(filename);
					algo2.FindUserLocation(inputfile, file, newfile);
					input = true ;
					break;
				default : System.out.println("wrong input");
				}
			}

			if (input){
				System.out.println("your file was created successfully!  ");
				System.out.println();    
				System.out.println(" 1:open the file   2:back to main menu  \n to exit press any other number ");
				int open = sc.nextInt() ;				
				if ( open == 1){
					System.out.println(newfile+" is opening...");
					String path = System.getProperty("user.dir"); 
					Runtime.getRuntime().exec("open "+path+"/"+newfile);
				}
				else if (open == 2) { 
					input = false ;
					loop = true ;
				}
				else System.out.println( "Goodbye !");

			}
		}
		sc.close();
	}
}