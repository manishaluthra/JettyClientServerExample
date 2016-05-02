package example.client;

import org.eclipse.jetty.client.HttpClient;
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
	
	public void startClient() throws Exception {
		// Instantiate HttpClient
		client = new HttpClient();
		client.start();
		System.out.println("Here! started client!!");
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


