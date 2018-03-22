/**
 * 
 */
package com.cht.task;

import java.io.IOException;

import com.cht.dto.UserErrorDTO;
import com.cht.util.FlashcastTimeInMillisHelper;
import com.cht.util.ResourceLocator;
import com.cht.util.db.OraDBConn;
import com.cht.util.db.PARAMETER;

/**
 * @author alberltin
 *
 */
public class UserErrorDBTask implements TaskInf {

	/* (non-Javadoc)
	 * @see com.cht.core.service.task.TaskInf#process(java.lang.Object)
	 */
	public void process(String initiator, Object dto) {
		// 
        if (dto instanceof UserErrorDTO) {
            UserErrorDTO userError = (UserErrorDTO) dto;
            //System.out.println(userError.toString());
            /* Add Your DB access code bellow */
            String jndiDataSource;
            OraDBConn ora;
            FlashcastTimeInMillisHelper th=new FlashcastTimeInMillisHelper();
			try {
				jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
	            ora = new OraDBConn(jndiDataSource,false);//false: weblogic true: tomcat
	            ora.clsParameter();
	            ora.addParameter(new PARAMETER(userError.getDevid()));
	            ora.addParameter(new PARAMETER(userError.getError()));
	            ora.addParameter(new PARAMETER(userError.getFeedappid()));
	            ora.addParameter(new PARAMETER(th.getDateTime(userError.getTime())));
	            ora.addParameter(new PARAMETER(userError.getUserid()));
	            ora.addParameter(new PARAMETER(userError.getHostAddress()));
	            ora.ExecProc("PROC_USERERROR",true);//auot set/colse connecton
	            System.out.println("PROC_USERERROR Result:"+ora.getResultMessage());
	            System.out.println("PROC_USERERROR Error:"+ora.getErrorMessage());
			} catch (IOException e) {
				// 
				e.printStackTrace();
			}finally{
				ora=null;
			}
			/* Add Your DB access code above */
        } else {
            System.out.println("Invalid DTO: Required UserErrorDTO");
        } 
	}

}
