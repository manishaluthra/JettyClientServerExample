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

public class HTTPclient {	
	private HttpClient client;
	private static HttpExchange exchange;
	
	public void startClient() throws Exception {
		// Instantiate HttpClient
		client = new HttpClient();
	//	HTTPExchangeListener listener = new HTTPExchangeListener(); 
		// Configure HttpClient, for example:
//		httpClient.setFollowRedirects(false);
		
		//exchange.setEventListener(listener);

		Market marketObj = new Market(Settings.symbol1, Settings.bank1, Settings.price1);
	//	Cloneable clone = new Cloneable();
     //   exchange.setRequestContent(Cloneable.put(serialize(marketObj)));
        //client.setTh

        Field symbol = new Field("symbol", marketObj.getSymbol());
        Field bank = new Field("bank", marketObj.getBank());
        Field price = new Field("price", marketObj.getPrice().toString());
        Fields fields = new Fields();
        fields.put(symbol);
        fields.put(bank);
        fields.put(price);

        ContentResponse res = client.FORM("http://localhost:8080", fields);
        
        System.out.println(res.getContentAsString());
        client.start();
    }
	
	/*public static byte[] serialize(Object obj) throws IOException {
			exchange = new HttpExchange();
			
			Address address = new Address("localhost", 8080);
			exchange.setAddress(address);
			//exchange.setStatus(1);
			
			//InputStream marketIp= new FileInputStream("tmp");
			//ObjectInputStream marketIpObj = new ObjectInputStream(marketIp);
			
			
			//exchange.setRequestContentSource(marketIp);
	        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
	            try(ObjectOutputStream o = new ObjectOutputStream(b)){
	                o.writeObject(obj);
	            }
	            return b.toByteArray();
	        }
	       
	        
	        
	        
	        
	    }*/
	
	public void stopClient() throws Exception {
	        client.stop();

	}
	
	// Start HttpClient
	//httpClient.doStart();
	
}


