package tw.com.cht.task;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import tw.com.cht.core.BaseCore;

public class UserApply implements BaseCore {

	@Override
	public Source getSource(HttpServletRequest request) {
		// TODO Auto-generated method stub
		//System.out.println(request.getAttribute("SourceUrl"));
		//System.out.println(request.getParameter("song"));
		
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		dBF.setIgnoringComments(true); // Ignore the comments present in the
		// XML File when reading the xml
		DocumentBuilder builder = null;
		try {
			builder = dBF.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		InputSource input = new InputSource(request.getAttribute("SourceUrl")+"UserApply.xml");
		Document doc = null;
		try {
			doc = builder.parse(input);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Source source = new DOMSource(doc);
		
		return source;
	}

	@Override
	public StreamSource getXSLT(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
