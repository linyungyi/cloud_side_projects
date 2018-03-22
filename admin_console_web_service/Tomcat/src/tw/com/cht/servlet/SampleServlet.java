package tw.com.cht.servlet;

import java.io.*; 
import java.net.URL;

import javax.servlet.*; 
import javax.servlet.http.*; 
import javax.xml.transform.stream.*; 

import tw.com.cht.xslt.XalanXslProcessorBean;

public class SampleServlet extends HttpServlet { 

    XalanXslProcessorBean processor; 

    public void init(ServletConfig config) { 
        processor = new XalanXslProcessorBean(); 
    } 

    public void doGet (HttpServletRequest request, 
                                HttpServletResponse response) 
             throws ServletException, IOException { 

        //sets the Content-Type portion of the HTTP header to text/html 
        response.setContentType("text/html"); 
        //PrintWriter out = response.getWriter();
        //out.println("xml");
        try { 
            processor.process(new StreamSource("D:/slashdot.xml"), new StreamSource("D:/slashdot.xsl"), request, response); 
        	//processor.process(new StreamSource(new InputStreamReader((new URL("http://localhost:8090/slashdot.xml")).openStream())),new StreamSource(new InputStreamReader((new URL("http://localhost:8090/slashdot.xsl")).openStream())), request, response);
            //out.flush();
        } 
        catch (Exception e) { 
        	//out.println(e.getMessage());
        } 
    } 
} 
