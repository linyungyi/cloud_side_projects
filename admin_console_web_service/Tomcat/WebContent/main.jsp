<%@ page contentType="text/html; charset=Big5" import="java.sql.*"  import="java.net.*"  import="java.io.*"  import="java.util.*" %>

<%      
   response.setHeader("Expires", "Tue, 20 Aug 1996 14:25:27 GMT");
   response.setHeader("Pragma", "no-cache");
    
       java.util.Random myRandom = new java.util.Random();
       String ID = (String)session.getAttribute("ID");
        
   //-----------------�P�_���S��ID �n�X�F-------------------------------------------------------------------------------------------------------------
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


                              //---------------���s���seeeion--------------------------------
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










    <jsp:useBean id="connPool" scope="application" class="DBConnect.ConnPool" />

	<jsp:useBean id="sqlBridge" scope="page" class="DBConnect.SQLBridge" >
	  <jsp:setProperty name="sqlBridge" property="connPool" value="<%=connPool%>" />
	  <jsp:setProperty name="sqlBridge" property="connectionSwitch" value="on" />
	</jsp:useBean>
<%
      String servicestatus="", Status_result="",type_des="",ALL_DAY="",weekday_result="",weekend_result="";
      String time_des="",vice_misidn="",start_time="",end_time="";
      String IS_ONENUMBER_desc="",IS_ONENUMBER="";
      
      
      
try
{  
      
      //--------------�g�@��g���O�_���]�w�H------------------------
     try
     {
         String SQL_join="select *  from time_setting  where msisdn='"+ID+"'";
         sqlBridge.execSQL(SQL_join);
         if(sqlBridge.nextRow())
	  {
            weekday_result="Y";
	  }
         else
          {
            weekday_result="N";
          }
       
       }
      catch(Exception e)
      {
             sqlBridge.destory();
       }
      //---------------�g���O�_�]�w�H-----------------------
     try
     {
         String SQL_weeken="select  *   from weeken_setting  where msisdn='"+ID+"'";
         sqlBridge.execSQL(SQL_weeken);
         if(sqlBridge.nextRow())
	        {
                   weekend_result="Y";
	        }
	       else
	        {
	            weekend_result="N";
	        }
       }
      catch(Exception e)
      {
             sqlBridge.destory();
       }   
     //---------------�O�_365�ѥ��ѭԳ]�w�H-----------------------
       try
       {
         String SQL_4="select  *  from time_setting aaa , weeken_setting bbb where aaa.msisdn=bbb.msisdn and aaa.vice_misidn=bbb.vice_misidn and aaa.start_time=bbb.start_time and aaa.end_time=bbb.end_time  and aaa.msisdn='"+ID+"'";
         sqlBridge.execSQL(SQL_4);
         if(sqlBridge.nextRow())
	          {
                    ALL_DAY="Y";
	          }
	         else
	          {
	               ALL_DAY="N";
	          }
       }
      catch(Exception e)
      {
             sqlBridge.destory();
       }        
         
     //---------------�Ҧ��Բӳ]�w���-----------------------
    
     if (weekday_result.compareTo("Y")==0 && weekend_result.compareTo("Y")==0 &&  ALL_DAY.compareTo("Y")==0 ) //�g�@��g��(Y)  �g��(Y) 
      {
             try
	       {
		  String SQL_setting2="select *  from time_setting  where msisdn='"+ID+"'";
		  sqlBridge.execSQL(SQL_setting2);
		  while(sqlBridge.nextRow())
			{
				vice_misidn=sqlBridge.getFieldString("vice_misidn");
				start_time=sqlBridge.getFieldString("start_time");
				  start_time=start_time.substring(0,2);
				end_time=sqlBridge.getFieldString("end_time");
				  if(end_time.compareTo("2359")==0)
				   {
				     end_time="24";
				   }		
				  else
				   { 	  
				     end_time=end_time.substring(0,2);
				   } 
				if(start_time.compareTo("00")==0 && end_time.compareTo("24")==0)
					{
					    time_des="�C����ɬq";
					}
			 }
	       
		}
	      catch(Exception e)
		{
			 sqlBridge.destory();
		} 		 	         
      }
       
         
         
         
         
    //--------------�A�Ȫ��A------------------------
    try
     {
         String SQL_join="select *  from user_profile  where msisdn='"+ID+"'";
         sqlBridge.execSQL(SQL_join);
         if(sqlBridge.nextRow())
	  {
	      servicestatus=sqlBridge.getFieldString("servicestatus");
              Status_result="Y";
	  }
         else
          {
               Status_result="N";
          }

       }
     catch(Exception e)
      {
             sqlBridge.destory();
       }
       

    if (Status_result.compareTo("Y")==0)
          {
              if(servicestatus.compareTo("1")==0)
                {
                    type_des="�Ұ�";

                }
               else
                {
                    type_des="�Ȱ�";
 
                }  
          }
         else
          {
              type_des="�|���ӯ����A��";
          } 
    
    
  //--------------�@�X�q�A�Ȫ��A------------------------
    try
     {
         String SQL_onenumber="select IS_ONENUMBER  from  user_profile  where msisdn='"+ID+"'";
         sqlBridge.execSQL(SQL_onenumber);
         if(sqlBridge.nextRow())
	        {
	            IS_ONENUMBER=sqlBridge.getFieldString("IS_ONENUMBER");
              if(IS_ONENUMBER.compareTo("1")==0)
               {
                    IS_ONENUMBER_desc="�@�X�q�Τ�";
               }
              else
               {
                    IS_ONENUMBER_desc="�D�@�X�q�Τ�";
               } 
	        }
       }
     catch(Exception e)
      {
             sqlBridge.destory();
       }
 
 
 
 
 
 }
   
