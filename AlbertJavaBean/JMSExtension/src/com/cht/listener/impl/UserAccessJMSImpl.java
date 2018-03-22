package com.cht.listener.impl;

import java.io.IOException;

import com.cht.dto.UserAccessDTO;
import com.cht.util.ResourceLocator;
import com.macromedia.flashcast.log.LogModuleContext;
import com.macromedia.flashcast.log.UserAccessModule;
/**
 * @author alberltin
 *
 */
public class UserAccessJMSImpl extends JMSBaseImpl implements UserAccessModule {

	private static final String LOGGER_NAME = "user.access";
	private static final String TOPIC_FACTORY_NAME = "UserAccess.Topic.Factory";
	private static final String TOPIC_NAME = "UserAccess.Topic";
	
	public String getTopicFactoryName() throws IOException { return ResourceLocator.getProperty(TOPIC_FACTORY_NAME); }
	public String getTopicName() throws IOException { return ResourceLocator.getProperty(TOPIC_NAME); }
	public String getLoggerName() { return LOGGER_NAME; }	
	
    public void log(UserAccessModule.TransactionRecord transactionRecord) {
        
    	UserAccessDTO dto = fillIn(transactionRecord);
    	send(dto);
    }

    private UserAccessDTO fillIn(UserAccessModule.TransactionRecord transactionRecord) {
        
        UserAccessDTO dto = new UserAccessDTO();
        dto.setDataIn(transactionRecord.dataIn);
        dto.setDataOut(transactionRecord.dataOut);
        dto.setDeviceId(transactionRecord.devid);
        dto.setHeaderIn(transactionRecord.headerIn);
        dto.setHeaderOut(transactionRecord.headerOut);
        dto.setRequest(transactionRecord.request);
        dto.setRequestId(transactionRecord.requestid);
        dto.setSessionId(transactionRecord.sessid);
        dto.setTimeEnd(transactionRecord.timeEnd);
        dto.setTimeStart(transactionRecord.timeStart);
        dto.setUserAddress(transactionRecord.useraddr);
        dto.setUserId(transactionRecord.userid);
        
        return dto;
    }
    
	public void close() {
		super.close();
	}

	public boolean init(LogModuleContext ctx) {
		return super.init(ctx);
	}
	
}
