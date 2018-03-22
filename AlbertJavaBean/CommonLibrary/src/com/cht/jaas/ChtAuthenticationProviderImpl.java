package com.cht.jaas;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.security.auth.login.AppConfigurationEntry;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import weblogic.management.security.ProviderMBean;
import weblogic.security.provider.PrincipalValidatorImpl;
import weblogic.security.spi.AuthenticationProvider;
import weblogic.security.spi.IdentityAsserter;
import weblogic.security.spi.PrincipalValidator;
import weblogic.security.spi.SecurityServices;

import com.macromedia.security.facade.Common;
import com.macromedia.util.log4j.EosLog4jLogger;

public class ChtAuthenticationProviderImpl implements AuthenticationProvider {

	private static final String LOG4J_CONFIGURATION = "aaa.log4j.xml";
    private static final String RESOURCE_BUNDLE	= "com.macromedia.flashcast.FlashCastResources";

	protected static Logger 	log         = Logger.getLogger(ChtAuthenticationProviderImpl.class);

	private   static Properties prop        = new Properties();

	private   static boolean 	initialized = false;

    private static File s_confDir;
    private static File s_logDir;

	private String description = null;

	public static void init () {
		if (initialized) {
			return;
		}

        String fcHome = System.getProperty(Common.FLASHCAST_HOME);
        if (fcHome == null) {
            System.err.println("Error finding System property " + Common.FLASHCAST_HOME);
        }

        s_confDir = new File(fcHome + File.separator + Common.DIRECTORY_CONF);
        s_logDir = new File(fcHome + File.separator + Common.DIRECTORY_LOG);

        try {
            s_logDir.mkdirs();
        }
        catch (Throwable t) {
            System.err.println("cannot create log directory  due to exception \"" + t.getMessage() + "\"");
        }

        /* Read in the properties from the file system. */
		loadProperties(Common.AAA_PROP_CUSTOM_FILE);

		try	{
            String filename = s_confDir.getCanonicalPath() + File.separator + LOG4J_CONFIGURATION;

            System.setProperty(Common.CONFIG_DIRECTORY_LOG, s_logDir.getCanonicalPath());

			DOMConfigurator.configure(filename);
		} catch (Throwable t) {
			System.err.println("exception initializing log4j");
			t.printStackTrace();
		}

		Locale locale = Locale.getDefault();
		ResourceBundle rb = null;

        try
        {
            rb = ResourceBundle.getBundle(RESOURCE_BUNDLE, locale, EosLog4jLogger.class.getClassLoader());
            Logger baselogger = Logger.getRootLogger();
            baselogger.setResourceBundle(rb);
        }
        catch (MissingResourceException mre1)
        {
            System.err.println("Resource bundle " + RESOURCE_BUNDLE + " not found with locale " + locale);
        }

        initialized = true;
	}

	public void initialize(ProviderMBean mbean, SecurityServices services) {
		init();

		description = mbean.getDescription() + "\n" + mbean.getVersion();
	}

	public String getDescription() {
		return description;
	}

	public void shutdown() {
	}

	public AppConfigurationEntry getLoginModuleConfiguration() {
		HashMap options = new HashMap();

		// specify the real JAAS module to call.
		options.put(Common.REAL_LOGIN_CLASS, ChtUserLoginModule.class.getName());
		options.put(Common.LOGIN_NAME, "CHT UserLoginModule");

		return new AppConfigurationEntry(ChtUserLoginModule.class.getName(),
										 AppConfigurationEntry.LoginModuleControlFlag.SUFFICIENT,
				                         options);
	}

	public AppConfigurationEntry getAssertionModuleConfiguration() {
		return null;
	}

	public PrincipalValidator getPrincipalValidator() {
		return new PrincipalValidatorImpl();
	}

	public IdentityAsserter getIdentityAsserter() {
		return null;
	}

	private static void loadProperties(String filename) {
		String      propfile;
		InputStream is;

		try {
            propfile = s_confDir.getCanonicalPath() + File.separator + filename;
            if ((is = new FileInputStream(propfile)) != null) {
				prop.load(is);
			} else {
				log.l7dlog(Level.ERROR, "ERROR_AUTH_LOAD_CONFIGURATION_FILE", new Object[] {propfile}, null);
			}
		} catch (Exception e) {
			log.l7dlog(Level.ERROR, "ERROR_AUTH_LOAD_CONFIGURATION_FILE", new Object[] {filename}, e);
		}
	}

	protected String getProperty(String key, String defaultValue) {
		return prop.getProperty(key, defaultValue);
	}
}
