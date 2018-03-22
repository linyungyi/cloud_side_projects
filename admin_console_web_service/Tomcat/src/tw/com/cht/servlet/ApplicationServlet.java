package tw.com.cht.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import tw.com.cht.core.BaseCore;
import tw.com.cht.util.ConfigLoader;
import tw.com.cht.util.TaskLoader;
import tw.com.cht.xslt.XalanXslProcessorBean;

/**
 * Servlet implementation class ApplicationServlet
 */
public class ApplicationServlet extends HttpServlet {

    XalanXslProcessorBean processor; 
    static TaskLoader taskLoader;
    static ConfigLoader configLoader;
    static String Config;
    static String Task;
    static String XsltDefaultDir;
    

    public void init(ServletConfig config) { 
        processor = new XalanXslProcessorBean();
        Config = config.getInitParameter("Config");
        Task = config.getInitParameter("Task");
        XsltDefaultDir = config.getInitParameter("Templates");
 
        if(taskLoader == null)
        {
        	LoadTaskList();
        }    
        
        if(configLoader == null)
        {
        	LoadConfig();
        }
    } 
    
    public void destroy() {
    	super.destroy();
    	taskLoader = null;
    	configLoader = null;
    }

    public void doGet (HttpServletRequest request, 
                                HttpServletResponse response) 
             throws ServletException, IOException { 
    	
        //sets the Content-Type portion of the HTTP header to text/html 
        //response.setContentType("text/html"); 
        //SampleCore sc = new SampleCore(request);
    	//String Command=request.getParameter("command");
    	BaseCore BC = null;
    	Source xml = null;
    	StreamSource xslt = null;

    	String TaskId=request.getParameter("Task");
    	String XsltUri=request.getParameter("xsl");
    	
    	request.setAttribute("SourceUrl", configLoader.getProperty("DOCUMENT.SourceUrl"));
    	request.setAttribute("XsltUri", XsltUri);
    	request.setAttribute("XsltUriDir", configLoader.getProperty("DOCUMENT.XsltUrl"));
    	request.setAttribute("XsltDefaultDir", XsltDefaultDir);
    	
    	if(TaskId != null && taskLoader.getProperty("TASK_ID."+TaskId)!=null)
    	{
	        try { 

	        	BC = (BaseCore) taskLoader.getInstance("TASK_ID."+TaskId);
	        	xml = BC.getSource(request);
	        	xslt = BC.getXSLT(request);
	        	processor.process(xml, xslt, request, response);

	            //processor.process(new StreamSource("D:/pcPlayList.xml"), new StreamSource("D:/pcPlayList.xsl"), request, response); 
	        	//processor.process(new StreamSource(new InputStreamReader((new URL("http://localhost:8090/slashdot.xml")).openStream())),new StreamSource(new InputStreamReader((new URL("http://localhost:8090/slashdot.xsl")).openStream())), request, response);
	        } 
	        catch (Exception e) { 
	        	System.out.println("tw.com.cht.ApplicationServlet: " + e.getMessage());

	    	    request.setAttribute("ExObj", e);
	    	    
		        try { 	        	
		        	BC = (BaseCore) taskLoader.getInstance("TASK_ID.errorTask");
		        	xml = BC.getSource(request);
		        	xslt = BC.getXSLT(request);
		        	processor.process(xml, xslt, request, response); 

		        } 
		        catch (Exception ex) { 
		        	System.out.println(ex.getMessage());
		        } 
	    	    
	        } 
    	}else{
    		//XsltUri = configloader.getProperty("DOCUMENT.XsltUrl")+TaskId+"_error.xsl";
    		
	        try { 	        	
	        	
	        	BC = (BaseCore) taskLoader.getInstance("TASK_ID.noTask");
	        	xml = BC.getSource(request);
	        	xslt = BC.getXSLT(request);
	        	processor.process(xml, xslt, request, response); 
	            //processor.process((StreamSource)source, new StreamSource(XsltUri), request, response); 
	        	//processor.process(new StreamSource(new InputStreamReader((new URL("http://localhost:8090/slashdot.xml")).openStream())),new StreamSource(new InputStreamReader((new URL("http://localhost:8090/slashdot.xsl")).openStream())), request, response);
	        } 
	        catch (Exception e) { 
	        	System.out.println(e.getMessage());
	        } 
    	}
    } 
    
    public void LoadTaskList(){
    	try{
     		taskLoader = new TaskLoader(Task);
     		//System.out.println(Task);
     		System.out.println("ApplicationServlet.LoadTaskList()");
    	}catch(Exception ex)
    	{
    		System.out.println("ApplicationServlet.LoadTaskList(): "+ex.getMessage());
    	}
    }
    
    public void LoadConfig(){
    	try{
    		configLoader = new ConfigLoader(Config);
    		//System.out.println(Config);
    		System.out.println("ApplicationServlet.LoadConfig()");
    	}catch(Exception ex)
    	{
    		System.out.println("ApplicationServlet.LoadConfig()"+ex.getMessage());
    	}
    } 

    public void StopTaskList(){
    	try{
     		taskLoader = null;
     		System.out.println("ApplicationServlet.StopTaskList()");
    	}catch(Exception ex)
    	{
    		System.out.println("ApplicationServlet.StopTaskList(): "+ex.getMessage());
    	}
    }    

    public void StopConfig(){
    	try{
    		configLoader = null;
    		System.out.println("ApplicationServlet.StopConfig()");
    	}catch(Exception ex)
    	{
    		System.out.println("ApplicationServlet.StopConfig()"+ex.getMessage());
    	}
    }     

}
