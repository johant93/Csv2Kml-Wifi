package Tools;


import java.util.ArrayList;

import wifi_tools.Wifi;

public interface CsvWrite {
	

	public void Array2csvSorted (ArrayList <Wifi> list,String newCsvDirection,int wifilimit)  ;
	public void Array2csv (ArrayList <Mac> list,String newCsvDirection) ;


}
