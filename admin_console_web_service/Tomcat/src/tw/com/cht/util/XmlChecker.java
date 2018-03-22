package tw.com.cht.util;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XmlChecker {
    
	private void write(Document document,String file){
		// write the XML document to disk
		try {

			// create DOMSource for source XML document
			Source xmlSource = new DOMSource(document);

			// create StreamResult for transformation result
			Result result = new StreamResult(new FileOutputStream(
					file));

			// create TransformerFactory
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();

			// create Transformer for transformation
			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty("indent", "yes");	//Java XML Indent

			// transform and deliver content to client
			transformer.transform(xmlSource, result);
		}

		// handle exception creating TransformerFactory
		catch (TransformerFactoryConfigurationError factoryError) {
			System.err.println("Error creating " + "TransformerFactory");
			factoryError.printStackTrace();
		} catch (TransformerException transformerError) {
			System.err.println("Error transforming document");
			transformerError.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
    	
    }
}
