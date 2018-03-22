package tw.com.cht.task;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import tw.com.cht.core.BaseCore;

public class BrcTest implements BaseCore {

	@Override
	public Source getSource(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Document document=null;
		Source source=null;
		String msIsdn = "";
		String sSlave = request.getParameter("SLAVE");
		String sDate0 = request.getParameter("DATE0");
		String sDate1 = request.getParameter("DATE1");
		String sStatus = request.getParameter("STATUS");
		String sResult = "";
		String sXml = "";
		String res = "";

		String msisdn0 = request.getParameter("MASTER");
		String pwd0 = request.getParameter("PWD");
		
		//msIsdn = msisdn0;
		System.out.println("msisdn="+msisdn0);
		System.out.println("pwd="+pwd0);
		if(msisdn0 != null)
		{
			msIsdn = msisdn0;
		}
		
		try{
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = document.createElement("BRC");
			document.appendChild(root);
			
			Element master = document.createElement("MASTER");
			master.appendChild(document.createTextNode(msIsdn));
			root.appendChild(master);
			
			Element slave = document.createElement("SLAVE");
			if(sSlave != null && sSlave.length() !=0)
				slave.appendChild(document.createTextNode(sSlave));
			else
				slave.appendChild(document.createTextNode("xxx"));
			root.appendChild(slave);
			
			Element date0 = document.createElement("DATE0");
			if(sDate0 != null && sDate0.length() != 0)
				date0.appendChild(document.createTextNode(sDate0));
			else
				date0.appendChild(document.createTextNode("0100"));
			root.appendChild(date0);
			
			Element date1 = document.createElement("DATE1");
			if(sDate1 != null && sDate1.length() != 0)
				date1.appendChild(document.createTextNode(sDate1));
			else
				date1.appendChild(document.createTextNode("2359"));
			root.appendChild(date1);
			
			Element status = document.createElement("STATUS");
			if(sStatus != null)
				status.appendChild(document.createTextNode(sStatus));
			else
				status.appendChild(document.createTextNode("2"));
			root.appendChild(status);
			
			Element result = document.createElement("RESULT");
			result.appendChild(document.createTextNode("1"));
			root.appendChild(result);
			
			if(pwd0 != null)
			{
				Element pwd = document.createElement("PWD");
				if(pwd != null && pwd0.length() != 0)
				{
					pwd.appendChild(document.createTextNode(pwd0));
					root.appendChild(pwd);
				}
			}
			
			
			source = new DOMSource(document);
       } 
        catch (Exception e) { 
        	System.out.println(e.getMessage());
        } 
		return source;
		

	}

	@Override
	public StreamSource getXSLT(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
