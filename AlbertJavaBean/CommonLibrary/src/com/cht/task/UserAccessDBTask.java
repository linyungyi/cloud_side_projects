/*
 * UserAccessDBTask.java
 *
 * Created on August 21, 2007, 6:52 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.cht.task;

import java.io.IOException;

import com.cht.dto.UserAccessDTO;
import com.cht.util.FlashcastTimeInMillisHelper;
import com.cht.util.ResourceLocator;
import com.cht.util.db.OraDBConn;
import com.cht.util.db.PARAMETER;

/**
 *
 * @author myf
 */
public class UserAccessDBTask implements TaskInf {
    
    /** Creates a new instance of UserAccessDBTask */
    public UserAccessDBTask() {
    }
    
	public void process(String initiator, Object dto) {
        
        if (dto instanceof UserAccessDTO) {
            UserAccessDTO userAccess = (UserAccessDTO) dto;
            //System.out.println(userAccess.toString());
            /* Add Your DB access code bellow */
            String jndiDataSource;
            OraDBConn ora;
            FlashcastTimeInMillisHelper th=new FlashcastTimeInMillisHelper();
			try {
				jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
	            ora = new OraDBConn(jndiDataSource,false);//false: weblogic true: tomcat
	            ora.clsParameter();
	            ora.addParameter(new PARAMETER(th.getDateTime(userAccess.getDataIn())));
	            ora.addParameter(new PARAMETER(th.getDateTime(userAccess.getDataOut())));
	            ora.addParameter(new PARAMETER(userAccess.getDeviceId()));
	            ora.addParameter(new PARAMETER(th.getDateTime(userAccess.getHeaderIn())));
	            ora.addParameter(new PARAMETER(th.getDateTime(userAccess.getDataOut())));
	            ora.addParameter(new PARAMETER(userAccess.getRequest()));
	            ora.addParameter(new PARAMETER(userAccess.getRequestId()));
	            ora.addParameter(new PARAMETER(userAccess.getSessionId()));
	            ora.addParameter(new PARAMETER(th.getDateTime(userAccess.getTimeEnd())));
	            ora.addParameter(new PARAMETER(th.getDateTime(userAccess.getTimeStart())));
	            ora.addParameter(new PARAMETER(userAccess.getUserAddress()));
	            ora.addParameter(new PARAMETER(userAccess.getUserId()));
	            ora.addParameter(new PARAMETER(userAccess.getHostAddress()));
	            ora.ExecProc("PROC_USERACCESS",true);//auot set/colse connecton
	            System.out.println("PROC_USERACCESS Result:"+ora.getResultMessage());
	            System.out.println("PROC_USERACCESS Error:"+ora.getErrorMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				ora=null;
			}
			/* Add Your DB access code above */
            
        } else {
            System.out.println("Invalid DTO: Required UserAccessDTO");
        } 
    }
}
