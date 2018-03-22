package tw.com.cht.authentication;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;


public class AAA extends AacgClient {
	Context initContext; 
	Context envContext; 
	DataSource ds;
	Connection con = null;
    Statement stmt = null;
    ResultSet rs0 = null;
    
	public AAA(HttpServletRequest request){
		super(request);
		try{
			initContext = new InitialContext(); 
			envContext  = (Context)initContext.lookup("java:/comp/env"); 
			ds =(DataSource) envContext.lookup("jdbc/FCDB");
		}catch (Exception e) {
			System.out.println("AAA.AAA:" + e.toString());
			
		}
	}
	
	public Hashtable process(){
		super.process();
		String msIsdn = (String)userInfo.get(MSISDN);
		if(msIsdn != null)
		{
			try {
				con = ds.getConnection();
			    stmt = con.createStatement();
			    rs0 = stmt.executeQuery("select * from mbms_member where msisdn='"+msIsdn+"'");
			    if(!rs0.next())
			    {
			    	AAAHttpThread clientThread = new AAAHttpThread(super.httpRequest,msIsdn);
			    }
			}catch (Exception e) {
				System.out.println("BAAA.process:" + e.toString());
				
			}
			
		}
		return userInfo;
	}
}

class AAAHttpThread implements Runnable {
	Context initContext = null; 
	Context envContext = null; 
	DataSource ds = null;
	Connection con = null;
    Statement stmt = null;
	Thread runner = null;
	URL url=null;
    URLConnection uc = null;
    InputStream ins = null;
    BufferedReader br = null;
	
    String M_HTTP ="http://M_HTTP/service/dispatcher.jsp?";
    String temp , response;
    String MSISDN;
	/*public AAAHttpThread() {
	}*/
	public AAAHttpThread(HttpServletRequest request,String msisdn) {
		M_HTTP = M_HTTP + "Service=ApplySvc&TelNum=" + msisdn + "&Spsvc=FC&Action=A&From=TL310&AuthType=IP";
		MSISDN = msisdn;
		runner = new Thread(this); // (1) Create a new thread.
		runner.start(); // (2) Start the thread.
	}
	public void run() {
		try{
			url = new URL (M_HTTP);
			uc = url.openConnection();
			ins = (InputStream)uc.getInputStream();
			br = new BufferedReader (new InputStreamReader (ins));
			while ((temp = br.readLine()) != null){
				response+=temp;
			}
			response=response.trim();
			
			try{
				initContext = new InitialContext();
				envContext = (Context) initContext.lookup("java:/comp/env");
 
				ds = (DataSource) envContext.lookup("jdbc/FCDB");
				con = ds.getConnection();
				stmt=con.createStatement();

				Calendar cal = Calendar.getInstance();
			    String login_time= Integer.toString(cal.get(cal.YEAR))+"/"+Integer.toString(cal.get(cal.MONTH)+1)+"/"+Integer.toString(cal.get(cal.DATE))+" "+Integer.toString(cal.get(cal.HOUR))+":"+Integer.toString(cal.get(cal.MINUTE))+":"+Integer.toString(cal.get(cal.SECOND));
			    String SQLcmd;
			    
				if ( (response.indexOf("<Status>Success</Status>") >0)) {
					SQLcmd="insert into _AAA(MSISDN,STATUS,ERRMSG,TIME_STAMP,ACTION,RES_TIME)  values('"+MSISDN+"','OK',null,to_date('"+login_time+"','yyyy/mm/dd HH24:MI:SS'),'A',sysdate)";
					stmt.executeUpdate(SQLcmd);

				}
				else if((response.indexOf("<Status>99</Status>"))>0){
					SQLcmd="insert into _AAA(MSISDN,STATUS,ERRMSG,TIME_STAMP,ACTION,RES_TIME)  values('"+MSISDN+"','FAIL','"+response.substring(response.indexOf("<Msg>")+5,response.indexOf("</Msg>"))+"',to_date('"+login_time+"','yyyy/mm/dd HH24:MI:SS'),'A',sysdate)";
					stmt.executeUpdate(SQLcmd);

				}
				else{
					SQLcmd="insert into _AAA(MSISDN,STATUS,ERRMSG,TIME_STAMP,ACTION,RES_TIME)  values('"+MSISDN+"','FAIL','"+response.substring(response.indexOf("<ErrorMsg>")+10,
					response.indexOf("</ErrorMsg>"))+"',to_date('"+login_time+"','yyyy/mm/dd HH24:MI:SS'),'A',sysdate)";
					stmt.executeUpdate(SQLcmd);

				}
			}catch(Exception dbex)
			{
				System.out.println("AAAHttpThread.run database exception:"
						+ dbex.toString());
			}finally
			{
				if(stmt!=null)
				{
					stmt.close();
					stmt = null;
				}
				if(con!=null)
				{
					con.close();
					con = null;
				}
				
			}
			
		}catch (Exception ex) {
			System.out.println("AAAHttpThread.run:"
					+ ex.getMessage());
			
		}finally
		{
			br = null;
			ins = null;
			uc = null;
			url = null;
			
		}

	}
}