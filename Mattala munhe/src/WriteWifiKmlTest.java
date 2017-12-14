import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.EmptyStackException;

import org.boehn.kmlframework.kml.Document;
import org.junit.Assert;
import org.junit.Test;

public class WriteWifiKmlTest {
	Document doc = new Document();
	CsvProcessor cp = new CsvProcessor();
	WriteWifiKml kml = new WriteWifiKml();

	
	@Test
	public void testCreateWifiKml() {
		File x = new File("/Users/joh/git/Csv2Kml-Wifi/MatalaMunhe/input/WigleWifiOfAriel.csv");
		    ArrayList<Wifi> Wifiarr = cp.getArrayList(x);
		    Assert.assertNotNull(Wifiarr);
			kml.createWifiKml(Wifiarr, "test");
					
	}

	@Test
	public void testwriteStyle (String color){
	
	}
	

	@Test
	public void testInputData() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateFile() {
		fail("Not yet implemented");
	}

}
