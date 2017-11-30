import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TestProcessor {

	public static void main(String[] args) throws IOException {
		CsvProcessor csv = new CsvProcessor() ;
		WifiSort Wsort = new WifiSort() ;
		WriteWifiKml wkml = new WriteWifiKml();
		Scanner sc = new Scanner(System.in);
		Wifi wifi = new Wifi();

		int select = 0,selcheck = 0;
		String check = "";
		boolean input = false, loop = false ;

		while ( !input ){

			if (loop) sc.nextLine();
			String filename = "",newfile = "";
			System.out.println("- Welcome to the main application of Csv2Kml -");
			System.out.println("Please, enter the Path name of your Csv file : ");
			filename = sc.nextLine();
			System.out.println("new file's name : ");
			newfile = sc.nextLine();
			File file = new File(filename);
			ArrayList<Wifi> wifiArr = csv.getArrayList(file);
			System.out.println();
			while ( !input ){
				System.out.println(" 1: sort the file by signal");
				System.out.println(" 2: create a kml file");
				System.out.println(" 3: create a kml file by data ");
				System.out.println("make a choice : ");
				select = sc.nextInt();
				switch (select){
				case 1:
					System.out.println(" choose the max number of wifi per list: ");
					int maxnumber = sc.nextInt();
					csv.Array2csvSorted(wifiArr,newfile, maxnumber);
					System.out.println("impress the list ?  1:yes 2:no  ");
                    int impress = sc.nextInt();
                    if ( impress == 1 ) wifi.printWifilist(wifiArr);
            
					input = true ;
					
					break;
				case 2:
					wkml.createWifiKml(wifiArr,newfile);
					input = true ;
					break;
				case 3:
					while (!input){
						System.out.println(" 1:by time  2:by Id   3:by location"); 
						selcheck = sc.nextInt();
						switch ( selcheck){
						case 1:
				            sc.nextLine(); // get the sc.getInt() that precede
							System.out.println("impress the time :");
							check = sc.nextLine();
							wkml.createWifiKml(Wsort.checkArraybyTime(wifiArr, check), newfile);
							input = true ;
							break ;
						case 2 :
				            sc.nextLine();
                           System.out.println("impress the id :");
							check = sc.nextLine();
							wkml.createWifiKml(Wsort.checkArraybyId(wifiArr, check), newfile);
							input = true ;
							break;
						case 3 :
				            sc.nextLine();
                           System.out.println("impress the latitude :");
							String Lat = sc.nextLine();
							System.out.println("impress the longitude :");
							String Lon = sc.nextLine();
							wkml.createWifiKml(Wsort.checkArraybyLocation(wifiArr, Lat, Lon), newfile);
							input = true ;
							break;
						default : System.out.println("wrong input");	
						}
					  }	
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
					System.out.println(newfile);
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
