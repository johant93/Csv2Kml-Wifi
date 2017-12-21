import java.util.ArrayList;
import java.util.Collections;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String inputline1 = "2017-12-03 08:53:08,model=Lenovo PB2-690Y_device=PB2PRO,32.10487307,35.21134308,692.0,2,Ariel_University,1c:b9:c4:15:42:68,11,-85.0,Ariel_University,1c:b9:c4:15:44:58,11,-88.0";
		String inputline2 = "2017-12-03 08:53:09,model=Lenovo PB2-690Y_device=PB2PRO,32.10487307,35.21134308,692.0,2,Ariel_University,1c:b9:c4:15:42:68,11,-85.0,Ariel_University,1c:b9:c4:15:44:58,11,-88.0";
		String inputline3 = "2017-12-03 08:53:10,model=Lenovo PB2-690Y_device=PB2PRO,32.10487307,35.21134308,692.0,2,Ariel_University,1c:b9:c4:15:42:68,11,-85.0,Ariel_University,1c:b9:c4:15:44:58,11,-88.0";
		String dataline = "12/05/17 11:48 AM,model=SM-G950F_device=dreamlte,?,?,?,3,IT-MNG,1c:b9:c4:15:ed:b8,1,-81, ,8c:0c:90:ae:16:83,11,-86,Ariel_University,1c:b9:c4:16:ed:3c,44,-91,,,,,,,,,,,,,,,,,,,,";
		Wifi wf = new Wifi("2017-12-03 08:53:11", null, null, null, null, null, null, null, null);
		wf.setPi("1000");
		ArrayList<Wifi> inputemp1 = LineToArray(inputline1) ;
		ArrayList<Wifi> inputemp2 = LineToArray(inputline2) ;
		ArrayList<Wifi> inputemp3 = LineToArray(inputline3) ;
		setPi2Arraylist(inputemp1, 20);
		setPi2Arraylist(inputemp2, 26);
		setPi2Arraylist(inputemp3, 101);
	

		ArrayList<Wifi> list = new ArrayList<>() ;
		
	
		emptyIn(inputemp1, list);
		emptyIn(inputemp2, list);
		emptyIn(inputemp3, list);
		list.add(wf);
		Collections.sort(list,Wifi.ComparatorPi);
		//Collections.sort(list,Wifi.ComparatorSignalAndTime);
       System.out.println(" wifi of list : ");
		for (Wifi wifi : list)
				System.out.println(wifi +" Pi :"+ wifi.getPi());

		ArrayList<Wifi> differentswifi = getfirstDifferentwifi(list, 4);
	    System.out.println(" wifi of listdiff : ");
		for (Wifi wifi : differentswifi)
			System.out.println(wifi +" Pi :"+ wifi.getPi());
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
		return differentswifislist ;
	}

}
