/**
 * 
 */
package com.cht.util.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author alberltin
 *
 */
public class OraDBConn {
	  private Context ctx = null;
	  private DataSource ds = null;
	  private Connection conn = null;
	  private Statement stmt = null;
	  private CallableStatement call=null;
	  private PreparedStatement pstmt=null;	  
	  private ResultSet rs=null;
	  private Vector Parameters=null;
	  private String Result="N/A";
	  private String Error="N/A";	  
	  private boolean Dolog=false;
	  private boolean CXT=true;
	  
	  private String DataSourceName;	  

	  public OraDBConn(String JndiString,boolean cxt){
	    DataSourceName=JndiString;
	    CXT=cxt;
	    Parameters=new Vector();
	  }
	  
	  public void setDoLog(boolean b){
		    Dolog=b;
	  }
	  
	  public void print(String msg){
		  System.out.println(msg);
		  if(Dolog){}
	  }
	  
	  public void setConnection() throws Exception{
	    try{	      
	        ctx = new InitialContext();	        
	        if(CXT)
	        {
	          Context envctx = (Context) ctx.lookup("java:comp/env");
	          ds = (DataSource) envctx.lookup(DataSourceName);
	        }else
	          ds=(DataSource)ctx.lookup(DataSourceName);
	        conn=ds.getConnection();
	      
	    }catch(Exception ex){
	    	print("ERROR ON OraDBConn.setConnection:"+ex.getMessage());
	    	throw ex;
	    }
	  }	  
	  
	  public void closeConnection(){
	    try {
			if(call!=null)
			  call.close();
			if(stmt!=null)
			  stmt.close();
			if(pstmt!=null)
			  pstmt.close();
			if(conn!=null)
			  conn.close();
			if(ctx!=null)
			  ctx.close();
	    }catch (Exception e) {
	    	print("ERROR ON OraDBConn.closeConnection:"+e.getMessage());
	    }
	    call = null;
	    stmt = null;
	    pstmt = null;
	    conn = null;
	    ds = null;
	    ctx = null;
	  }
	  
	  public String getResultMessage(){
		    return Result;
	  }

	  public String getErrorMessage(){
	    return Error;
	  }	  
	  
	  public void addParameter(PARAMETER p){
	    Parameters.add(p);
	  }

	  public void clsParameter(){
	    Parameters.clear();
	  }	  
	  
	  public void ExecProc(String proc,boolean auto){
	    int OffSet=3;
	    String pcall=" ";
	    pcall="{call ";
	    pcall=pcall+proc;
	    pcall=pcall+"(?,?";
	    for(int i=0;i<Parameters.size();i++)
	    {
	      pcall=pcall+",?";
	    }
	    pcall=pcall+")}";

	    try{
	      if(conn==null)
	    	  this.setConnection();

	      call = conn.prepareCall(pcall);
	      call.registerOutParameter(1, oracle.jdbc.driver.OracleTypes.VARCHAR);
	      call.registerOutParameter(2, oracle.jdbc.driver.OracleTypes.VARCHAR);

	      for(int i=0;i<Parameters.size();i++)
	      {
	        PARAMETER tmp=(PARAMETER)Parameters.elementAt(i);
	        switch(tmp.getType())
	        {
	          case PARAMETER.NUMBER:
	            call.setInt(OffSet+i,tmp.getNumber());
	            break;
	          case PARAMETER.VARCHAR2:
	            call.setString(OffSet+i,tmp.getVARCHAR());
	            break;
	          case PARAMETER.LONG:
	        	  call.setLong(OffSet+i, tmp.getLONG());
	          case PARAMETER.BOOLEAN:
	        	  call.setBoolean(OffSet+i, tmp.getBOOLEAN());
	          default:
	            call.setString(OffSet+i,tmp.getVARCHAR());
	            break;
	        }
	      }
	      call.execute();

	      Result=(String)call.getObject(1);
	      Error= (String) call.getObject(2);

	    }catch(Exception e)
	    {
	      print("ERROR ON ORADB.ExecProc:"+e.getMessage());
	    }finally
	    {
	    	if(auto)
	    		this.closeConnection();
	    	else
	    	{
	    		if(call!=null)
	    		{
	    			try{
	    			call.close();
	    			}catch(Exception ex){}
	    		}
	    	}
	    }

	  }
	  public String ExecFunc(String proc,boolean auto){

	    String value=null;
	    String sql="";
	    int OffSet=1;

	    String pcall="";
	    pcall=proc+"(";
	    for(int i=0;i<Parameters.size();i++)
	    {
	      if(i==0)
	        pcall=pcall+"?";
	      else
	        pcall=pcall+",?";
	    }
	    pcall=pcall+")";

	    try{
	      if(conn==null)
	    	  this.setConnection();

	      sql="select "+pcall+" as rs from dual";
	      pstmt = conn.prepareStatement(sql);
	      
	      for(int i=0;i<Parameters.size();i++)
	      {
	        PARAMETER tmp=(PARAMETER)Parameters.elementAt(i);
	        switch(tmp.getType())
	        {
	          case PARAMETER.NUMBER:
	            call.setInt(OffSet+i,tmp.getNumber());
	            break;
	          case PARAMETER.VARCHAR2:
	            call.setString(OffSet+i,tmp.getVARCHAR());
	            break;
	          case PARAMETER.LONG:
	        	  call.setLong(OffSet+i, tmp.getLONG());
	          case PARAMETER.BOOLEAN:
	        	  call.setBoolean(OffSet+i, tmp.getBOOLEAN());
	          default:
	            call.setString(OffSet+i,tmp.getVARCHAR());
	            break;
	        }
	      }
	      rs = pstmt.executeQuery();

	      while(rs.next()){
	        value=rs.getString("rs");
	      }

	      Result="success";

	    }catch(Exception e)
	    {
	      print("ERROR ON ORADB.ExecFunc:"+e.getMessage());
	      Result="fail";
	    }finally
	    {
	    	if(auto)
	    		this.closeConnection();
	    	else
	    	{
	    		if(pstmt!=null)
	    		{
	    			try{
	    				pstmt.close();
	    			}catch(Exception ex){}
	    		}
	    		if(rs!=null)
	    		{
	    			try{
	    				rs.close();
	    			}catch(Exception ex){}
	    		}
	    	}
	    }
	    return value;

	  }
}
