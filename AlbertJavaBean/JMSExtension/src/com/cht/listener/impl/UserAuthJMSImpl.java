/**
 * 
 */
package com.cht.listener.impl;

import java.io.IOException;

import com.cht.dto.UserAuthDTO;
import com.cht.util.ResourceLocator;
import com.macromedia.flashcast.log.LogModuleContext;
import com.macromedia.flashcast.log.UserAuthModule;

/**
 * @author alberltin
 *
 */
public class UserAuthJMSImpl extends JMSBaseImpl implements UserAuthModule {

	private static final String LOGGER_NAME = "user.auth";
	private static final String TOPIC_FACTORY_NAME = "UserAuth.Topic.Factory";
	private static final String TOPIC_NAME = "UserAuth.Topic";
	
	public String getTopicFactoryName() throws IOException { return ResourceLocator.getProperty(TOPIC_FACTORY_NAME); }
	public String getTopicName() throws IOException { return ResourceLocator.getProperty(TOPIC_NAME); }
	public String getLoggerName() { return LOGGER_NAME; }	
	
	public void login(long arg0, String arg1, String arg2, String arg3,
			String arg4, String arg5) {

        UserAuthDTO dto = new UserAuthDTO();
        dto.setAct("login");
        dto.setD(arg0);
        dto.setUserid(arg1);
        dto.setDevid(arg2);
        dto.setSessid(arg3);
        dto.setAddr(arg4);
        dto.setAction(arg5);
        dto.setLogout_timeon(0);
        
        send(dto);
	}

	public void logout(long arg0, String arg1, String arg2, String arg3,
			String arg4, String arg5, long arg6) {

        UserAuthDTO dto = new UserAuthDTO();
        dto.setAct("logout");
        dto.setD(arg0);
        dto.setUserid(arg1);
        dto.setDevid(arg2);
        dto.setSessid(arg3);
        dto.setAddr(arg4);
        dto.setAction(arg5);
        dto.setLogout_timeon(arg6);
        
        send(dto);
	}
	
	public void close() {
		super.close();
	}

	public boolean init(LogModuleContext ctx) {
		return super.init(ctx);
	}
	
}
