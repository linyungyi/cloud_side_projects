package com.albert.html.parser.sample;

import net.htmlparser.jericho.*;
import java.util.*;
import java.io.*;
import java.net.*;
 
public class HtmlParser
{
    public static void main(String[] args) throws Exception
    {
        String sourceUrlString="https://bet.i-win.com.tw/SBP2Web/betting.do?method=getBettingList&sportCode=BS&poolCode=ALL";
        
 //       if(sourceUrlString.indexOf(':') == -1)
 //           sourceUrlString ="file:"+sourceUrlString;
            
        Source source=new Source(new URL(sourceUrlString));
        List Elements_TABLE=source.getAllElements(HTMLElementName.TABLE);
        Elements_TABLE.remove(0);
        Iterator it_TABLE = Elements_TABLE.iterator();
        while(it_TABLE.hasNext())
        {
            Element Element_TABLE = (Element)it_TABLE.next();
            Segment getContent_TABLE = (Segment)Element_TABLE.getContent();
            List Elements_TR = getContent_TABLE.getAllElements(HTMLElementName.TR);
            Elements_TR.remove(0);
            Iterator it_TR = Elements_TR.iterator();
            while(it_TR.hasNext())
            {
                Element Element_TR = (Element)it_TR.next();
                Segment getContent_TR = (Segment)Element_TR.getContent();
                List Elements_FONT = getContent_TR.getAllElements(HTMLElementName.FONT);
                Iterator it_FONT = Elements_FONT.iterator();
                int i = 1;
                while(it_FONT.hasNext())
                {
                    Element Element_FONT = (Element)it_FONT.next();
                    Segment getContent_FONT = (Segment)Element_FONT.getContent();
                    String a1 = getContent_FONT.toString();
                    
                    if ( i == 1 )
                    {
                        int x = a1.lastIndexOf(';');
                        System.out.print(a1.substring( 
                            (a1.lastIndexOf(';'))+1 , a1.length() )+"," );
                    }
                    
                    if ( i == 2 || i == 3)
                    {
                        String y = a1.substring(0,5);
                        if( y.equals("Pass "))
                        {
                            System.out.print(a1.substring(0,4)+",");
                        }
                        else if ( y.equals("+FAIL"))
                        {
                            System.out.print(a1.substring(1,5)+",");
                        }
                        
                    }
                    
                    if (!it_FONT.hasNext())
                    {
                        System.out.print(a1);
                    }
                    //System.out.println(i + " = " + Element_FONT.getContent());
                    i++;
                }
                System.out.println();
            }
        }
    }
}

