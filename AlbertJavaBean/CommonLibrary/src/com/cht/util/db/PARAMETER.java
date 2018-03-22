/**
 * 
 */
package com.cht.util.db;

/**
 * @author alberltin
 *
 */
public class PARAMETER {
	  public static final int NUMBER=1;
	  public static final int VARCHAR2=2;
	  public static final int LONG=3;
	  public static final int BOOLEAN=4;

	  private String sContent="";
	  private int iContent;
	  private long lContent;
	  private boolean bContent;
	  private int Type=0;

	  public PARAMETER(String c) {
	    sContent=c;
	    Type=VARCHAR2;
	  }

	  public PARAMETER(int c) {
	    iContent=c;
	    Type=NUMBER;
	  }
	  
	  public PARAMETER(long c){
		  lContent=c;
		  Type=LONG;
	  }
	  
	  public PARAMETER(boolean c){
		  bContent=c;
		  Type=BOOLEAN;
	  }	  

	  public int getType(){
	    return Type;
	  }

	  public int getNumber(){
	    return iContent;
	  }

	  public String getVARCHAR(){
	    return sContent;
	  }
	  
	  public long getLONG(){
		  return lContent;
	  }
	  
	  public boolean getBOOLEAN(){
		  return bContent;
	  }	  
}
