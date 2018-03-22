/**
 * 
 */
package com.cht.task;

import java.io.IOException;
import java.util.Iterator;

import com.cht.dto.FeedItemCollectionDTO;
import com.cht.dto.FeedItemDTO;
import com.cht.dto.UserDataDTO;
import com.cht.util.FlashcastTimeInMillisHelper;
import com.cht.util.ResourceLocator;
import com.cht.util.db.OraDBConn;
import com.cht.util.db.PARAMETER;

/**
 * @author alberltin
 *
 */
public class UserDataDBTask implements TaskInf {

	/* (non-Javadoc)
	 * @see com.cht.core.service.task.TaskInf#process(java.lang.Object)
	 */
	public void process(String initiator, Object dto) {
		// 
        if (dto instanceof UserDataDTO) {
            UserDataDTO userData = (UserDataDTO) dto;
            //System.out.println("Initiator.[" + initiator + "].Value[" + userData.toString()+ "]");
            /* Add Your DB access code bellow */
            String jndiDataSource;
            OraDBConn ora;
            String DevID;
            String TimeStamp;
            FlashcastTimeInMillisHelper th=new FlashcastTimeInMillisHelper();
			try {
				jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
	            ora = new OraDBConn(jndiDataSource,false);//false: weblogic true: tomcat
	            ora.clsParameter();
	            ora.addParameter(new PARAMETER(userData.getDevid()));
	            ora.addParameter(new PARAMETER((userData.getError()?1:0)));
	            ora.addParameter(new PARAMETER(userData.getFeedid()));
	            ora.addParameter(new PARAMETER((userData.getOutbound()?1:0)));
	            ora.addParameter(new PARAMETER(userData.getRequestid()));
	            ora.addParameter(new PARAMETER(userData.getSessid()));
	            ora.addParameter(new PARAMETER(userData.getSize()));	            
	            ora.addParameter(new PARAMETER(th.getDateTime(userData.getTimeStart())));
	            ora.addParameter(new PARAMETER(userData.getTsreq()));
	            ora.addParameter(new PARAMETER(userData.getUseraddr()));
	            ora.addParameter(new PARAMETER(userData.getUserid()));
	            ora.addParameter(new PARAMETER(userData.getHostAddress()));	            

	            ora.ExecProc("PROC_USERDATA",false);//auot set/colse connecton
	            System.out.println("PROC_USERDATA Result:"+ora.getResultMessage());
	            System.out.println("PROC_USERDATA Error:"+ora.getErrorMessage());
	            
	            if(ora.getResultMessage().compareToIgnoreCase("success")==0){
	            	DevID=userData.getDevid();
	            	TimeStamp=ora.getErrorMessage();
	            	FeedItemCollectionDTO feeditemCollection=userData.getFicDto();
	            	if(feeditemCollection!=null){
		            	ora.clsParameter();
		            	ora.addParameter(new PARAMETER(DevID));
		            	ora.addParameter(new PARAMETER(TimeStamp));
		            	ora.addParameter(new PARAMETER(feeditemCollection.getFeedId()));
		            	ora.addParameter(new PARAMETER((feeditemCollection.isDelta()?1:0)));
			            ora.ExecProc("PROC_USERDATA_FEED",false);//auot set/colse connecton
			            System.out.println("PROC_USERDATA_FEED Result:"+ora.getResultMessage());
			            System.out.println("PROC_USERDATA_FEED Error:"+ora.getErrorMessage());
			            
			            Iterator ir=feeditemCollection.getIterator();
			            if(ir!=null){
				            while(ir.hasNext()){
				            	FeedItemDTO fidto=(FeedItemDTO)ir.next();
				            	ora.clsParameter();
				            	ora.addParameter(new PARAMETER(DevID));
				            	ora.addParameter(new PARAMETER(TimeStamp));
				            	ora.addParameter(new PARAMETER(fidto.getId()));
				            	ora.addParameter(new PARAMETER(fidto.getType()));
				            	ora.addParameter(new PARAMETER(th.getDateTime(fidto.getLastModified())));
				            	ora.addParameter(new PARAMETER(fidto.getSizeInBytes()));
					            ora.ExecProc("PROC_USERDATA_ITEM",false);//auot set/colse connecton
					            System.out.println("PROC_USERDATA_ITEM Result:"+ora.getResultMessage());
					            System.out.println("PROC_USERDATA_ITEM Error:"+ora.getErrorMessage());
				            }
			            }

	            	}

	            }
	            
	            ora.closeConnection();
	            
			} catch (IOException e) {
				// 
				e.printStackTrace();
			}finally{
				ora=null;
			}
            /* Add Your DB access code above */
        } else {
            System.out.println("Invalid DTO: Required UserDataDTO");
        } 
	}

}
