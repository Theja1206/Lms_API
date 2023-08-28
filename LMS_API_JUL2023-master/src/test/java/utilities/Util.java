package utilities;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import resources.ConfigReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
public class Util 
{
	 static XLData reader;
	Map<String,String> xl;


	public static List<Map<String, String>> getxlData(String SheetName) {

		reader = new XLData();
		// ********* data from excel ********** //
		
		List<Map<String, String>> data = null;
		try {
			data = reader.getData(ConfigReader.getGlobalValue("ProgramFile"), SheetName);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
}
