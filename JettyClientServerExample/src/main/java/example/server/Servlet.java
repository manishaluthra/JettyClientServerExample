package example.server;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import example.client.HTTPclient;
import example.client.Market;
import example.client.Settings;
 
/**
 * 
 * @author manisha
 *
 */
public class Servlet
{
    public static void main( String[] args ) throws Exception
    {
        // Create a basic jetty server object that will listen on port 8080.
        // Note that if you set this to port 0 then a randomly available port
        // will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.
        Server server = new Server(8080);
 
        // The ServletHandler is a dead simple way to create a context handler
        // that is backed by an instance of a Servlet.
        // This handler then needs to be registered with the Server object.
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
 
        // Passing in the class for the Servlet allows jetty to instantiate an
        // instance of that Servlet and mount it on a given context path.
 
        // IMPORTANT:
        // This is a raw Servlet, not a Servlet that has been configured
        // through a web.xml @WebServlet annotation, or anything similar.
        handler.addServletWithMapping(HelloServlet.class, "/*");
 
        // Start things up!
        server.start();
    	
       
        HTTPclient client = new HTTPclient();
        try {
        	
			client.startClient();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See
        // http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
        server.join();
        //client.stopClient();
        
    }
 
    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet
    {
    	//private WebSocketFactory _factory;
        @Override
        protected void doGet( HttpServletRequest request,
                              HttpServletResponse response ) throws ServletException,
                                                            IOException
        {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
            PrintWriter p = response.getWriter();
            //ServletOutputStream out = response.getOutputStream();
            //InputStream in = new FileInputStream("tmp");
            //in.
            
            
//            response.getWriter().println(request.getParameterNames().nextElement());
//            for(Enumeration<String> e = request.getParameterNames(); e.hasMoreElements();) {
//            	String name = e.nextElement();
//            	p.format("%s: %s%n", name, request.getParameter(name));
//            	
//            	System.out.println("Here!!"+ name+ request.getParameter(name));
//            }
            
        }
        
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        		throws ServletException, IOException {
        	// TODO Auto-generated method stub
//        	Market marketObj = new Market(Settings.symbol1, Settings.bank1, Settings.price1);
//        	req.setAttribute("market", marketObj);
//        	super.doPost(req, resp);
        }
    }
}
