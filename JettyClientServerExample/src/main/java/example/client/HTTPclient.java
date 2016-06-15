package example.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ClientInfoStatus;
import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.client.Address;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpExchange;
import org.eclipse.jetty.io.Buffer;

import example.server.FileUtils;

/**
 * @Example from http://zetcode.com/java/jetty/httpclient/
 * @author manisha
 *
 */
public class HTTPclient {	
	private HttpClient client;
	private Socket socketConnection;
	private HttpExchange exchange;
	FileUtils file;
	
	public HTTPclient() {
		file = new FileUtils();
	}
	
	public void startClient() throws Exception {
		// Instantiate HttpClient
		client = new HttpClient();
		client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
		client.start();
		System.out.println("Here! started client!!");
		Market marketObj = new Market(Settings.symbol1, Settings.bank1, Settings.price1);
		socketConnection = new Socket("localhost", 8080);
		ObjectOutputStream clientOutputStream = new ObjectOutputStream(socketConnection.getOutputStream());
		clientOutputStream.writeObject(marketObj);
//        Field symbol = new Field("symbol", marketObj.getSymbol());
//        Field bank = new Field("bank", marketObj.getBank());
//        Field price = new Field("price", marketObj.getPrice().toString());
//        Fields fields = new Fields();
//        fields.put(symbol);
//        fields.put(bank);
//        fields.put(price);
//
//        ContentResponse res = client.FORM("http://localhost:8080", fields);
//        
//        System.out.println(res.getContentAsString());
	/**
	 * http://alvinalexander.com/java/jwarehouse/jetty-6.1.9/extras/client/src/test/java/org/mortbay/jetty/client/HttpExchangeTest.java.shtml
	 */
        final CountDownLatch latch=new CountDownLatch(10);
        final long l0=System.currentTimeMillis();
        for (int i=0; i<10; i++)
        {
            final int n=i;
            if (n%1000==0)
            {
                Thread.sleep(200);
            }
		exchange = new HttpExchange() {
			 protected void onRequestCommitted()
             {
                 // System.err.println("Request committed");
             }

             protected void onResponseStatus(Buffer version, int status, Buffer reason)
             {
                 // System.err.println("Response Status: " + version+" "+status+" "+reason);
             }

             protected void onResponseHeader(Buffer name, Buffer value)
             {
                 // System.err.println("Response header: " + name + " = " + value);
             }

             protected void onResponseContent(Buffer content)
             {
                 // System.err.println("Response content:" + content);
             }

             protected void onResponseComplete()
             {
                 System.err.println("Response completed "+n);
            	 long l1 = System.currentTimeMillis();
            	 long time = l1 - l0;
            	 System.out.println("Round trip time:: " +time);
                 latch.countDown();
             }
		};
		exchange.setAddress(new Address("localhost", 8080));
		exchange.setRequestURI("/marketObj");
		exchange.setRequestContentSource(downloadStream());
		client.send(exchange);
		clientOutputStream.close();
    }
	}
	
	public ObjectInputStream downloadStream() throws IOException, ClassNotFoundException {
		ObjectInputStream clientInputStream = new ObjectInputStream(socketConnection.getInputStream());
		Market marketObj = (Market) clientInputStream.readObject();
		System.out.println("Market datastructure:: Bank:: " + marketObj.getBank());
		return clientInputStream;
	}
	
	
	
	public void stopClient() throws Exception {
	        client.stop();

	}
	
	
}


