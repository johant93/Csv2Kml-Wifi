package Tools;

import java.io.File;
import java.util.ArrayList;

import wifi_tools.Wifi;

public interface CsvRead {

	public ArrayList<Wifi> getArrayList(File doc);
	public ArrayList<Wifi> combo2Array (File file);

}
