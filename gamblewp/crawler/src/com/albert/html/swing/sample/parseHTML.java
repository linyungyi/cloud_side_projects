package com.albert.html.swing.sample;

import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.io.*;
import java.net.*;
  
public class parseHTML extends HTMLEditorKit.ParserCallback {
  
    // 記錄是否將資料印出
    private boolean inHeader=false;
  
    public parseHTML() {}
  
    // 將 Parse HTML 後的資料印出
    public void handleText(char[] text, int position) {
       if (inHeader) {
           // 印出 xxxx => <A HREF = ....> xxxx </A>
           // xxxx => HTML Tag A 的文字 (text)
           System.out.println(text);
       }
    }
  
    // Parse HTML Start Tag
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes,int position) {
  
        // 分析 Tag 的重點在這行
        if (tag == HTML.Tag.BODY) {
            this.inHeader = true;
        }
    }
  
    // Parse HTML End Tag
    public void handleEndTag(HTML.Tag tag, int position) {
        if (tag == HTML.Tag.BODY) {
            inHeader = false;
        }
    }
  
    public static void main(String[] args) {
  
        ParserGetter kit = new ParserGetter();
        HTMLEditorKit.Parser parser = kit.getParser();
  
        HTMLEditorKit.ParserCallback callback = new parseHTML();
  
        try {
            // 輸入欲分析的網頁
            URL u = new URL("https://bet.i-win.com.tw/SBP2Web/betting.do?method=getBettingList&sportCode=BS&poolCode=ALL");
  
            // 讀入網頁
            InputStream in = u.openStream();
            InputStreamReader r = new InputStreamReader(in);
  
            // 呼叫 parse method 開始進行  Parse HTML
            parser.parse(r, callback, true);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}