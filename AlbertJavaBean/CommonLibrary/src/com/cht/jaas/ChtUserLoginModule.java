/**
 * 
 */
package com.cht.jaas;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.TextInputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;

import com.macromedia.auth.principals.ClientTypePrincipal;
import com.macromedia.auth.principals.DevicePrincipal;
import com.macromedia.auth.principals.DeviceTypePrincipal;
import com.macromedia.auth.principals.UserPrincipal;
import com.macromedia.flashcast.auth.CoreLoginModule;
import com.macromedia.util.log4j.L7dMessageGenerator;

/**
 * @author alberltin
 *
 */
public class ChtUserLoginModule extends CoreLoginModule {

	protected static Logger log = Logger.getLogger(ChtUserLoginModule.class.getPackage().getName());

	public Logger getLogger() {
		return log;
	}


	public boolean login() throws LoginException {

		if (this.m_cb == null)
			throw new LoginException(new L7dMessageGenerator(log).l7dmessage("ERROR_AUTH_NO_CALLBACK", new Object[] {}));

		NameCallback      imei 			= new NameCallback("imei");
		NameCallback	  msisdn 		= new NameCallback("msisdn");
		NameCallback	  userid 		= new NameCallback("userid");
		NameCallback	  username 		= new NameCallback("username");
		NameCallback	  clientip 		= new NameCallback("clientip");
		TextInputCallback clienttype 	= new TextInputCallback("clienttype");
		TextInputCallback devicetype 	= new TextInputCallback("devicetype");
		Callback[] cblist = {imei, msisdn, userid, username, clientip, clienttype, devicetype};

		try {
			this.m_cb.handle(cblist);
		} catch (IOException ioe) {
			throw new LoginException(new L7dMessageGenerator(log).l7dmessage("ERROR_AUTH_NO_INFORMATION",
						new Object[] {ioe.toString()}));
		} catch (UnsupportedCallbackException uce) {
			throw new LoginException(new L7dMessageGenerator(log).l7dmessage("ERROR_AUTH_NO_SUPPORT",
					new Object[] {uce.getCallback().toString()}));
		}

        if ((devicetype.getText()) == null) {
			throw new LoginException(new L7dMessageGenerator(log).l7dmessage("ERROR_AUTH_NO_DEVICETYPE", new Object[] {}));
		}
		if ((clienttype.getText()) == null) {
			throw new LoginException(new L7dMessageGenerator(log).l7dmessage("ERROR_AUTH_NO_CLIENTTYPE", new Object[] {}));
		}
		if (imei.getName() == null && msisdn.getName() == null) {
			throw new LoginException(new L7dMessageGenerator(log).l7dmessage("ERROR_AUTH_NO_DEVICEID", new Object[] {}));
		}
		
		//set principals
		if (msisdn.getName() != null) {
			this.m_principals.add(new DevicePrincipal(msisdn.getName()));
			this.m_name = msisdn.getName();
		} else {
			this.m_principals.add(new DevicePrincipal(imei.getName()));
			this.m_name = imei.getName();
		}
		
		this.m_principals.add(new ClientTypePrincipal(clienttype.getText()));
		this.m_principals.add(new DeviceTypePrincipal(devicetype.getText()));
		
		if (userid.getName() != null)
			this.m_principals.add(new UserPrincipal(userid.getName()));

		this.m_authsuccess = true;

		if (log.isDebugEnabled()) {
			log.debug("auth phase 1 " + "succeeding for client \""
					  + this.m_name + "\"");
		}

		return this.m_authsuccess;
	}
}
