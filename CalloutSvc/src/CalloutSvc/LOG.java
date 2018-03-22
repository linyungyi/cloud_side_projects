package CalloutSvc;

import java.util.*;
import java.io.*;

public class LOG {
  public LOG() {
    //writeLog("log","fdsafdsa");
  }
  public synchronized void writeLog(String folder,String logmsg){
    int yy,mm,dd,hh,ms,ss,mms;
    String fileN;
    String daytime;
    yy=java.util.Calendar.getInstance().get(Calendar.YEAR);
    mm=java.util.Calendar.getInstance().get(Calendar.MONTH)+1;
    dd=java.util.Calendar.getInstance().get(Calendar.DATE);
    fileN=new Integer(yy).toString();
    fileN+=(mm<10?"0"+new Integer(mm).toString():new Integer(mm).toString());
    fileN+=(dd<10?"0"+new Integer(dd).toString():new Integer(dd).toString());
    fileN+=".txt";

    try{

      File fo=new File(folder);
      if(!fo.exists())
        fo.mkdirs();
      FileWriter fr=new FileWriter(folder+"/"+fileN,true);
      hh=java.util.Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
      ms=java.util.Calendar.getInstance().get(Calendar.MINUTE);
      ss=java.util.Calendar.getInstance().get(Calendar.SECOND);
      mms=java.util.Calendar.getInstance().get(Calendar.MILLISECOND);
      daytime=(hh<10?"0"+new Integer(hh).toString()+":":new Integer(hh).toString()+":");
      daytime+=(ms<10?"0"+new Integer(ms).toString()+":":new Integer(ms).toString()+":");
      daytime+=(ss<10?"0"+new Integer(ss).toString():new Integer(ss).toString());
      daytime+=".";
      daytime+=(mms<10?"00"+new Integer(mms).toString():(mms>=100?new Integer(mms).toString():"0"+new Integer(mms).toString()));
      fr.write(daytime+"\t"+logmsg+"\r\n");
      fr.close();
    }catch(Exception e){
      System.out.println("error on LOG : "+e.getMessage());
    }

  }
  public static void main(String[] arguments) {
    new LOG();
  }

}
