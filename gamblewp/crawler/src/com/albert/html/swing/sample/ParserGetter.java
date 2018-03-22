package com.albert.html.swing.sample;
import javax.swing.text.html.*;

public class ParserGetter extends HTMLEditorKit {
  
    //purely to make this methods public
    public HTMLEditorKit.Parser getParser() {
        return super.getParser();
    }
}