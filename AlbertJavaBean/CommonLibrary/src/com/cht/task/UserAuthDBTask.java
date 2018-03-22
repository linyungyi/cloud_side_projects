/**
 * 
 */
package com.cht.task;

import java.io.IOException;

import com.cht.dto.UserAuthDTO;
import com.cht.util.FlashcastTimeInMillisHelper;
import com.cht.util.ResourceLocator;
import com.cht.util.db.OraDBConn;
import com.cht.util.db.PARAMETER;

/**
 * @author alberltin
 *
 */
public class UserAuthDBTask implements TaskInf {

	/* (non-Javadoc)
	 * @see com.cht.core.service.task.TaskInf#process(java.lang.Object)
	 */
	public void process(String initiator, Object dto) {
		// 
        if (dto instanceof UserAuthDTO) {
            UserAuthDTO userAuth = (UserAuthDTO) dto;
            //System.out.println(userAuth.toString());
            /* Add Your DB access code bellow */
            String jndiDataSource;
            OraDBConn ora;
            FlashcastTimeInMillisHelper th=new FlashcastTimeInMillisHelper();
			try {
				jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
	            ora = new OraDBConn(jndiDataSource,false);//false: weblogic true: tomcat
	            ora.clsParameter();
	            ora.addParameter(new PARAMETER(th.getDateTime(userAuth.getD())));
	            ora.addParameter(new PARAMETER(userAuth.getUserid()));
	            ora.addParameter(new PARAMETER(userAuth.getDevid()));
	            ora.addParameter(new PARAMETER(userAuth.getSessid()));
	            ora.addParameter(new PARAMETER(userAuth.getAddr()));
	            ora.addParameter(new PARAMETER(userAuth.getAction()));
	            ora.addParameter(new PARAMETER(th.getDateTime(userAuth.getLogout_timeon())));
	            ora.addParameter(new PARAMETER(userAuth.getAct()));
	            ora.addParameter(new PARAMETER(userAuth.getHostAddress()));
	            ora.ExecProc("PROC_USERAUTH",true);//auot set/colse connecton
	            System.out.println("PROC_USERAUTH Result:"+ora.getResultMessage());
	            System.out.println("PROC_USERAUTH Error:"+ora.getErrorMessage());
			} catch (IOException e) {
				// 
				e.printStackTrace();
			}finally{
				ora=null;
			}
			/* Add Your DB access code above */
        } else {
            System.out.println("Invalid DTO: Required UserAuthDTO");
        } 
	}

}
