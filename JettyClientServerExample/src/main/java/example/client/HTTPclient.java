package example.client;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.management.ObjectInstance;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.Fields;
import org.eclipse.jetty.util.Fields.Field;

/**
 * @Example from http://zetcode.com/java/jetty/httpclient/
 * @author manisha
 *
 */
public class HTTPclient {	
	private HttpClient client;
	private static HttpExchange exchange;
	
	public void startClient() throws Exception {
		// Instantiate HttpClient
		client = new HttpClient();
		client.start();
	
		Market marketObj = new Market(Settings.symbol1, Settings.bank1, Settings.price1);
	
        Field symbol = new Field("symbol", marketObj.getSymbol());
        Field bank = new Field("bank", marketObj.getBank());
        Field price = new Field("price", marketObj.getPrice().toString());
        Fields fields = new Fields();
        fields.put(symbol);
        fields.put(bank);
        fields.put(price);

        ContentResponse res = client.FORM("http://localhost:8080", fields);
        
        System.out.println(res.getContentAsString());    
    }
	
	
	public void stopClient() throws Exception {
	        client.stop();

	}
	
	
}


