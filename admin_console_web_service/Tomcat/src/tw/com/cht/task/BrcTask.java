package tw.com.cht.task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;

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

import tw.com.cht.authentication.*;
import tw.com.cht.core.BaseCore;

public class BrcTask implements BaseCore {

	public Source getSource(HttpServletRequest request){
		// TODO Auto-generated method stub
		Document document=null;
		Source source=null;
		
		Context initContext; 
		Context envContext; 
		Connection con = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    ResultSet rs1 = null;
	    
	    String msIsdn = "";
		
		try { 	        	
        	
        	/*
        	 <BRC>
				<MASTER>0933946930</MASTER>
				<SLAVE>034245116</SLAVE>
				<DATE0>0000</DATE0>
				<DATE1>2359</DATE1>
				<STATUS>1</STATUS>
				<RESULT>1</RESULT>
			</BRC>
        	 */
			String sSlave = request.getParameter("SLAVE");
			String sDate0 = request.getParameter("DATE0");
			String sDate1 = request.getParameter("DATE1");
			String sStatus = request.getParameter("STATUS");
			String sMsisdn = request.getParameter("MASTER");
			String sPasswd = request.getParameter("PWD");
			String sResult = "0";
			String sXml = "";
			String res = "";
			
			/*
			System.out.println("SLAVE:"+request.getParameter("SLAVE"));
			System.out.println("DATE0:"+request.getParameter("DATE0"));
			System.out.println("DATE1:"+request.getParameter("DATE1"));
			System.out.println("STATUS:"+request.getParameter("STATUS"));
			*/
			
			AacgClient client = new AacgClient(request);
			
			Hashtable userInfo = client.process();
			msIsdn = (String)userInfo.get(client.MSISDN);
			
			if(msIsdn == null)
			{
				msIsdn = "";
				if(sMsisdn !=null && sPasswd != null)
				{ //case: user/passwd login
					BrcAuthenticate brca=new BrcAuthenticate();
					if(brca.login(sMsisdn,sPasswd))
					{
						msIsdn = sMsisdn;
					}
				}
				
			}else
			{
				//System.out.println("Brc MSISDN:"+msIsdn);
				if(sSlave == null && sDate0 == null && sDate1 == null && sStatus == null && sPasswd !=null)
				{
					BrcAuthenticate brca=new BrcAuthenticate();
					if(!brca.updateUserPwd(msIsdn, sPasswd))
						sPasswd = "";
				}
			}
			
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = document.createElement("BRC");
			document.appendChild(root);
			
			if(sSlave != null && sDate0 != null && sDate1 != null && sStatus != null && msIsdn.trim().length()!=0)
			{
				sXml = "<RING_ALLDAY><UI>WEB</UI><MSISDN>"+msIsdn+"</MSISDN><V0:"+sDate0+"-"+sDate1+">"+sSlave+"</V0:"+sDate0+"-"+sDate1+"></RING_ALLDAY>";
				
				try
				{
					Socket socket=new Socket("10.1.14.35", 8002);
				    BufferedReader In = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				    PrintStream Out = new PrintStream(socket.getOutputStream());
				    Out.println(sXml);
				    Out.flush();
				    res =In.readLine();
				    In.close();
				    socket.close();
				 }
				 catch(Exception e)
				 {
					 System.out.println("BrcTask.SocketException1:" + e.getMessage());
					 sResult = "0";
				 }
			 //----------------¸ÑªRres-------------------------------------------------
				 if(res.indexOf("Success")>0)
				 {
					 sResult = "1";
				 }else
				 {
					 sResult = "0";
				 }
				 
				 sXml = "<RING><UI>WEB</UI><MSISDN>"+msIsdn+"</MSISDN><Action>"+sStatus+"</Action></RING>";
				 try
					{
						Socket socket=new Socket("10.1.14.35", 8002);
					    BufferedReader In = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					    PrintStream Out = new PrintStream(socket.getOutputStream());
					    Out.println(sXml);
					    Out.flush();
					    res =In.readLine();
					    In.close();
					    socket.close();
					 }
					 catch(Exception e)
					 {
						 System.out.println("BrcTask.SocketException2:" + e.getMessage());
						 sResult = "0";
					 }
				 //----------------¸ÑªRres-------------------------------------------------
				 if(res.indexOf("Success")>0)
				 {
					 sResult = "1";
				 }else
				 {
					 sResult = "0";
				 }
			}
			
			try {

				initContext = new InitialContext(); 
				envContext  = (Context)initContext.lookup("java:/comp/env"); 
				DataSource ds =(DataSource) envContext.lookup("jdbc/BRCDB");
			    con = ds.getConnection();
			    stmt = con.createStatement();
			    
		        rs1 = stmt.executeQuery("select *  from user_profile  where msisdn='"+msIsdn+"'");
		        //System.out.println("select *  from user_profile  where msisdn='"+msIsdn+"'");
		        if(rs1.next())
		  	  	{
		  	      	sStatus=rs1.getString("servicestatus");
		  	      	rs=stmt.executeQuery("select * from time_setting  where msisdn='"+msIsdn+"'");
			        while (rs.next()) {
			        	sSlave=rs.getString("vice_misidn");
						sDate0=rs.getString("start_time");
						//sDate0=sDate0.substring(0,2);
						sDate1=rs.getString("end_time");
						//if(sDate1.compareTo("2359")==0)
						//{
						//  sDate1="24";
						//}else
						//{ 	  
						//  sDate1=sDate1.substring(0,2);
						//} 
			        }
			        sResult = "1";
		  	  	}
		        else
		        {
		            sStatus = "2";
		            sSlave = "";
		            sDate0 = "";
		            sDate1 = "";
		            sResult = "1";
		        }
		        if(rs != null)
		        	rs.close();
		        if(rs1 != null)
		        	rs1.close();
		        if(stmt != null)
		        	stmt.close();
		        if(con != null)
		        	con.close();
			}
			catch (Exception e) {
				System.out.println("BrcTask.DBException1:" + e.toString());
				
			}
			
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
				date0.appendChild(document.createTextNode("0000"));
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
				status.appendChild(document.createTextNode("0"));
			root.appendChild(status);
			
			Element result = document.createElement("RESULT");
			result.appendChild(document.createTextNode(sResult));
			root.appendChild(result);
			
			if(sPasswd != null)
			{
				Element pwd = document.createElement("PWD");
				pwd.appendChild(document.createTextNode(sPasswd));
				root.appendChild(pwd);

			}
			
			source = new DOMSource(document);
       } 
        catch (Exception e) { 
        	System.out.println(e.getMessage());
        } 
		return source;
		
	}
	public StreamSource getXSLT(HttpServletRequest request){
		String XsltUri=(String)request.getAttribute("XsltUri");
		String XsltDir=(String)request.getAttribute("XsltUriDir");
		
		if(XsltUri==null)
			return null;//XsltUri="error.xsl";
		
		return new StreamSource(XsltDir+"/"+XsltUri);
	}

}
