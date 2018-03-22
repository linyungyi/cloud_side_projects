/**
 * 
 */
package com.cht.listener.impl;

import java.io.IOException;

import com.cht.dto.UserErrorDTO;
import com.cht.util.ResourceLocator;
import com.macromedia.flashcast.log.LogModuleContext;
import com.macromedia.flashcast.log.UserErrorModule;

/**
 * @author alberltin
 *
 */
public class UserErrorJMSImpl extends JMSBaseImpl implements UserErrorModule {

	private static final String LOGGER_NAME = "user.error";
	private static final String TOPIC_FACTORY_NAME = "UserError.Topic.Factory";
	private static final String TOPIC_NAME = "UserError.Topic";
	
	public String getTopicFactoryName() throws IOException { return ResourceLocator.getProperty(TOPIC_FACTORY_NAME); }
	public String getTopicName() throws IOException { return ResourceLocator.getProperty(TOPIC_NAME); }
	public String getLoggerName() { return LOGGER_NAME; }	
	
	public void log(UserErrorModule.TransactionRecord transactionRecord) {
	      
        UserErrorDTO dto = fillIn(transactionRecord);
        send(dto);
	}

    private UserErrorDTO fillIn(UserErrorModule.TransactionRecord transactionRecord) {
        
        UserErrorDTO dto = new UserErrorDTO();
        dto.setDevid(transactionRecord.devid);
        dto.setError(transactionRecord.error);
        dto.setFeedappid(transactionRecord.feedappid);
        dto.setTime(transactionRecord.time);
        dto.setUserid(transactionRecord.userid);
        
        return dto;
    }	

	public void close() {
		super.close();
	}

	public boolean init(LogModuleContext ctx) {
		return super.init(ctx);
	}
	
}
