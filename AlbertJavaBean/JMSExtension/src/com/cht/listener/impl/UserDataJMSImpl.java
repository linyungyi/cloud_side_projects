package com.cht.listener.impl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import com.cht.dto.FeedItemCollectionDTO;
import com.cht.dto.FeedItemDTO;
import com.cht.dto.UserDataDTO;
import com.cht.util.ResourceLocator;
import com.macromedia.flashcast.item.FeedItem;
import com.macromedia.flashcast.log.LogModuleContext;
import com.macromedia.flashcast.log.UserDataModule;
/**
 * @author alberltin
 *
 */
public class UserDataJMSImpl extends JMSBaseImpl implements UserDataModule {

	private static final String LOGGER_NAME = "user.data";
	private static final String TOPIC_FACTORY_NAME = "UserData.Topic.Factory";
	private static final String TOPIC_NAME = "UserData.Topic";
	
	public String getTopicFactoryName() throws IOException { return ResourceLocator.getProperty(TOPIC_FACTORY_NAME); }
	public String getTopicName() throws IOException { return ResourceLocator.getProperty(TOPIC_NAME); }
	public String getLoggerName() { return LOGGER_NAME; }	
	
	public void log(UserDataModule.TransactionRecord transactionRecord) {

		UserDataDTO dto = fillIn(transactionRecord);
		send(dto);
	}

    private UserDataDTO fillIn(UserDataModule.TransactionRecord transactionRecord) {
        
        UserDataDTO dto = new UserDataDTO();
        dto.setDevid(transactionRecord.devid);
        dto.setError(transactionRecord.error);
        dto.setFeedid(transactionRecord.feedid);
        dto.setOutbound(transactionRecord.outbound);
        dto.setRequestid(transactionRecord.requestid);
        dto.setSessid(transactionRecord.sessid);
        dto.setSize(transactionRecord.size);
        dto.setTimeStart(transactionRecord.timeStart);
        dto.setTsreq(transactionRecord.tsreq);
        dto.setUseraddr(transactionRecord.useraddr);
        dto.setUserid(transactionRecord.userid);
        
        FeedItemCollectionDTO ficdto=new FeedItemCollectionDTO();
        if(transactionRecord.coll!=null){
            ficdto.setDelta(transactionRecord.coll.getIsDelta());
            ficdto.setFeedId(transactionRecord.coll.getFeedId());
            Iterator il=transactionRecord.coll.getItemIterator();
            HashSet hs=new HashSet();
            while(il.hasNext()){
            	FeedItem fi=(FeedItem)il.next();
            	FeedItemDTO fidto=new FeedItemDTO();
            	fidto.setId(fi.getId());
            	fidto.setLastModified(fi.getLastModified());
            	fidto.setSizeInBytes(fi.getSizeInBytes());
            	fidto.setType(fi.getType());
            	hs.add(fidto);
            }
            ficdto.setIterator(hs.iterator());
        }        
        dto.setFicDto(ficdto);
        
        return dto;
    }
    
	public void close() {
		super.close();
	}

	public boolean init(LogModuleContext ctx) {
		return super.init(ctx);
	}
	
}
