package tw.com.cht.core;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class NoTask implements BaseCore {

	public Source getSource(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Document document=null;
		Source source=null;
		
		try { 	        	
        	
        	//<web-app>
        	//<display-name>error</display-name>
        	//<error code="100">
        	//<description> {TaskId} is not istalled</description>
        	//</error>
        	//</web-app>
        	
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
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
			source = new DOMSource(document);
       } 
        catch (Exception e) { 
        	System.out.println(e.getMessage());
        } 
		return source;
	}

	public StreamSource getXSLT(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//return new StreamSource(request.getAttribute("XsltUri")+"/error.xsl");
		String XsltUri=(String)request.getAttribute("XsltUri");
		String XsltDir=(String)request.getAttribute("XsltUriDir");
		
		if(XsltUri==null)
			return null;//XsltUri="error.xsl";
		
		return new StreamSource(XsltDir+"/"+XsltUri);
	}

}
