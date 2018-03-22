package com.albert.html.parser.sample;

import net.htmlparser.jericho.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class FormFieldList {
	public static void main(String[] args) throws Exception {
		String sourceUrlString="https://bet.i-win.com.tw/SBP2Web/betting.do?method=getBettingList&sportCode=BS&poolCode=ALL";
		if (args.length==0)
		  System.err.println("Using default argument of \""+sourceUrlString+'"');
		else
			sourceUrlString=args[0];
		if (sourceUrlString.indexOf(':')==-1) sourceUrlString="file:"+sourceUrlString;
		Source source=new Source(new URL(sourceUrlString));
		FormFields formFields=source.getFormFields();
		System.out.println("The document "+sourceUrlString+" contains "+formFields.size()+" form fields:\n");
		for (FormField formField : formFields) {
			System.out.println(formField.getDebugInfo());
		}
  }
}
