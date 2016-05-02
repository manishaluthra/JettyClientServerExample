package example.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import example.client.Market;

public class FileUtils {
	
	public void printLine(Market marketObj, PrintWriter pW) {
		pW.println(marketObj.getSymbol()+","+marketObj.getBank()+","+marketObj.getPrice()+","+marketObj.getRoundTripTime());
	}

	public PrintWriter createPrintWriter(File file) {
		PrintWriter print = null;
		try {
			print = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return print;
	}
	
	public void saveFile(Market marketObj, String fileName, int i) {
		File newMarketData = new File("marketData/"+i+".txt");
		if(newMarketData.exists())
			newMarketData.delete();
		
		PrintWriter newMkData = createPrintWriter(newMarketData);
		printLine(marketObj, newMkData);
	}
	
}
