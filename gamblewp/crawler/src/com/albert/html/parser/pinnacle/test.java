package com.albert.html.parser.pinnacle;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;
import java.util.*;
import javax.net.ssl.*;
import java.net.Authenticator;

public class test {
	private String url = "https://localhost:8443/me2/SetUserInfo";
	// String
	// post="TransactionID=a&Caller=b&Callee=c&RingID=e&Eventon=sss&OperatorID=d";
	String post = "";
	String username="BL396108";
	String password="naeba123";
	final static String TransactionID_tag = "TransactionID=";
	final static String Caller_tag = "&Caller=";
	final static String Callee_tag = "&Callee=";
	final static String RingID_tag = "&RingID=";
	final static String Eventon_tag = "&EventOn=";
	final static String Operator_tag = "&OperatorID=";

	private X509TrustManager xtm = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] chain, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) {
			// System.out.println("cert: " + chain[0].toString() +
			// ", authType: " + authType);
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	};

	private HostnameVerifier hnv = new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
			// System.out.println("hostname: " + hostname);
			return true;
		}
	};

	public test(String Url) {
		url = Url;
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("TLS");
			X509TrustManager[] xtmArray = new X509TrustManager[] { xtm };
			sslContext.init(null, xtmArray, new java.security.SecureRandom());
		} catch (GeneralSecurityException gse) {
			// Print out some error message and deal with this exception
		}

		if (sslContext != null) {
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
					.getSocketFactory());
		}
		HttpsURLConnection.setDefaultHostnameVerifier(hnv);
	}

	public void run() {
		try {
			
			String key = "";
			String id = "";
			String strResponse = "";
			String readLine;
			
			HttpURLConnection m_uc0 = (HttpURLConnection) (new URL("http://www.pinnaclesports.com/"))
			.openConnection();
			
			//DataOutputStream dos0 = new DataOutputStream(m_uc0.getOutputStream());

			for (int i = 1; (key = m_uc0.getHeaderFieldKey(i)) != null; i++) {
				System.out.println(key + ":" + m_uc0.getHeaderField(key));
				//if (key.equalsIgnoreCase("set-cookie")) {
				//	id = m_uc0.getHeaderField(key);
				//	id = id.substring(0, id.indexOf(";"));
				//} // if
			}
			
			BufferedReader br0 = new BufferedReader(new InputStreamReader(m_uc0
					.getInputStream()));

			while ((readLine = br0.readLine()) != null) {
				//System.out.println(readLine);
				if( readLine.indexOf("input")>0 )
				{
					//System.out.println(readLine);
					String name=readLine.substring(readLine.indexOf("name=")+6);
					name = name.substring(0,name.indexOf('"'));
					String value=readLine.substring(readLine.indexOf("value=")+7);
					value = value.substring(0,value.indexOf('"'));
					
					System.out.println(name);
					System.out.println(value);
					
					if(name.indexOf("UserName")>0)
					{
						post=post+name+"="+username+"&";
					}else if(name.indexOf("Password")>0)
					{
						post=post+name+"="+password+"&";
					}else
					{
						post=post+name+"="+value+"&";
					}
				}
			}
			post = post.substring(0, post.length()-1);
			System.out.println(post);
			br0.close();
			
			Authenticator.setDefault(new MyAuthenticator(username, password));
			
			HttpURLConnection m_uc = (HttpURLConnection) (new URL("https://www.pinnaclesports.com/Secure/LoginPage.aspx?destination=sports"))
					.openConnection();
			m_uc.setDoInput(true);
			m_uc.setDoOutput(true);
			m_uc.setUseCaches(false);
			m_uc.setRequestMethod("POST");

			DataOutputStream dos = new DataOutputStream(m_uc.getOutputStream());
			//dos.writeBytes(post);
			dos.writeBytes("ctl00$MCPH$LF$UserName="+username);
			dos.writeBytes("&ctl00$MCPH$LF$Password="+password);
			dos.flush();
			dos.close();


			for (int i = 1; (key = m_uc.getHeaderFieldKey(i)) != null; i++) {
				System.out.println(key + ":" + m_uc.getHeaderField(key));
				if (key.equalsIgnoreCase("set-cookie")) {
					id = m_uc.getHeaderField(key);
					id = id.substring(0, id.indexOf(";"));
				} // if
			}

			// System.out.println("cookie = "+id);

			BufferedReader br = new BufferedReader(new InputStreamReader(m_uc
					.getInputStream()));

			while ((readLine = br.readLine()) != null) {
				System.out.println(readLine);
				strResponse += readLine;
			}
			br.close();

			//URL url = new URL(
			//		"https://www2.pinnaclesports.com/members/LoggedInPage.aspx?destination=sports");
			/*URL url = new URL(
							"https://www.pinnaclesports.com/");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestProperty("Cookie", id);
			connection.connect();
			System.out.println("**********************************");
			for (int i = 1; (key = m_uc.getHeaderFieldKey(i)) != null; i++) {
				System.out.println(key + ":" + m_uc.getHeaderField(key));
				if (key.equalsIgnoreCase("set-cookie")) {
					id = m_uc.getHeaderField(key);
					id = id.substring(0, id.indexOf(";"));
				} // if
			}

			BufferedReader br2 = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			String readLine2;
			while ((readLine2 = br2.readLine()) != null) {
				//System.out.println(readLine2);

			}
			br2.close();*/

			// System.out.println(strResponse);

			// Whatever we want to do with these quotes
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String transaction() {
		long t = System.currentTimeMillis();
		String id = new Long(t).toString();
		id = id.substring(id.length() - 10, id.length());
		return id;
	}

	public String FormatTime() {
		int yy, mm, dd, hh, ms, ss;
		String daytime;

		yy = java.util.Calendar.getInstance().get(Calendar.YEAR);
		mm = java.util.Calendar.getInstance().get(Calendar.MONTH) + 1;
		dd = java.util.Calendar.getInstance().get(Calendar.DATE);
		hh = java.util.Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		ms = java.util.Calendar.getInstance().get(Calendar.MINUTE);
		ss = java.util.Calendar.getInstance().get(Calendar.SECOND);

		daytime = new Integer(yy).toString();
		daytime += (mm < 10 ? "0" + new Integer(mm).toString()
				: new Integer(mm).toString());
		daytime += (dd < 10 ? "0" + new Integer(dd).toString()
				: new Integer(dd).toString());
		daytime += (hh < 10 ? "0" + new Integer(hh).toString()
				: new Integer(hh).toString());
		daytime += (ms < 10 ? "0" + new Integer(ms).toString()
				: new Integer(ms).toString());
		daytime += (ss < 10 ? "0" + new Integer(ss).toString()
				: new Integer(ss).toString());

		return daytime;
	}

	public void encode(String caller, String callee, String ringid,
			String OperatorID) {
		/*
		 * post=TransactionID_tag+transaction(); post=post+Caller_tag+caller;
		 * post=post+Callee_tag+callee; post=post+RingID_tag+ringid;
		 * post=post+Eventon_tag+FormatTime();
		 * post=post+Operator_tag+OperatorID;
		 */
		//post = "ctl00$MCPH$LF$UserName=st291188&ctl00$MCPH$LF$Password=lqehanujX7";
		//post="ctl00$MCPH$LF$UserName=BL396108&ctl00$MCPH$LF$Password=naeba123&__EVENTTARGET=&__EVENTARGUMENT=&__LASTFOCUS=&ctl00$MCPH$LF$LanguageID=0&ctl00$MCPH$LF$PriceStyle=&ctl00$MCPH$LF$LinesTypeView=c&ctl00$MCPH$LF$MemberServer=www35.pinnaclesports.com&__PREVIOUSPAGE=2eoCOYt8-BaH3Fk4TmLAjNDipT_VBAaVYOUq08FQtLoUD6yQNdxYWcgAm6WCzq7jEhWHY";
		post="ctl00$MCPH$LF$UserName=BL396108&ctl00$MCPH$LF$Password=naeba123&__EVENTTARGET=&__EVENTARGUMENT=&__LASTFOCUS=&ctl00$MCPH$LF$LanguageID=0&ctl00$MCPH$LF$PriceStyle=&ctl00$MCPH$LF$LinesTypeView=c&ctl00$MCPH$LF$MemberServer=www35.pinnaclesports.com&__PREVIOUSPAGE=G8UybpNHRfkqAHWMjjC8cPo0JF9ZSH8QPSfoSUJE29HpqpfXbsXkdP_kdf38TsBchf_UKeL3loPxJ5j3h4qecXjTdq01";
	}

	public static void main(String[] args) {
		test httpsTest = new test(
				//"https://www.pinnaclesports.com/");
				"https://www.pinnaclesports.com/Secure/LoginPage.aspx?destination=sports");
		//httpsTest.encode("0933926491", "0922234234", "012345", "01");
		httpsTest.run();

	}
	
	  static class MyAuthenticator extends Authenticator {
		    private String username, password;

		    public MyAuthenticator(String user, String pass) {
		      username = user;
		      password = pass;
		    }

		    protected PasswordAuthentication getPasswordAuthentication() {
		      System.out.println("Requesting Host  : " + getRequestingHost());
		      System.out.println("Requesting Port  : " + getRequestingPort());
		      System.out.println("Requesting Prompt : " + getRequestingPrompt());
		      System.out.println("Requesting Protocol: "
		          + getRequestingProtocol());
		      System.out.println("Requesting Scheme : " + getRequestingScheme());
		      System.out.println("Requesting Site  : " + getRequestingSite());
		      return new PasswordAuthentication(username, password.toCharArray());
		    }
		  }
}