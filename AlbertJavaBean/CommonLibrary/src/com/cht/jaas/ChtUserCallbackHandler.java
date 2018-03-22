/**
 * 
 */
package com.cht.jaas;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextInputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.log4j.Logger;

import com.cht.util.ResourceLocator;
import com.cht.util.db.OraDBConn;
import com.cht.util.db.PARAMETER;
import com.macromedia.flashcast.net.csp.EosProtocol;
import com.macromedia.flashcast.server_common.jaas.EosServletCallbackHandler;
import com.macromedia.flashcast.server_common.servlet.EosServletRequest;
import com.macromedia.util.log4j.EosLog4jLogger;
import com.macromedia.util.log4j.L7dMessageGenerator;

/**
 * @author alberltin
 *
 */

public class ChtUserCallbackHandler implements EosServletCallbackHandler {

    static Logger log = EosLog4jLogger.getLogger(ChtUserCallbackHandler.class);
    EosServletRequest m_req;
	
	public void setRequest(EosServletRequest req) {
		m_req=req;
	}

	protected String getIMEI()
    {
		return m_req.getParameter(EosProtocol.ATTRIB_DEVICE_ID);
    }

    protected String getDeviceType()
    {
    	return m_req.getParameter(EosProtocol.ATTRIB_DEVICE_DEV);
    }

    protected String getClientType()
    {
    	return m_req.getParameter(EosProtocol.ATTRIB_DEVICE_CLIENT);
    }

    protected String getUsername()
    {
    	return m_req.getParameter(EosProtocol.ATTRIB_USERNAME);
    }

    private String getPassword()
    {
        String s = m_req.getParameter("password");
        return s == null ? null : s.toLowerCase();
    }

    private String getMSISDN(){
    	String msisdn = null;
    	String s = m_req.getHeader("MSISDN");
    	StringTokenizer tokens = new StringTokenizer(s, " ");
    	if (tokens.countTokens() < 2) {
    		//error
    	} else {
    		for (int i=0; i<2; i++)
    			msisdn = tokens.nextToken();
    	}
    	msisdn="0933946930";
    	return msisdn;
    }
    
    private String getUserID(String devid){
    	String userid = null;
    	//TODO: implement get user id for callback handler
    	String jndiDataSource = "";
    	OraDBConn ora;
    	try {
			jndiDataSource = ResourceLocator.getProperty("DB.ConnectionPool");
			ora = new OraDBConn(jndiDataSource,false);//false: weblogic true: tomcat
			ora.clsParameter();
			ora.addParameter(new PARAMETER(devid));
			userid=ora.ExecFunc("FUNC_GETUSERID",true);//auot set/colse connecton
			
		} catch (IOException e) {
			e.printStackTrace();
			if(log.isDebugEnabled())
				log.debug("DeviceID = " + devid + " can't get UserID correctly.");			
		} finally {
			ora=null;
		}
    	return userid;
    }

    private String getClientIP(){
    	String client = "";
    	String s = m_req.getHeader("MSISDN");
    	StringTokenizer tokens = new StringTokenizer(s, " ");
    	if (tokens.countTokens() < 2) {
    		//error
    	} else {
    		client = tokens.nextToken();
    	}
    	return client;
    }        
    
	public void handle(Callback[] cblist) throws IOException, UnsupportedCallbackException {
		
		for (int i=0; i<cblist.length; i++) {		
			if (cblist[i] instanceof NameCallback) {
				NameCallback nc = (NameCallback) cblist[i];
				String prompt = nc.getPrompt();
				String sztext = nc.getDefaultName();
				/*
				if(log.isDebugEnabled())
					log.debug("auth provider requesting name with prompt of \"" +
							nc.getPrompt() + "\" and default name of \"" + nc.getDefaultName() + "\"");
							*/
				
				if(log.isDebugEnabled())
					log.debug("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
											
				if (prompt != null){
					if (prompt.equals("imei")) {
						sztext = getIMEI();
					} else if (prompt.equals("username")) {
						sztext = getUsername();
					} else if (prompt.equals("userid")) {
						sztext = getUserID(getMSISDN());
					} else if (prompt.equals("msisdn")) {
						sztext = getMSISDN();
					} else if (prompt.equals("clientip")) {
						sztext = getClientIP();
					}					
				}
				
				if (sztext != null) nc.setName(sztext);

				if(log.isDebugEnabled())
					log.debug("auth provider requesting name with prompt of \"" +
							nc.getPrompt() + "\" name " + nc.getName()+ " and default name of \"" + nc.getDefaultName() + "\"");
				
				
			} else if (cblist[i] instanceof TextInputCallback) {
				
				TextInputCallback tc = (TextInputCallback) cblist[i];
				String prompt = tc.getPrompt();
				String sztext = tc.getDefaultText();

				
				if (prompt !=null){
					if (prompt.equals("devicetype")){
						sztext = getDeviceType();
					} else if (prompt.equals("clienttype")){
						sztext = getClientType();
					}
				}
				if (sztext != null) tc.setText(sztext);
				if(log.isDebugEnabled())
					log.debug("auth provider requesting text with prompt of \"" +
							tc.getPrompt() + "\" name " + tc.getText() + " and default name of \"" + tc.getDefaultText() + "\"");
				
			} else if (cblist[i] instanceof PasswordCallback) {
				/* This is the callback asking for the password. */
				PasswordCallback 	pc = (PasswordCallback) cblist[i];
				String				pass = null;

				if (log.isDebugEnabled())
                    log.debug("auth provider requesting password with prompt of \"" +
					    pc.getPrompt() + "\"");

				/* If we find the password, set it. */
				if ((pass = this.getPassword()) != null)
					pc.setPassword(pass.toCharArray());

			} else {
				/* We don't support this callback type. */
				throw new UnsupportedCallbackException(cblist[i], 
				        new L7dMessageGenerator(log).l7dmessage("ERROR_JAAS_CALLBACK_NOT_SUPPORTED", 
				                new Object[] {}));				
			}
			
		}
	}

}
