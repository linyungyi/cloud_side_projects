package com.albert.html.parser.iwin;

import java.net.URL;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.MasonTagTypes;
import net.htmlparser.jericho.MicrosoftTagTypes;
import net.htmlparser.jericho.PHPTagTypes;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.StartTag;
import net.htmlparser.jericho.TextExtractor;

import com.albert.dto.SoccerDTO;


public class iwinSoccerSource {

	static final String bigWord0="單場";
	static final String bigWord1="主客和 大小 正確得分 主隊勝 和局 客隊勝 總球數 大 小";
	static boolean debug;
	
	private static Pattern pattern = Pattern
	.compile("[0-9]{4}");

	
	String sourceUrlString;
	String extractor;
	String restString;
	
	int index[];
	int total;
	
	public iwinSoccerSource(String url,boolean Debug){
		sourceUrlString = url;
		index= new int[1000];
		total=0;
		debug=Debug;
	}
	
	public Vector<SoccerDTO> getData() throws Exception{
		Vector<SoccerDTO> games=new Vector<SoccerDTO>();

		MicrosoftTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this example otherwise they override processing instructions
		MasonTagTypes.register();
		Source source=new Source(new URL(sourceUrlString));
		source.fullSequentialParse();
		
		//System.out.println("\nAll text from file (exluding content inside SCRIPT and STYLE elements):\n");
		//System.out.println(source.getTextExtractor().setIncludeAttributes(true).toString());

		//System.out.println("\nSame again but this time extend the TextExtractor class to also exclude text from P elements and any elements with class=\"control\":\n");
		TextExtractor textExtractor=new TextExtractor(source) {
			public boolean excludeElement(StartTag startTag) {
				return startTag.getName()==HTMLElementName.P || "control".equalsIgnoreCase(startTag.getAttributeValue("class"));
			}
		};
		//System.out.println(textExtractor.setIncludeAttributes(true).toString());
		extractor = textExtractor.setIncludeAttributes(true).toString();
		//System.out.println(extractor);
		
		String tmpString;
		/*restString = extractor;
		while(true)
		{
			tmpString=stringBetweenBigWord0(restString.trim());
			if(tmpString.length()==0)
				break;
			System.out.println(tmpString.trim());
			Matcher matcher = pattern.matcher(tmpString.trim());
			while(matcher.find())
			{
				String sn=matcher.group(0);
				System.out.println(sn+" "+matcher.start());
				index[total++]=matcher.start();
			}
			
		}*/
		//tmpString=extractor;
		Matcher matcher = pattern.matcher(extractor.trim());
		while(matcher.find())
		{
			//String sn=matcher.group(0);
			//System.out.println(sn+" "+matcher.start());
			index[total++]=matcher.start();
		}
		
		for(int i=0;i<total;i++)
		{
			SoccerDTO dto=new SoccerDTO();
			if(i+1<total)
			{
				tmpString = extractor.substring(index[i], index[i+1]);				
			}else
			{//the last one
				tmpString = extractor.substring(index[i]);
			}
			if(debug)
				System.out.println(tmpString);
			try{
				String sa[]=tmpString.split("\\s+");
				int a,b,c;
				String s1="",s2="";
				int number = sa.length;

				a=1;
				while(true)
				{
					if(sa[a].compareToIgnoreCase("對")==0)
						break;
					a++;
				}
				
				for(int j=1;j<a;j++)
				{
					s1=s1+sa[j];
					//System.out.println(a+">>>"+sa[j]);
				}
				
				b=3;
				while(true)
				{
					if(sa[b].indexOf('/')!=-1)
						break;
					b++;
				}
				for(int k=3;k<b;k++)
				{
					s2=s2+sa[k];
				}
				c=14;
				while(true)
				{
					if(sa[c++].compareToIgnoreCase("小")==0)
						break;
				}
				
				
				/*
				dto.setGameNumber(sa[0]);
				dto.setHomeName(sa[1]);
				dto.setVisitName(sa[3]);
				dto.setEventDatetime(sa[4]+" "+sa[5]);

				dto.setMoneyLineHome(Double.parseDouble(sa[15]));
				dto.setMoneyLineDraw(Double.parseDouble(sa[16]));
				dto.setMoneyLineVisit(Double.parseDouble(sa[17]));
				dto.setTotalPoint(Double.parseDouble(sa[18]));
				dto.setOverAdjust(Double.parseDouble(sa[19]));
				dto.setUnderAdjust(Double.parseDouble(sa[20]));
				*/
				dto.setGameNumber(sa[0]);
				
				dto.setHomeName(s1);
				dto.setVisitName(s2);
				dto.setEventDatetime(sa[b]+" "+sa[b+1]);
				
				if(number > 18)
				{
					dto.setMoneyLineHome(Double.parseDouble(sa[c]));
					dto.setMoneyLineDraw(Double.parseDouble(sa[c+1]));
					dto.setMoneyLineVisit(Double.parseDouble(sa[c+2]));
					dto.setTotalPoint(Double.parseDouble(sa[c+3]));
					dto.setOverAdjust(Double.parseDouble(sa[c+4]));
					dto.setUnderAdjust(Double.parseDouble(sa[c+5]));
				}else
				{
					switch(pointOrLine(tmpString))
					{
					case 0:
						dto.setMoneyLineHome(Double.parseDouble(sa[c]));
						dto.setMoneyLineDraw(Double.parseDouble(sa[c+1]));
						dto.setMoneyLineVisit(Double.parseDouble(sa[c+2]));
						break;
					case 1:
						dto.setTotalPoint(Double.parseDouble(sa[c]));
						dto.setOverAdjust(Double.parseDouble(sa[c+1]));
						dto.setUnderAdjust(Double.parseDouble(sa[c+2]));
						break;
					}
				}
				
				games.add(dto);
			}catch(Exception e)
			{
				System.out.println(e.getMessage()+" : "+tmpString);
			}
			
			if(i==0 && debug)
			{
				System.out.println(">>>>"+dto.getGameNumber()+" "+dto.getHomeName()+" "+dto.getVisitName()+" "+dto.getEventDatetime()+" "+dto.getMoneyLineHome()+" "+dto.getMoneyLineDraw()+" "+dto.getMoneyLineVisit()+" "+dto.getTotalPoint()+" "+dto.getOverAdjust()+" "+dto.getUnderAdjust());
			}
			
		}
		
		return games;
	}
	
	String stringBetweenBigWord0(String s){
		String ret="";
		String aString;
		int a,b;
		a = s.indexOf(bigWord0);
		if(a != -1)
		{
			aString = s.substring(a+bigWord0.length());
			//System.out.println(aString);
			b = aString.indexOf(bigWord0);
			if(b!=-1)
			{
				ret = aString.substring(0, b);
				restString = aString.substring(b);
			}else
			{
				ret = aString;
				restString = "";
			}
		}

		return ret;
	}
	
	int pointOrLine(String str){
		int ret=0;//line
		int little;
		int space=0;
		String tmp;
		
		little=str.indexOf("小");
		tmp=str.substring(little+1);
		for(int i=0;i<tmp.length();i++)
		{
			space = i;
			if(tmp.charAt(i)!=' ')
				break;
		}
		
		if(space>2) //point
			ret=1;
		
		return ret;
	}
	
	public static void main(String[] args) throws Exception {
		iwinSoccerSource s = new iwinSoccerSource("https://bet.i-win.com.tw/SBP2Web/betting.do?method=getBettingList&sportCode=FB&poolCode=ALL",true);
		s.getData();
	}
}
