package example.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import example.client.Market;

/**
 * 
 * @author manisha
 *
 */
public class FileUtils {
	
	private File marketFile;
	private PrintWriter newMkDataPrint;
	

	public FileUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public PrintWriter getNewMkDataPrint() {
		return newMkDataPrint;
	}

	public void setNewMkDataPrint(PrintWriter newMkDataPrint) {
		this.newMkDataPrint = newMkDataPrint;
	}
	public FileUtils(File marketFile) {
		marketFile = new File("results/marketData.csv");
		if(marketFile.exists())
			marketFile.delete();
		this.marketFile = marketFile;
	}
	
	public void printLine(Market marketObj, PrintWriter pW, int i) {
		pW.println(i+","+marketObj.getSymbol()+","+marketObj.getBank()+","+marketObj.getPrice()+","+marketObj.getRoundTripTime());
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
	
	public void saveFile(Market marketObj, int i) {
		
		newMkDataPrint = createPrintWriter(marketFile);
		newMkDataPrint.println("S.No,Symbol,Bank,Price,RoundTripTime");
		printLine(marketObj, newMkDataPrint, i);
	}
	
	public void closeFile(PrintWriter pW) {
		pW.close();
	}
	
}
