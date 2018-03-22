package tw.com.cht.util;

import java.util.*;
import java.io.*;

public class TaskLoader {
	
    private static Properties taskDefinition;
    private static final String TASK_DEFINITION = "TaskDefinition.properties";    
    private static String real_path;
    
    static long lm=0;
    
	public TaskLoader(String dir) throws IOException{
		real_path=dir;
		
        if (taskDefinition == null) {
        	load();
        }
        
        Timer timer = new Timer(); //
        timer.scheduleAtFixedRate(new Trigger(), 0, 5000);//
	}
	
	public String getProperty(String taskId){
		return taskDefinition.getProperty(taskId);
	}
	
	public Object getInstance(String taskId){
		String classname = taskDefinition.getProperty(taskId);
		Object Obj=null;
		if(classname!=null)
		{
			try{
				Obj=Class.forName(classname).newInstance();
	        } catch (ClassNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IllegalAccessException ex) {
	            ex.printStackTrace();
	        } catch (InstantiationException ex) {
	            ex.printStackTrace();
	        }
		}else
			System.out.println("TaskLoader.getInstance : there is no \""+taskId+"\" in the "+TASK_DEFINITION);
        return Obj;
	}
	
	private synchronized void load() throws IOException{
		taskDefinition = new Properties();
        taskDefinition.load(new FileInputStream(real_path));
        lm=new File(real_path).lastModified();
	}	

	private synchronized boolean CompareFileDate(){
	   long tmplm=new File(real_path).lastModified();
	   if(lm!=tmplm)
	   {
	     lm=tmplm;
	     return false;
	   }else
	     return true;
	}
	
	private class Trigger extends TimerTask {
	   public Trigger(){
	   }
	   public void run() {
	         if(!CompareFileDate())
	         {
				 try{
					 load();
					 System.out.println(TASK_DEFINITION + " was modified!");
				 }catch(Exception ex)
				 {
					 System.out.println("TaskLoader.Trigger.run: "+ex.getMessage());
				 }
	         }
	   }
	}	
/*    public static synchronized String getTaskList(String taskId) throws IOException {
        
        if (taskDefinition == null) {
            taskDefinition = new Properties();
            taskDefinition.load(TaskLoader.class.getResourceAsStream(TASK_DEFINITION));
        }
        
        String definition = taskDefinition.getProperty(taskId);
        StringTokenizer tokens = new StringTokenizer(definition, "|");
        while (tokens.hasMoreTokens()) {
            try {
                String classname = tokens.nextToken().trim();
                tasks.add(Class.forName(classname).newInstance());
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
        }
        
        return tasks;
    }
    */

}
