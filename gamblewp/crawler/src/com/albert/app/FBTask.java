package com.albert.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.TimerTask;
import java.util.Vector;

import com.albert.dto.*;
import com.albert.html.parser.iwin.iwinSoccerSource;
import com.albert.html.parser.pinnacle.pinnacleSoccerSource;
import com.albert.jexcel.jexecelReport;

public class FBTask extends TimerTask{
	
	Hashtable<String,String> mapping;
	
	static String iURL;
	static String pURL;
	static boolean debug;
	
	public FBTask(String iwinUrl,String pinacleUrl,boolean Debug){
		mapping = new Hashtable<String,String>();
		iURL=iwinUrl;
		pURL=pinacleUrl;
		debug=Debug;
		
		
		try {
		    BufferedReader in = new BufferedReader(new FileReader("name.map"));
		    String str;

		    while ((str = in.readLine()) != null) {
		    	String a[]=str.split(";");
		    	int number = a.length;
		    	if(number > 0)
		    	{
		    		mapping.put(a[0], a[1]);
		    	}
		    }
		    in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	Vector<SoccerCombine> merge(Vector<SoccerDTO> chs,Vector<SoccerDTO> ens){
		Vector<SoccerCombine> result=new Vector<SoccerCombine>();
		int sizeChs = chs.size();
		Enumeration<SoccerDTO> eChs=chs.elements();
		SoccerDTO dtoA=null,dtoB=null;
		
		while(eChs.hasMoreElements())
		{
			dtoA = (SoccerDTO)eChs.nextElement();
			String mapName = (String)mapping.get(dtoA.getHomeName());
			if(mapName!=null)
			{
				String mapNameList[] = mapName.split(",");
				int sizeMapName = 0;
				
				if(mapNameList !=null)
				{
					sizeMapName = mapNameList.length;
				}
				
				if(sizeMapName==0)
				{
					Enumeration<SoccerDTO> eEns=ens.elements();
					while(eEns.hasMoreElements())
					{
						SoccerDTO dtoTemp = (SoccerDTO)eEns.nextElement();
						if(mapName.compareToIgnoreCase(dtoTemp.getHomeName())==0)
						{
							//iwin:10/03 02:44
							//pinacle:2010-10-03 12:59
							
							dtoB=dtoTemp;
							ens.remove(dtoTemp);
							break;
						}
					}
				}else
				{
					for(int i=0;i<sizeMapName;i++){
						Enumeration<SoccerDTO> eEns=ens.elements();
						while(eEns.hasMoreElements())
						{
							SoccerDTO dtoTemp = (SoccerDTO)eEns.nextElement();
							if(mapNameList[i].compareToIgnoreCase(dtoTemp.getHomeName())==0)
							{
								dtoB=dtoTemp;
								ens.remove(dtoTemp);
								break;
							}
						}
					}
				}
			}
			
			SoccerCombine sc=new SoccerCombine(dtoA,dtoB);
			result.add(sc);

		}
		
		dtoA=null;
		Enumeration<SoccerDTO> eEns=ens.elements();
		while(eEns.hasMoreElements())
		{
			dtoB = (SoccerDTO)eEns.nextElement();
			SoccerCombine sc=new SoccerCombine(dtoA,dtoB);
			result.add(sc);
		}
		
		return result;
	}
	
    public void run() {
        System.out.println("足球任務時間：" + new Date());
        try{
        	System.out.print(" (1)getting data from FB.IWIN.URL.");
            iwinSoccerSource iwin = new iwinSoccerSource(iURL,debug);
            Vector<SoccerDTO> ivt=iwin.getData();
            System.out.println(" finished!");
            System.out.print(" (2)getting data from FB.PINNACLE.URL.");
            pinnacleSoccerSource pinnacle = new pinnacleSoccerSource(pURL,debug);
            Vector<SoccerDTO> pvt=pinnacle.getData();
            System.out.println(" finished!");
            System.out.print(" (3)matching!");
            System.out.println(" finished!");
            Vector<SoccerCombine> sc = merge(ivt,pvt);
            System.out.println(" (4)writing into excel!");
    		jexecelReport jr = new jexecelReport("SRS.xls");
    		jr.setSc(sc);
    		jr.create(sc);
            
        }catch(Exception e)
        {
        	e.printStackTrace();
        }

        
    }
    
    
}