<%@ page contentType="text/html; charset=Big5" import="java.sql.*"  import="java.io.*" import="java.util.*" import="java.net.*"   %>
<%
 String alarm_String="",alarm_String2="";
%>
<%
 try
   {
%>            
	<jsp:useBean id="connPool" scope="application" class="DBConnect.ConnPool" >
	  <jsp:setProperty name="connPool" property="driverName" value="oracle.jdbc.driver.OracleDriver" />
	  <jsp:setProperty name="connPool" property="maxConnections" value="10" />
	  <jsp:setProperty name="connPool" property="jdbcURL" value="jdbc:oracle:thin:@10.1.14.45:1521:rbt" />
	  <jsp:setProperty name="connPool" property="userName" value="MUTIRING" />
	  <jsp:setProperty name="connPool" property="password" value="MUTIRING" />
	  <jsp:setProperty name="connPool" property="connectionSwitch" value="on" />
	</jsp:useBean>
<%
   }
  catch(Exception e)
   {
               
            response.sendRedirect("check.html");
             return;
   }  
       


      String BV_SessionID=request.getParameter("BV_SessionID");
			      String BV_EngineID=request.getParameter("BV_EngineID");
			
				String session_String="?BV_SessionID="+BV_SessionID+"&BV_EngineID="+BV_EngineID;
				//String Session_url="http://auth.emome.net/emome/GetSession"+session_String;
				String Session_url="http://10.1.3.76/emome/GetSession"+session_String;
                                URL aURL = new URL(Session_url);
				URLConnection connection = aURL.openConnection();

				BufferedReader in = new BufferedReader(
				                new InputStreamReader(connection.getInputStream()));
				      String line=null;
				      String content="";
				      while( (line=in.readLine())!=null) {
				        content+=line;
				        	
				      }
				if (in!=null){
				      in.close();
				}   
				boolean IsSessionLogout=false;      
				if (content.indexOf("true")!=-1){
				         IsSessionLogout=false; 
				}else{
				         IsSessionLogout=true; 
				}
				 
				
				if (IsSessionLogout){
				     session.setAttribute("BV_SessionID",null);
				     session.setAttribute("BV_EngineID",null);
				     session.setAttribute("ID",null);
				   
				}



	response.sendRedirect("../Newbrc/instruction.jsp");
	

%>