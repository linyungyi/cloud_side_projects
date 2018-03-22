<%@ page contentType="text/html; charset=Big5" import="java.sql.*"  import="java.net.*"  import="java.io.*"  import="java.util.*" %>

<%      
   response.setHeader("Expires", "Tue, 20 Aug 1996 14:25:27 GMT");
   response.setHeader("Pragma", "no-cache");
    
       java.util.Random myRandom = new java.util.Random();
       String ID = (String)session.getAttribute("ID");
        
   //-----------------判斷有沒有ID 登出了-------------------------------------------------------------------------------------------------------------
    String BV_SessionID=(String)session.getAttribute("BV_SessionID");      
    String BV_EngineID=(String)session.getAttribute("BV_EngineID");
       
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
				        System.out.println(content);   	
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
				  //System.out.println("IsSessionLogout:"+IsSessionLogout); 
				
				if (IsSessionLogout){
				     session.setAttribute("BV_SessionID",null);
				     session.setAttribute("BV_EngineID",null);
				     session.setAttribute("ID",null);
				}  


                              //---------------重新抓取seeeion--------------------------------
				ID = (String)session.getAttribute("ID");
				BV_SessionID=(String)session.getAttribute("BV_SessionID");      
				BV_EngineID=(String)session.getAttribute("BV_EngineID");

        if(BV_SessionID==null)
          {
              BV_SessionID="";
          }   
  
          if(BV_EngineID==null)
          {
              BV_EngineID="";
          } 
       
	  if(ID == null)
		{
			          String authUrl="http://auth.emome.net/emome/membersvc/AuthServlet?itype=4&nocache="+String.valueOf(myRandom.nextInt())+"&serviceId=9010&toURL=main.jsp&Type=Newbrc";
			          response.sendRedirect(authUrl); 
			          return;
	       }
              else
	       {
	              session.setAttribute("ID",ID);
	       }

  //-------------------------------------------------------------------------------------------------------------------------------------------------------
      
%>







<%



 request.setCharacterEncoding("big5");
 String type_des="",action="";

  action = request.getParameter("action");

  if(action==null)
  {
     response.sendRedirect("main.jsp");
		   return;
  }


 String res="";



  String setup_XML="<RING><UI>WEB</UI><MSISDN>"+ID+"</MSISDN><Action>"+action+"</Action></RING>";
    if(setup_XML.indexOf("null")>0)
       {
         response.sendRedirect("main.jsp");
	       return;
       }
 //-----------------------丟Socket--------------------------------------
    try
        {
          Socket socket=new Socket("10.1.14.35", 8002);
          BufferedReader In = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintStream Out = new PrintStream(socket.getOutputStream());
          Out.println(setup_XML);
          Out.flush();
          res =In.readLine();
          In.close();
          socket.close();
        }
    catch(Exception e)
       {
	         System.out.println(e.getMessage());
	         return;
        }
//System.out.println(join_XML);
//----------------解析res-------------------------------------------------
    if(res.indexOf("Success")>0)
    {
             response.sendRedirect("setup_result.jsp?result=Success&Type=status&action="+action);

    }
    else
    {
             response.sendRedirect("setup_result.jsp?result=fail&Type=status&action="+action);
    }


 %>