package CalloutSvc;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.sql.*;

public class _GW {

  static doCommand conn_c;

  public _GW(int port,int pid) {

    //inital user list here
    Vector list =new Vector();
    Hashtable vm = new Hashtable();

    conn_c= new doCommand(port,pid,list,vm);
    Thread t = new Thread(conn_c);
    t.start();
  }
  public static void main(String[] arguments) {
    new _GW(App.baseValue,0);
  }
}

class doCommand implements Runnable{
  int port_base=10000;
  int port_range=10000;
  int port = App.baseValue;
  int pid = 0;
  ServerSocket server;
  static LOG logging;
  static Vector UserTable;
  static Hashtable ClientTable;

  static boolean STDDEV0=true;
  static boolean LOG=true;

  long nClientHandler=0;

  //Socket socket;
  final String VERSION="CalloutSvc 1.0";
  String logName ;
  Timer timer=null;
  int startTimer = 5000;
  int minPeriod = 50;
  int maxPeriod = 30000;

  doCommand() {
    logName = "_CalloutSvc_"+pid;
    logging = new LOG();
    InitConfig();
    UserTable =  new Vector();
    ClientTable=new Hashtable();
    tiggerStart();
  }

  doCommand(int p,int id,Vector l,Hashtable v) {
    logging = new LOG();
    port = p;
    pid = id;

    logName = "_CalloutSvc_"+pid;
    InitConfig();
    //UserTable =  new Vector();
    //ClientTable=new Hashtable();
    UserTable = l;
    ClientTable = v;
    tiggerStart();
  }

  public void reloadConfig() {
    InitConfig();
  }
  public synchronized void addClientHandler(){
    nClientHandler++;
  }
  public synchronized void subtractClientHandler(){
    nClientHandler--;
  }
  private synchronized void InitConfig() {
    try {
      //getConfig("config.xml");
      //System.out.println(port+" "+sAccount+" "+ sPassWord +" "+ sUrl);
      InputStream ips = new FileInputStream("_svc.properties");
      Properties p = new Properties();
      p.load(ips);
      loadProperties(p);
    }
    catch (Exception ex) {
      System.out.println("Error on InitConfig (pid="+pid+" port="+port+"): " + ex.getMessage());
      logging.writeLog(logName, "Error on InitConfig (pid="+pid+" port="+port+"): " + ex.getMessage());
    }
  }

  private synchronized void tiggerStart(){
    timer = new Timer(); //
    timer.scheduleAtFixedRate(new Trigger(), startTimer, minPeriod);//
  }

