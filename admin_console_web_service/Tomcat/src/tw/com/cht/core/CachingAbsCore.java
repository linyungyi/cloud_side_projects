package tw.com.cht.core;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class CachingAbsCore implements BaseCore {

	String strSourceUrl = "";
	String strXsltUri = "";
	String strXsltUriDir = "";
	String strXsltDefaultDir = "";
	
	static Source sourceCache = null;
	static String sourceUri = null;
	
	public CachingAbsCore() {
		// TODO Auto-generated constructor stub
	}
	/* initial attribute that is set in the HttpServletRequest*/
	protected void initAttribute(HttpServletRequest request){
		/*attribute*/
		strSourceUrl = (String)request.getAttribute("SourceUrl");
		strXsltUri = (String)request.getAttribute("XsltUri");
		strXsltUriDir = (String)request.getAttribute("XsltUriDi");
		strXsltDefaultDir = (String)request.getAttribute("XsltDefaultDir");
		/*parameter*/
		sourceUri = request.getParameter("Task");
	}
	
	/* get full path of cache file*/
	protected String getSourceUrl(){
		return strSourceUrl+sourceUri;
	}
	
	/* read cache source stored in the memory*/
	protected Source readFromMemory(){
		return sourceCache;
	}
	
	/*
	 *  write cache source in the memory. 
	 *  To disable cache in the memory, please overwrite writeIntoMemory(Source data) method and leave it empty. 
	 *  */
	protected void writeIntoMemory(Source data){
		sourceCache = data;
	}
	
	/* read cache source stored in the disk*/
	protected Source readFromDisk(){
		Source source = null;
		
		File f = new File(getSourceUrl());
		if(!f.exists())
			return null;

		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		dBF.setIgnoringComments(true); // Ignore the comments present in the
		// XML File when reading the xml
		DocumentBuilder builder = null;
		try {
			builder = dBF.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		InputSource input = new InputSource(getSourceUrl());
		
		Document doc = null;
		try {
			doc = builder.parse(input);
			source = new DOMSource(doc);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return source;
	}
	
	/* 
	 * write cache source in the disk. 
	 * To disable cache in the memory, please overwrite writeIntoDisk(Source data) method and leave it empty.
	 * */
	protected void writeIntoDisk(Source data){
		setSourceUri();
		
		if(data == null)
		{
			File f =  new File(getSourceUrl());
			f.delete();
			return;
		}
		
		try 
        { 
                 TransformerFactory tFactory = TransformerFactory.newInstance(); 
                 Transformer transformer = tFactory.newTransformer(); 
                 StreamResult result = new StreamResult(new File(getSourceUrl())); 
                 transformer.transform(data, result); 
         } 
         catch(Exception ex) 
         { 
                 ex.printStackTrace(); 
         } 
	}
	
	@Override
	public Source getSource(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Source source = null;
		initAttribute(request);
		criteriaOfCache();
		
		if((source=readFromMemory()) != null)
			return source;
		
		if((source=readFromDisk()) != null)
		{
			writeIntoMemory(source);
			return source;
		}
		
		source = pullDataSource(request);
		
		writeIntoMemory(source);
		writeIntoDisk(source);
		
		return source;

	}

	@Override
	public StreamSource getXSLT(HttpServletRequest request) {
		// TODO Auto-generated method stub
		if(strXsltUri==null)
			return null;
		return new StreamSource(strXsltUriDir+"/"+strXsltUri);

	}
	
	/* to do pull data from data source here*/
	protected abstract Source pullDataSource(HttpServletRequest request);
	/* 
	 * to do criteria to reset cache here. 
	 * use writeIntoMemory(null) to clear cache source in the memory; 
	 * use writeIntoDisk(null) to clear cache file in the disk
	 */
	protected abstract void criteriaOfCache();
	/* sourceUri = ?. redefine file name of cache file. leave it empty to use default file name. default file name is Task name. */
	protected abstract void setSourceUri();
}
