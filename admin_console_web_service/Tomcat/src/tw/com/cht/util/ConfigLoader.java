package tw.com.cht.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;


public class ConfigLoader {
    private static Properties ConfigDefinition;
    private static final String Config_DEFINITION = "config.properties";    
    private static String real_path;
    //private static String url;
    
    static long lm=0;
    
	public ConfigLoader(String dir) throws IOException{
		real_path=dir;
		//url=ConfigLoader.class.getResource(Config_DEFINITION).getFile();
		//System.out.println(url);
		
        if (ConfigDefinition == null) {
        	load();
        }
        
        Timer timer = new Timer(); //
        timer.scheduleAtFixedRate(new ConfigTrigger(), 0, 5000);//
	}
	
	public synchronized String getProperty(String name){
		String ret=null;
        try
        {
          ret=ConfigDefinition.getProperty(name);          
        }catch(Exception e)
        {
          System.out.println("ERROR_ON loadProperties : "+e.getMessage());
        }
		return ret;
	}
	
	private synchronized void load() throws IOException{
		ConfigDefinition = new Properties();
        ConfigDefinition.load(new FileInputStream(real_path));
        lm=new File(real_path).lastModified();        
        //System.out.println("load:"+getProperty("DOCUMENT.SourceUri"));
	}	

	private synchronized boolean CompareFileDate(){
	   long tmplm=new File(real_path).lastModified();
	   //System.out.println(tmplm);
	   //System.out.println("load:"+getProperty("DOCUMENT.SourceUri"));
	   if(lm!=tmplm)
	   {
	     lm=tmplm;
	     return false;
	   }else
	     return true;
	}
	
	private class ConfigTrigger extends TimerTask {
	   public ConfigTrigger(){
	   }
	   public void run() {

	         if(!CompareFileDate())
	         {
				 try{
					 load();
					 System.out.println(Config_DEFINITION + " was modified!");
				 }catch(Exception ex)
				 {
					 System.out.println("ConfigLoader.ConfigTrigger.run: "+ex.getMessage());
				 }
	         }
	   }
	}	
}