  public void run() {
    try {
      String DBScript="";
      System.out.println("VERSION : " + VERSION);
      logging.writeLog(logName, "VERSION : " + VERSION);
      System.out.println("Callout Service on port : " + port + " pid="+pid);
      logging.writeLog(logName, "Callout Service on port : " + port + " pid="+pid);
      server = new ServerSocket(port);
      System.out.println("Daemon start Listening.");
      logging.writeLog(logName, "Daemon start Listening.");

      while (true) {
        final Socket socket = server.accept();
        Thread handler = new Thread() {
          public void run() {
            addClientHandler();
            try {
              //socket.setSoTimeout(30000);
              BufferedReader br = new BufferedReader(new InputStreamReader(
                  socket.getInputStream()));
              PrintStream ps = new PrintStream(socket.getOutputStream());
              //int i=1;

              while(true){
                String xString = br.readLine().trim();
                //String xString = br.readLine();
                String resString = "";
                //if (xString.indexOf("Instrumental") != -1) {
                if (STDDEV0) {
                  System.out.println(java.util.Calendar.getInstance().getTime() +
                                     " recieved from client(" +
                                     socket.getInetAddress() + ") handlers="+nClientHandler+": " + xString);
                }
                if (LOG) {
                  logging.writeLog(logName,
                                   "recieved from client(" +
                                   socket.getInetAddress() +
                                   ") handlers="+nClientHandler+": " + xString);
                }
                //if(i==9)
                //Thread.sleep(5000);
                long StartTime = System.currentTimeMillis();
                resString = _ClientHandler(xString);
                long ProcessTime = System.currentTimeMillis() - StartTime;
                //i++;
                //resString = xString;
                if (STDDEV0) {
                  System.out.println(java.util.Calendar.getInstance().getTime() +
                                     " send to client : " + resString + " (" + ProcessTime + " ms.)");
                }
                if (LOG) {
                  logging.writeLog(logName, "send to client : " + resString + " (" + ProcessTime + " ms.)");
                }

                ps.println(resString);
                ps.flush();
              }

            }
            catch (java.net.SocketTimeoutException se) {
              if(STDDEV0)
              {
                System.out.println("Error on doCommand.run.1 (client): " +
                                   se.getMessage());
              }
              logging.writeLog(logName,
                               "Error on doCommand.run.1 (client): " + se.getMessage());
              logging.writeLog(logName,
                               "Error on doCommand.run.1 (client): " +
                               se.getMessage());
            }
            catch (Exception e) {
              if(STDDEV0)
              {
                System.out.println("Error on doCommand.run.2 (client): " +
                                   e.getMessage());
              }
              logging.writeLog(logName,
                               "Error on doCommand.run.2 (client): " + e.getMessage());
              logging.writeLog(logName,
                               "Error on doCommand.run.2 (client): " + e.getMessage());
            }
            try {
              socket.close();
              System.out.println("Server socket close.");
            }
            catch (Exception ex) {
              if(STDDEV0)
              {
                System.out.println("Error on doCommand.run : " + ex.getMessage());
              }
              logging.writeLog(logName,
                               "Error on doCommand.run : " + ex.getMessage());
              logging.writeLog(logName,
                               "Error on doCommand.run : " + ex.getMessage());
            }
            subtractClientHandler();
            if(STDDEV0)
            {
              System.out.println("current handlers="+nClientHandler);
            }
            logging.writeLog(logName,
                             "current handlers="+nClientHandler);
          }
        };
        handler.start();
      }
    }
    catch (IOException iox) {
      if(STDDEV0)
      {
        System.out.println("Error on doCommand.run.1 (server): " + iox.getMessage());
      }
      logging.writeLog(logName,
                       "Error on doCommand.run.1 (server): " + iox.getMessage());
      logging.writeLog(logName,
                       "Error on doCommand.run.1 (server): " + iox.getMessage());
    }
    catch (Exception e) {
      if(STDDEV0)
      {
        System.out.println("Error on doCommand.run.2 (server): " + e.getMessage());
      }
      logging.writeLog(logName,
                       "Error on doCommand.run.2 (server): " + e.getMessage());
      logging.writeLog(logName,
                       "Error on doCommand.run.2 (server): " + e.getMessage());
    }
  }

  //private String _DB(String s){
  private String _ClientHandler(String MsgId){
    String result = "";

    synchronized(UserTable)
    {
      //do remove here
    }

    { //reset timer here if acm fail
      timer.cancel();
      timer = null;
      timer = new Timer();
      timer.schedule(new Trigger(),minPeriod+1000,minPeriod+1000);
    }

    return result + MsgId;
  }

  private synchronized void loadProperties(Properties ps) {
          Properties p=ps;
          try
          {
//
            if(p.getProperty("PORTBASE")!=null)
              port_base=Integer.parseInt(p.getProperty("PORTBASE"));

            if(p.getProperty("PORTRANGE")!=null)
              port_range=Integer.parseInt(p.getProperty("PORTRANGE"));

            if(p.getProperty("LOG")!=null)
              LOG=(p.getProperty("LOG").compareToIgnoreCase("true")==0?true:false);

          }catch(Exception e)
          {
            System.out.println("ERROR_ON loadProperties (pid="+pid+" port="+port+"): "+e.getMessage());
          }
        }
  //}

  private class Trigger extends TimerTask {
    private int s=0;
    public Trigger(){

    }
    public void run() {
        //do socket client here
        s=java.util.Calendar.getInstance().get(Calendar.SECOND);
        synchronized(ClientTable){

          if (STDDEV0) {
            System.out.println(s);
          }
         if (LOG) {
           logging.writeLog(logName," " + s);
         }

        }
    }
  }


}
