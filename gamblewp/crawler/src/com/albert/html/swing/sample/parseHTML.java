package com.albert.html.swing.sample;

import javax.swing.text.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;
import java.io.*;
import java.net.*;
  
public class parseHTML extends HTMLEditorKit.ParserCallback {
  
    // �O���O�_�N��ƦL�X
    private boolean inHeader=false;
  
    public parseHTML() {}
  
    // �N Parse HTML �᪺��ƦL�X
    public void handleText(char[] text, int position) {
       if (inHeader) {
           // �L�X xxxx => <A HREF = ....> xxxx </A>
           // xxxx => HTML Tag A ����r (text)
           System.out.println(text);
       }
    }
  
    // Parse HTML Start Tag
    public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes,int position) {
  
        // ���R Tag �����I�b�o��
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
            // ��J�����R������
            URL u = new URL("https://bet.i-win.com.tw/SBP2Web/betting.do?method=getBettingList&sportCode=BS&poolCode=ALL");
  
            // Ū�J����
            InputStream in = u.openStream();
            InputStreamReader r = new InputStreamReader(in);
  
            // �I�s parse method �}�l�i��  Parse HTML
            parser.parse(r, callback, true);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}