catch (Exception e_ALL )
{

}
finally
{
                    sqlBridge.destory();

}
 
 
 %>	
	

<html>
<head>
<title>3G�A��--�����P��</title>
<meta http-equiv="Content-Type" content="text/html; charset=big5">
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--



function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
//-->
</script>
<script language="JavaScript">
function checknum( obj )
{
 for( var i=0; i< obj.value.length; i++ )
 {
 	if( obj.value.substring(i, i+1) < '0' || obj.value.substring(i, i+1) > '9' )
 	{
 		alert("�п�J�Ʀr!!");
 		obj.value="";
 	}
 	
 }
}
function checkform( Sform )
{
 with(Sform)
    {


       if( !(V_MSISDN.value.length==9 || V_MSISDN.value.length==10))
        {
           alert("�п�J���T�X��!");
           return false;
        }
         if( V_MSISDN.value.substring(0,1)!='0' || V_MSISDN.value.substring(0,2)=='01' || V_MSISDN.value.substring(0,4)=='0800' || V_MSISDN.value.substring(0,4)=='0204' )
        {
           alert("�п�J���T���X!");
           return false;
        }
      
    }
}
function checkform2( Sform )
{
 with(Sform)
    {

     if(vice_misidn.value.length!=0)
      {
	       if( !(vice_misidn.value.length==9 || vice_misidn.value.length==10))
	        {
	           alert("�п�J���T�X��!");
	           return false;
	        }
	         if( vice_misidn.value.substring(0,1)!='0' || vice_misidn.value.substring(0,2)=='01' || vice_misidn.value.substring(0,4)=='0800' || vice_misidn.value.substring(0,4)=='0204')
	        {
	           alert("�п�J���T���X!");
	           return false;
	        }
      }
    }
}        
</script>
<link href="style.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="675" border="0" align="left" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td align="center" valign="top"><table width="675" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td height="300" align="center" valign="top" ><table width="95%" border="0" cellspacing="0" cellpadding="0">
              
              <tr> 
                <td><table width="100%"  border="0" cellspacing="0" cellpadding="2">
                    <%
				      if (Status_result.compareTo("Y")==0)
                        {
				  %>
                    <tr align="left" valign="top"> 
                      <td width="12"><img src="images/icon_01.gif" width="8" height="18"></td>
                      <td rowspan="4" class="t2"><form name="form1" method="post" action="setup_exe.jsp" onSubmit="return checkform(this)">
                          <table width="522" border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td width="522" align="left" class="t1"> <span class="style2"><strong>�]�w�P���Ƹ� 
                                </strong> </span></td>
                            </tr>
                            <tr> 
                              <td align="left" class="t4"> �P���Ƹ��G 
                                <input name="V_MSISDN" type="text" class="t4" size="15" maxlength="10" onKeyUp="checknum(this)"></td>
                            </tr>
                            <tr> 
                              <td align="left" class="t2"><p>�����z�G�п�J���ئ�ʹq�ܸ��X�Υ��ܸ��X�A���ܸ��X�e���n�O�o�[�ϰ츹�X�@�I�Y�z�u�]�w�Ƹ��A���]�w�C��P���ɬq�A�t�αN�۰ʹw�]���C����ɬq�}�ҦP���\��C 
                                </p></td>
                            </tr>
                            <tr> 
                              <td align="left"><input name="Submit2222" type="submit" class="sty1" value="�T�w"> 
                              </td>
                            </tr>
                          </table>
                        </form></td>
                    </tr>
                    <tr align="left" valign="top"> 
                      <td>&nbsp;</td>
                    </tr>
                    <tr align="left" valign="top"> 
                      <td>&nbsp;</td>
                    </tr>
                    <tr align="left" valign="top"> 
                      <td>&nbsp;</td>
                    </tr>
                    <tr align="left" valign="top"> 
                      <td><img src="images/icon_01.gif" width="8" height="18"></td>
                      <td class="t1"> <strong>�˷R���Ȥ�z�n �A�z�ثe�A�ȳ]�w���G</strong></td>
                    </tr>
                    <tr align="left" valign="top"> 
                      <td>&nbsp;</td>
                      <td rowspan="4"><form name="form1" method="post" action="advance_exe.jsp" onSubmit="return checkform2(this)">
                          <table width="525" border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td width="525" align="left" class="t4">�D���G<%=ID%></td>
                            </tr>
                            <tr> 
                              <td align="left" class="t4"> �}�Үɶ��G�C�� 
                                <input name="start_time" type="text"  size="2" maxlength="2" class="t4" value="<%=start_time%>" onKeyUp="checknum(this)">
                                ��<strong> 
                                <input name="end_time" type="text" class="t4" size="2" maxlength="2"  value="<%=end_time%>" onKeyUp="checknum(this)">
                                <%
				    if(start_time.compareTo("00")==0 && end_time.compareTo("24")==0)
					  {
					%>
                                <font color="#FF0000"    > (���ɬq)</font> 
                                <%		  
					  }
				  %>
                                </strong></td>
                            </tr>
                            <tr> 
                              <td align="left" class="t4"> �P���Ƹ� �G<strong> 
                                <input name="vice_misidn" type="text" class="t4" size="15" maxlength="10" value="<%=vice_misidn%>" onKeyUp="checknum(this)">
                                </strong> </td>
                            </tr>
                            <tr> 
                              <td align="left" class="t2">�����z�G�}�Үɶ��]�w�A��쬰�p�ɡA�p�n�]�w�Ѧ��W8�ɨ�U��4�ɡA�п�J08��16 
                                �C </td>
                            </tr>
                            <tr> 
                              <td align="left" class="sty1"><input name="Submit223" type="submit" class="sty1" value="�T�w"></td>
                            </tr>
                          </table>
                        </form></td>
                    </tr>
                    <tr align="left" valign="top"> 
                      <td>&nbsp;</td>
                    </tr>
                    <tr align="left" valign="top"> 
                      <td>&nbsp;</td>
                    </tr>
                    <tr align="left" valign="top"> 
                      <td>&nbsp;</td>
                    </tr>
                    
                    <%}%>
                    <tr align="left" valign="top"> 
                      <td><img src="images/icon_01.gif" width="8" height="18"></td>
                      <td rowspan="2" class="t2"><form name="form2" method="post" action="status_exe.jsp">
                          <table width="480" border="0" cellspacing="0" cellpadding="0">
                            <tr> 
                              <td align="left" class="t1"><strong>�˷R���Ȥ�z�n�A�z�ثe�A�Ȫ��A���G 
                                <font color="#FF0000"><%=type_des%></font></strong> 
                              </td>
                            </tr>
                            <%
				   if (Status_result.compareTo("Y")==0)
                           {       
				%>
                            <tr> 
                              <td align="left" class="t4"> �����P���A�� 
                                <%
						     if(servicestatus.compareTo("1")==0)
                                {         
						  %>
                                <input name="action" type="radio" value="0">
                                �Ȱ� 
                                <%
						         }
								else
								  { 
						   %>
                                <input name="action" type="radio" value="1">
                                �Ұ� 
                                <%
							       }
							%>
                              </td>
                            </tr>
                            
                            <tr> 
                              <td align="left" class="t2"> �����z�G�]�w&quot;�Ȱ�&quot;�A�ȴ����A���믲�O����ú�ǡC</td>
                            </tr>
                            <tr> 
                              <td align="left"><input name="Submit22" type="submit" class="sty1" value="�T�w"></td>
                            </tr>
                            <%}%>
                          </table>
                        </form></td>
                    </tr>
                    <tr align="left" valign="top"> 
                      <td>&nbsp;</td>
                    </tr>
                    
					
					<%
					     if(IS_ONENUMBER.compareTo("1")==0)
                 {
					%>										
                    <tr align="left" valign="top"> 
                      <td><img src="images/icon_01.gif" width="8" height="18"></td>
                      <td class="t2"> <table width="480" border="0" cellspacing="0" cellpadding="0">
                          <tr> 
                            <td align="left" class="t1"><strong>�˷R���Ȥ�z�n�A�z�ثe���G 
                              <font color="#FF0000"><%=IS_ONENUMBER_desc%></font></strong> 
                            </td>
                          </tr>
                        </table></td>
                    </tr>
					<%     }
					%>
					
					
					
                  </table></td>
              </tr>
             
            </table>
            
          </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
