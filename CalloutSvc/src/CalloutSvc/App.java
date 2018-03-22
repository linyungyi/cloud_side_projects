package CalloutSvc;

import java.util.Random;

public class App {

  private int pid=0;
  private int port=10000;
  public _GW daemon1;
  public static int baseValue = 10000;

  public App() {
    Random randomGenerator = new Random();
    port = randomGenerator.nextInt(baseValue)+baseValue;
    CreateDaemon();
  }

  public App(int PORT,int PID){
    port = PORT;
    pid = PID;
    CreateDaemon();
  }

  protected void CreateDaemon(){
    //System.out.print("create daemom on port=" + port + " pid=" + pid);
    daemon1=new _GW(port,pid);
  }
  public static void main(String[] arguments) {
    new App();
  }

}
