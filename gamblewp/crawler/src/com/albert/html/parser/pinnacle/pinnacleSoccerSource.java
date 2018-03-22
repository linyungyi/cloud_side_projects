package com.albert.html.parser.pinnacle;

import java.io.InputStream;
import java.net.URL;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import net.htmlparser.jericho.TextExtractor;

import com.albert.dto.SoccerDTO;
import com.albert.html.parser.iwin.iwinSoccerSource;

public class pinnacleSoccerSource {
	static boolean debug;
	static final String WANTED="Soccer";
	static final String VISIT="Visiting";
	static final String HOME="Home";
	String sportType;
	String dateTime;
	String gameNumber;
	String league;
	String homeName;
	String visitName;
	String homeOrVisit;
	String participant;
	String moneylineVisit;
	String moneylineHome;
	String moneylineDraw;
	String totalPoint;
	String overAdjust;
	String underAdjust;
	
	
	String sourceUrlString;

	
	public pinnacleSoccerSource(String url,boolean Debug){
		sourceUrlString = url;
		debug = Debug;
	}
	
	public Vector<SoccerDTO> getData() throws Exception{
		Vector<SoccerDTO> games=new Vector<SoccerDTO>();

		try {
			URL xmlUrl = new URL(sourceUrlString);
			InputStream in = xmlUrl.openStream();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(in);
			doc.getDocumentElement().normalize();
			//System.out.println("Root element " + doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("event");
			//System.out.println("Information of all employees");

			for (int s = 0; s < nodeLst.getLength(); s++) {
				Node fstNode = nodeLst.item(s);
			    
			    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
			  
			      Element fstElmnt = (Element) fstNode;
			      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("sporttype");
			      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
			      NodeList fstNm = fstNmElmnt.getChildNodes();
			      //System.out.print("sporttype : "  + ((Node) fstNm.item(0)).getNodeValue());
			      sportType = ((Node) fstNm.item(0)).getNodeValue();

			      if(sportType.compareToIgnoreCase(WANTED)==0)
			      {
			    	  
			    	  Element fstElmnt1 = (Element) fstNode;
				      NodeList fstNmElmntLst1 = fstElmnt1.getElementsByTagName("event_datetimeGMT");
				      Element fstNmElmnt1 = (Element) fstNmElmntLst1.item(0);
				      NodeList fstNm1 = fstNmElmnt1.getChildNodes();
				      //System.out.print(" event_datetimeGMT : "  + ((Node) fstNm1.item(0)).getNodeValue());
				      dateTime = ((Node) fstNm1.item(0)).getNodeValue();
				      
				      Element fstElmnt2 = (Element) fstNode;
				      NodeList fstNmElmntLst2 = fstElmnt2.getElementsByTagName("gamenumber");
				      Element fstNmElmnt2 = (Element) fstNmElmntLst2.item(0);
				      NodeList fstNm2 = fstNmElmnt2.getChildNodes();
				      //System.out.println(" gamenumber : "  + ((Node) fstNm2.item(0)).getNodeValue());
				      gameNumber = ((Node) fstNm2.item(0)).getNodeValue();
				      
				      Element fstElmnt3 = (Element) fstNode;
				      NodeList fstNmElmntLst3 = fstElmnt3.getElementsByTagName("league");
				      Element fstNmElmnt3 = (Element) fstNmElmntLst3.item(0);
				      NodeList fstNm3 = fstNmElmnt3.getChildNodes();
				      //System.out.println(" league : "  + ((Node) fstNm3.item(0)).getNodeValue());
				      league = ((Node) fstNm3.item(0)).getNodeValue();
				      
				      Element fstElmnt4 = (Element) fstNode;
				      NodeList fstNmElmntLst4 = fstElmnt4.getElementsByTagName("participants");

				      for (int j = 0; j < fstNmElmntLst4.getLength(); j++) {
				    	  Node fstNode4 = fstNmElmntLst4.item(j);
				    	  if (fstNode4.getNodeType() == Node.ELEMENT_NODE) {
				    		  Element fstElmnt41 = (Element) fstNode4;
						      NodeList fstNmElmntLst41 = fstElmnt41.getElementsByTagName("participant");
						      for (int l = 0; l < fstNmElmntLst41.getLength(); l++) {
						    	  Node fstNode41 = fstNmElmntLst41.item(l);
						    	  if (fstNode41.getNodeType() == Node.ELEMENT_NODE) {
						    		  Element fstElmnt411 = (Element) fstNode41;
								      NodeList fstNmElmntLst411 = fstElmnt411.getElementsByTagName("visiting_home_draw");
								      Element fstNmElmnt411 = (Element) fstNmElmntLst411.item(0);
								      NodeList fstNm411 = fstNmElmnt411.getChildNodes();
								      //System.out.println(" visiting_home_draw : "  + ((Node) fstNm411.item(0)).getNodeValue());
								      homeOrVisit = ((Node) fstNm411.item(0)).getNodeValue();
								      
								      Element fstElmnt412 = (Element) fstNode41;
								      NodeList fstNmElmntLst412 = fstElmnt412.getElementsByTagName("participant_name");
								      Element fstNmElmnt412 = (Element) fstNmElmntLst412.item(0);
								      NodeList fstNm412 = fstNmElmnt412.getChildNodes();
								      //System.out.println(" participant_name : "  + ((Node) fstNm412.item(0)).getNodeValue());
								      participant=((Node) fstNm412.item(0)).getNodeValue();
								      
								      if(homeOrVisit.compareToIgnoreCase(HOME)==0)
								      {
								    	  homeName = participant;
								      }else if(homeOrVisit.compareToIgnoreCase(VISIT)==0)
								      {
								    	  visitName = participant;
								      }
						    	  }
						      }
						      
				    	  }
				      }
				      
				      Element fstElmnt5 = (Element) fstNode;
				      NodeList fstNmElmntLst5 = fstElmnt5.getElementsByTagName("periods");

				      for (int j = 0; j < fstNmElmntLst5.getLength(); j++) {
				    	  Node fstNode5 = fstNmElmntLst5.item(j);
				    	  if (fstNode5.getNodeType() == Node.ELEMENT_NODE) {
				    		  Element fstElmnt51 = (Element) fstNode5;
						      NodeList fstNmElmntLst51 = fstElmnt51.getElementsByTagName("period");
						      for (int l = 0; l < fstNmElmntLst51.getLength(); l++) {
						    	  Node fstNode51 = fstNmElmntLst51.item(l);
						    	  if (fstNode51.getNodeType() == Node.ELEMENT_NODE) {
						    		  Element fstElmnt511 = (Element) fstNode51;
								      NodeList fstNmElmntLst511 = fstElmnt511.getElementsByTagName("moneyline");
								      for(int m = 0; m < fstNmElmntLst511.getLength(); m++)
								      {
								    	  Node fstNode511 = fstNmElmntLst511.item(m);
								    	  if (fstNode511.getNodeType() == Node.ELEMENT_NODE) {
								    		  
								    		  Element fstElmnt5111 = (Element) fstNode511;
										      NodeList fstNmElmntLst51111 = fstElmnt5111.getElementsByTagName("moneyline_visiting");
										      Element fstNmElmnt51111 = (Element) fstNmElmntLst51111.item(0);
										      NodeList fstNm51111 = fstNmElmnt51111.getChildNodes();
										      //System.out.println(" participant_name : "  + ((Node) fstNm412.item(0)).getNodeValue());
										      moneylineVisit=((Node) fstNm51111.item(0)).getNodeValue();
										      
										      Element fstElmnt5112 = (Element) fstNode511;
										      NodeList fstNmElmntLst51112 = fstElmnt5112.getElementsByTagName("moneyline_home");
										      Element fstNmElmnt51112 = (Element) fstNmElmntLst51112.item(0);
										      NodeList fstNm51112 = fstNmElmnt51112.getChildNodes();
										      //System.out.println(" participant_name : "  + ((Node) fstNm412.item(0)).getNodeValue());
										      moneylineHome=((Node) fstNm51112.item(0)).getNodeValue();
										      
										      Element fstElmnt5113 = (Element) fstNode511;
										      NodeList fstNmElmntLst51113 = fstElmnt5113.getElementsByTagName("moneyline_draw");
										      Element fstNmElmnt51113 = (Element) fstNmElmntLst51113.item(0);
										      NodeList fstNm51113 = fstNmElmnt51113.getChildNodes();
										      //System.out.println(" participant_name : "  + ((Node) fstNm412.item(0)).getNodeValue());
										      moneylineDraw=((Node) fstNm51113.item(0)).getNodeValue();
								    	  }
								    	  
								      }
								      
								      Element fstElmnt512 = (Element) fstNode51;
								      NodeList fstNmElmntLst512 = fstElmnt512.getElementsByTagName("total");
								      for(int m = 0; m < fstNmElmntLst512.getLength(); m++)
								      {
								    	  Node fstNode512 = fstNmElmntLst512.item(m);
								    	  if (fstNode512.getNodeType() == Node.ELEMENT_NODE) {
								    		  
								    		  Element fstElmnt5121 = (Element) fstNode512;
										      NodeList fstNmElmntLst51121 = fstElmnt5121.getElementsByTagName("total_points");
										      Element fstNmElmnt51121 = (Element) fstNmElmntLst51121.item(0);
										      NodeList fstNm51121 = fstNmElmnt51121.getChildNodes();
										      //System.out.println(" participant_name : "  + ((Node) fstNm412.item(0)).getNodeValue());
										      totalPoint=((Node) fstNm51121.item(0)).getNodeValue();
										      
										      Element fstElmnt5122 = (Element) fstNode512;
										      NodeList fstNmElmntLst51122 = fstElmnt5122.getElementsByTagName("over_adjust");
										      Element fstNmElmnt51122 = (Element) fstNmElmntLst51122.item(0);
										      NodeList fstNm51122 = fstNmElmnt51122.getChildNodes();
										      //System.out.println(" participant_name : "  + ((Node) fstNm412.item(0)).getNodeValue());
										      overAdjust=((Node) fstNm51122.item(0)).getNodeValue();
										      
										      Element fstElmnt5123 = (Element) fstNode512;
										      NodeList fstNmElmntLst51123 = fstElmnt5123.getElementsByTagName("under_adjust");
										      Element fstNmElmnt51123 = (Element) fstNmElmntLst51123.item(0);
										      NodeList fstNm51123 = fstNmElmnt51123.getChildNodes();
										      //System.out.println(" participant_name : "  + ((Node) fstNm412.item(0)).getNodeValue());
										      underAdjust=((Node) fstNm51123.item(0)).getNodeValue();
								    	  }
								    	  
								      }
						    	  
						    	  
						    	  }
						      }
						      
				    	  }
				      }
				      
				      //System.out.println();
				      try{
					      SoccerDTO dto=new SoccerDTO();
					      dto.setGameNumber(gameNumber);
						  dto.setHomeName(homeName);
						  dto.setVisitName(visitName);
						  dto.setEventDatetime(dateTime);
						  dto.setLeague(league);
						  dto.setMoneyLineHome(Double.parseDouble(moneylineHome));
						  dto.setMoneyLineDraw(Double.parseDouble(moneylineDraw));
						  dto.setMoneyLineVisit(Double.parseDouble(moneylineVisit));
						  dto.setTotalPoint(Double.parseDouble(totalPoint));
						  dto.setOverAdjust(Double.parseDouble(overAdjust));
						  dto.setUnderAdjust(Double.parseDouble(underAdjust));
						  games.add(dto);
						  if(debug)
							  System.out.println(dto.getGameNumber()+", "+dto.getHomeName()+", "+dto.getVisitName()+", "+dto.getEventDatetime()+", "+dto.getMoneyLineHome()+", "+dto.getMoneyLineDraw()+", "+dto.getMoneyLineVisit()+", "+dto.getTotalPoint()+", "+dto.getOverAdjust()+", "+dto.getUnderAdjust());
				      }catch(Exception ex)
				      {
				    	  ex.printStackTrace();
				      }
				      
			      }
			      /*
			      Element fstElmnt1 = (Element) fstNode;
			      NodeList fstNmElmntLst1 = fstElmnt1.getElementsByTagName("event_datetimeGMT");
			      Element fstNmElmnt1 = (Element) fstNmElmntLst1.item(0);
			      NodeList fstNm1 = fstNmElmnt1.getChildNodes();
			      System.out.print(" event_datetimeGMT : "  + ((Node) fstNm1.item(0)).getNodeValue());
			      
			      Element fstElmnt2 = (Element) fstNode;
			      NodeList fstNmElmntLst2 = fstElmnt2.getElementsByTagName("gamenumber");
			      Element fstNmElmnt2 = (Element) fstNmElmntLst2.item(0);
			      NodeList fstNm2 = fstNmElmnt2.getChildNodes();
			      System.out.println(" gamenumber : "  + ((Node) fstNm2.item(0)).getNodeValue());
			      
			      Element fstElmnt3 = (Element) fstNode;
			      NodeList fstNmElmntLst3 = fstElmnt3.getElementsByTagName("league");
			      Element fstNmElmnt3 = (Element) fstNmElmntLst3.item(0);
			      NodeList fstNm3 = fstNmElmnt3.getChildNodes();
			      System.out.println(" league : "  + ((Node) fstNm3.item(0)).getNodeValue());	
			      */
			      /*
			      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("lastname");
			      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
			      NodeList lstNm = lstNmElmnt.getChildNodes();
			      System.out.println("Last Name : " + ((Node) lstNm.item(0)).getNodeValue());
			      */
			    }

			 }
		} catch (Exception e) {
			  e.printStackTrace();
		}

		
		return games;
	}
	
	public static void main(String[] args) throws Exception {
		pinnacleSoccerSource s = new pinnacleSoccerSource("http://xml.pinnaclesports.com/pinnacleFeed.aspx",true);
		s.getData();
	}
}
