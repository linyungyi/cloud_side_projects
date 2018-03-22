/**
 * 
 */
package com.cht.task;

import java.io.IOException;

import com.cht.dto.UserSignalDTO;
import com.cht.util.FlashcastTimeInMillisHelper;
import com.cht.util.ResourceLocator;
import com.cht.util.db.OraDBConn;
import com.cht.util.db.PARAMETER;

/**
 * @author alberltin
 *
 */
public class UserSignalDBTask implements TaskInf {

	/* (non-Javadoc)
	 * @see com.cht.core.service.task.TaskInf#process(java.lang.Object)
	 */
	public void process(String initiator, Object dto) {
		// 
        if (dto instanceof UserSignalDTO) {
            UserSignalDTO userSignal = (UserSignalDTO) dto;
            //System.out.println(userSignal.toString());
            /* Add Your DB access code bellow */
            String jndiDataSource;
            OraDBConn ora;
            FlashcastTimeInMillisHelper th=new FlashcastTimeInMillisHelper();
			try {
				jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
	            ora = new OraDBConn(jndiDataSource,false);//false: weblogic true: tomcat
	            ora.clsParameter();
	            ora.addParameter(new PARAMETER(userSignal.getBatchId()));
	            ora.addParameter(new PARAMETER(userSignal.getMessage()));
	            ora.addParameter(new PARAMETER(userSignal.getNumDevices()));
	            ora.addParameter(new PARAMETER(userSignal.getProviderName()));
	            ora.addParameter(new PARAMETER((userSignal.isSuccess()?1:0)));
	            ora.addParameter(new PARAMETER(th.getDateTime(userSignal.getTimeStart())));
	            ora.addParameter(new PARAMETER(userSignal.getHostAddress()));
	            ora.ExecProc("PROC_USERSIGNAL",true);//auot set/colse connecton
	            System.out.println("PROC_USERSIGNAL Result:"+ora.getResultMessage());
	            System.out.println("PROC_USERSIGNAL Error:"+ora.getErrorMessage());
			} catch (IOException e) {
				// 
				e.printStackTrace();
			}finally{
				ora=null;
			}
			/* Add Your DB access code above */
        } else {
            System.out.println("Invalid DTO: Required UserSignalDTO");
        } 
	}

}
