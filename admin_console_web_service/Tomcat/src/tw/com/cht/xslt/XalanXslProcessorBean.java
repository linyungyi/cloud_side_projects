package tw.com.cht.xslt;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xml.sax.SAXException;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
 * Servlet implementation class XalanXslProcessorBean
 */
public class XalanXslProcessorBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TransformerFactory tFactory;

	// the constructor simply gets a new TransformerFactory instance
	public XalanXslProcessorBean() {
		tFactory = TransformerFactory.newInstance();
	}

	// this method takes as input a XML source, a XSL source, and returns the
	// output of the transformation to the servlet output stream
/*	public void process(StreamSource xmlSource, StreamSource xslSource,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SAXException,Exception {
		try {

			Templates templates = tFactory.newTemplates(xslSource);
			Transformer transformer = templates.newTransformer();
			transformer.transform(xmlSource, new StreamResult(response
					.getOutputStream()));
			// transformer.transform(xmlSource, new StreamResult(new
			// File("D:/pb.html")));
		} catch (Exception e) {
			// should log some message here
			System.out.println("XalanXslProcessorBean error : "
					+ e.getMessage());
			throw e;
		}
	}*/

	public void process(Source xmlSource, StreamSource xslSource,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SAXException,Exception {
		try {
			Transformer transformer = null;
			if(xslSource ==null)
			{
				transformer = tFactory.newTransformer();
			}
			else
			{
				Templates templates = tFactory.newTemplates(xslSource);
				transformer = templates.newTransformer();
			}
			transformer.transform(xmlSource, new StreamResult(response
					.getOutputStream()));
			// transformer.transform(xmlSource, new StreamResult(new
			// File("D:/pb.html")));
		} catch (Exception e) {
			// should log some message here
			System.out.println("XalanXslProcessorBean error : "
					+ e.getMessage());
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
