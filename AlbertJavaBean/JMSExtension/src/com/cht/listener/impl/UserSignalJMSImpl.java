/**
 * 
 */
package com.cht.listener.impl;

import java.io.IOException;

import com.cht.dto.UserSignalDTO;
import com.cht.util.ResourceLocator;
import com.macromedia.flashcast.log.LogModuleContext;
import com.macromedia.flashcast.log.UserSignalModule;

/**
 * @author alberltin
 *
 */
public class UserSignalJMSImpl extends JMSBaseImpl implements UserSignalModule {

	private static final String LOGGER_NAME = "user.signal";
	private static final String TOPIC_FACTORY_NAME = "UserSignal.Topic.Factory";
	private static final String TOPIC_NAME = "UserSignal.Topic";
	
	public String getTopicFactoryName() throws IOException { return ResourceLocator.getProperty(TOPIC_FACTORY_NAME); }
	public String getTopicName() throws IOException { return ResourceLocator.getProperty(TOPIC_NAME); }
	public String getLoggerName() { return LOGGER_NAME; }	
	
	public void log(UserSignalJMSImpl.TransactionRecord transactionRecord) {

		UserSignalDTO dto = fillIn(transactionRecord);
		send(dto);
	}

    private UserSignalDTO fillIn(UserSignalModule.TransactionRecord transactionRecord) {
        
        UserSignalDTO dto = new UserSignalDTO();
        dto.setBatchId(transactionRecord.batchId);
        dto.setMessage(transactionRecord.message);
        dto.setNumDevices(transactionRecord.numDevices);
        dto.setProviderName(transactionRecord.providerName);
        dto.setSuccess(transactionRecord.success);
        dto.setTimeStart(transactionRecord.timeStart);
        
        return dto;
    }	

	public void close() {
		super.close();
	}

	public boolean init(LogModuleContext ctx) {
		return super.init(ctx);
	}
	
}
