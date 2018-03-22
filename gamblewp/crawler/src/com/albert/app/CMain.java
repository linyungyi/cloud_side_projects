package com.albert.app;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;

public class CMain {

	static Timer fbTimer;
	static int fbPeriod;
	static boolean fbChecked;
	static boolean fbDebug;
	
	static String iFBURL;
	static String pFBURL;

	public CMain() {
		InitConfig();
		
		if(fbChecked)
		{
			fbTimer = new Timer();
			fbTimer.schedule(new FBTask(iFBURL,pFBURL,fbDebug), 1000, fbPeriod*1000);
		}
		
		
		//System.out.println("現在時間：" + new Date());
	}

	private synchronized void InitConfig() {
		try {
			// getConfig("config.xml");
			// System.out.println(port+" "+sAccount+" "+ sPassWord +" "+ sUrl);
			InputStream ips = new FileInputStream("app.properties");
			Properties p = new Properties();
			p.load(ips);
			loadProperties(p);
		} catch (Exception ex) {
			System.out.println("Error on InitConfig : " + ex.getMessage());
			//logging.writeLog("_GW", "Error on InitConfig : " + ex.getMessage());
		}
	}

	private synchronized void loadProperties(Properties ps) {
		Properties p = ps;
		try {

			if (p.getProperty("FB.TIMER") != null)
				fbPeriod = Integer.parseInt(p.getProperty("FB.TIMER"));
			
			if(p.getProperty("FB")!=null)
				fbChecked=(p.getProperty("FB").trim().compareToIgnoreCase("yes")==0?true:false);
			
			if(p.getProperty("FB.IWIN.URL")!=null)
				iFBURL=p.getProperty("FB.IWIN.URL").trim();
			
			if(p.getProperty("FB.PINNACLE.URL")!=null)
				pFBURL=p.getProperty("FB.PINNACLE.URL").trim();
			
			if(p.getProperty("FB.DEBUG")!=null)
				fbDebug=(p.getProperty("FB.DEBUG").trim().compareToIgnoreCase("yes")==0?true:false);

			/*
			 * if(p.getProperty("QUERYALARM")!=null)
			 * QueryAlarm=Integer.parseInt(p.getProperty("QUERYALARM"));
			 * 
			 * if(p.getProperty("STDDEV0")!=null)
			 * STDDEV0=(p.getProperty("STDDEV0"
			 * ).compareToIgnoreCase("true")==0?true:false);
			 * 
			 * if(p.getProperty("LOG")!=null)
			 * LOG=(p.getProperty("LOG").compareToIgnoreCase
			 * ("true")==0?true:false);
			 * 
			 * if(p.getProperty("IPTV")!=null)
			 * IPTV=(p.getProperty("IPTV").compareToIgnoreCase
			 * ("true")==0?true:false);
			 */

		} catch (Exception e) {
			System.out.println("ERROR_ON loadProperties : " + e.getMessage());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CMain cmain = new CMain();


	}

}
