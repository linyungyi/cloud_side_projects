package com.my.db;


import java.net.InetAddress;

import org.apache.log4j.Logger;

import com.my.log.util.Log4j;

import DBPool.ConnPool;
import DBPool.SQLBridge;

public class DB{
	Logger logger = new Log4j(this.getClass().getName()).getLogger();
  static ConnPool cp ;
    /*public DB(){
        DBopen();
         }*/
    public DB(String mark){
    	if(cp==null)
        DBopen(mark, 10);
    }

    public DB(String mark, int connections){
    	logger.warn("start DBopen:"+connections);
    	if(cp==null)
        DBopen(mark, connections);
    }

    public void DBopen(String mark, int connections){
        try{
        	logger.warn("cp==null");
        	if(cp==null)
        		cp = new ConnPool();
            cp.setDriverName("com.mysql.jdbc.Driver");
            if (InetAddress.getLocalHost().toString().indexOf("10.144") != -1){
                cp.setJdbcURL("jdbc:mysql://127.0.0.1:3306/mail_log");
            } else{
                cp.setJdbcURL("jdbc:mysql://127.0.0.1:3306/mail_log");
            }
            cp.setUserName("hadoop");
            cp.setPassword("hadoop");
            cp.setMaxConnections(connections);
            cp.setDebugFlag(false);
            cp.setMark(mark);            
            cp.setConnectionSwitch("on");
        } catch (Exception ex){
            System.out.println("DBOPEN error:" + ex.toString());
        }
    }

    public void closeDB(){
        try{
            cp.setConnectionSwitch("off");
        } catch (Exception ex){
        }
    }

    public SQLBridge getSqlBridge(){
        SQLBridge sqlBridge = new SQLBridge();

        try{
            sqlBridge.setConnPool(cp);
            sqlBridge.setAutoCommit("False");
            sqlBridge.setConnectionSwitch("on");
        } catch (Exception ex){
            System.out.println("SQL Error:" + ex.toString());
        }
        return sqlBridge;
    }

}
