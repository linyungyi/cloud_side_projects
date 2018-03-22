package tw.com.cht.xslt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tw.com.cht.core.BaseCore;
import tw.com.cht.util.ConfigLoader;
import tw.com.cht.util.TaskLoader;

public class XslProcessorServlet extends HttpServlet { 

    XalanXslProcessorBean processor; 
    static TaskLoader taskloader;
    static ConfigLoader configloader;
    static String Config;
    static String Task;
    static String XsltDefaultDir;
    

    public void init(ServletConfig config) { 
        processor = new XalanXslProcessorBean();
        Config = config.getInitParameter("Config");
        Task = config.getInitParameter("Task");
        XsltDefaultDir = config.getInitParameter("Templates");
        //System.out.println("XslProcessorServlet.init");
        if(taskloader == null)
        {
        	LoadTaskList();
        }    
        
        if(configloader == null)
        {
        	LoadConfig();
        }
    } 
    
    public void destroy() {
    	super.destroy();
    	taskloader = null;
    	configloader = null;
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
    	if(TaskId != null && taskloader.getProperty("TASK_ID."+TaskId)!=null)
    	{
	        try { 
	        	request.setAttribute("SourceUri", configloader.getProperty("DOCUMENT.SourceUri"));
	        	request.setAttribute("XsltUri", XsltUri);
	        	request.setAttribute("XsltUriDir", configloader.getProperty("DOCUMENT.XsltUri"));
	        	
	        	BC = (BaseCore) taskloader.getInstance("TASK_ID."+TaskId);
	        	xml = BC.getSource(request);
	        	xslt = BC.getXSLT(request);
	        	processor.process(xml, xslt, request, response);
	            //processor.process(new StreamSource("D:/pcPlayList.xml"), new StreamSource("D:/pcPlayList.xsl"), request, response); 
	        	//processor.process(new StreamSource(new InputStreamReader((new URL("http://localhost:8090/slashdot.xml")).openStream())),new StreamSource(new InputStreamReader((new URL("http://localhost:8090/slashdot.xsl")).openStream())), request, response);
	        } 
	        catch (Exception e) { 
	        	System.out.println(e.getMessage());
	       		
	        	boolean exists = (new File(configloader.getProperty("DOCUMENT.XsltUri")+TaskId+"_error.xsl")).exists();
	    	    if (!exists) {
	    	        //XsltUri = XsltDefaultDir+"/error.xsl";
	    	    	request.setAttribute("XsltUriDir", XsltDefaultDir);
	    	    	XsltUri = null;
	    	    }else
	    	    {
	    	    	request.setAttribute("XsltUriDir", configloader.getProperty("DOCUMENT.XsltUri"));
	    	    	XsltUri = TaskId+"_error.xsl";
	    	    }
	    	    //System.out.println(XsltUri);
	    	    request.setAttribute("SourceUri", configloader.getProperty("DOCUMENT.SourceUri"));
	    	    request.setAttribute("XsltUri", XsltUri);
	    	    request.setAttribute("ExObj", e);
		        try { 	        	
		        	BC = (BaseCore) taskloader.getInstance("TASK_ID.errorTask");
		        	xml = BC.getSource(request);
		        	xslt = BC.getXSLT(request);

					processor.process(xml, xslt, request, response); 

		        } 
		        catch (Exception ex) { 
		        	System.out.println(ex.getMessage());
		        } 
	    	    
	        } 
    	}else{
    		//XsltUri = configloader.getProperty("DOCUMENT.XsltUri")+TaskId+"_error.xsl";
    		
    		boolean exists = (new File(configloader.getProperty("DOCUMENT.XsltUri")+TaskId+"_error.xsl")).exists();
    	    if (!exists) {
    	        //XsltUri = XsltDefaultDir+"/error.xsl";
    	    	request.setAttribute("XsltUriDir", XsltDefaultDir);
    	    }else
    	    {
    	    	request.setAttribute("XsltUriDir", configloader.getProperty("DOCUMENT.XsltUri"));
    	    	XsltUri = TaskId+"_error.xsl";
    	    }
    	    request.setAttribute("SourceUri", configloader.getProperty("DOCUMENT.SourceUri"));
    	    request.setAttribute("XsltUri", XsltUri);
    		
	        try { 	        	
	        	
	        	//<web-app>
	        	//<display-name>error</display-name>
	        	//<error code="100">
	        	//<description> {TaskId} is not istalled</description>
	        	//</error>
	        	//</web-app>
	        	/*
				Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
				Element root = document.createElement("web-app");
				document.appendChild(root);
				
				Element displayname = document.createElement("display-name");
				displayname.appendChild(document.createTextNode("error message"));
				root.appendChild(displayname);
				
				Element description = document.createElement("description");
				description.appendChild(document.createTextNode("no task"));
				
				Element error = document.createElement("error");
				Attr codeAttribute = document.createAttribute("code");
				codeAttribute.setValue("100");
				
				error.setAttributeNode(codeAttribute);
				error.appendChild(description);
				root.appendChild(error);			
				Source source = new DOMSource(document);
				
				processor.process(source, new StreamSource(XsltUri), request, response); 
				*/
	        	
	        	BC = (BaseCore) taskloader.getInstance("TASK_ID.noTask");
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
     		taskloader = new TaskLoader(Task);
     		//System.out.println(Task);
     		System.out.println("XslProcessorServlet.LoadTaskList()");
    	}catch(Exception ex)
    	{
    		System.out.println("XslProcessorServlet.LoadTaskList(): "+ex.getMessage());
    	}
    }
    
    public void LoadConfig(){
    	try{
    		configloader = new ConfigLoader(Config);
    		//System.out.println(Config);
    		System.out.println("XslProcessorServlet.LoadConfig()");
    	}catch(Exception ex)
    	{
    		System.out.println("XslProcessorServlet.LoadConfig()"+ex.getMessage());
    	}
    } 

    public void StopTaskList(){
    	try{
     		taskloader = null;
     		System.out.println("XslProcessorServlet.StopTaskList()");
    	}catch(Exception ex)
    	{
    		System.out.println("XslProcessorServlet.StopTaskList(): "+ex.getMessage());
    	}
    }    

    public void StopConfig(){
    	try{
    		configloader = null;
    		System.out.println("XslProcessorServlet.StopConfig()");
    	}catch(Exception ex)
    	{
    		System.out.println("XslProcessorServlet.StopConfig()"+ex.getMessage());
    	}
    }     
    
} 


