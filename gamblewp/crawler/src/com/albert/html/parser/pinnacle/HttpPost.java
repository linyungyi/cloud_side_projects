package com.albert.html.parser.pinnacle;


import java.io.IOException;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

public class HttpPost
{
    private static String url = "https://www.pinnaclesports.com/Secure/LoginPage.aspx?destination=sports"; 

    public static void main(String[] args)
    {
        HttpClient client = new HttpClient();

        PostMethod post = new PostMethod(url);

        NameValuePair[] data = { new NameValuePair("ctl00$MCPH$LF$UserName", "BL396108"), new NameValuePair("ctl00$MCPH$LF$Password", "naeba123") };

        post.setRequestBody(data);

        try {

            int statusCode = client.executeMethod(post);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + post.getStatusLine());
            }


            byte[] responseBody = post.getResponseBody();
            System.out.println(new String(responseBody));

        } catch (HttpException httpexc) {
            System.err.println("Fatal protocol violation: " + httpexc.getMessage());
            httpexc.printStackTrace();
        } catch (IOException ioexc) {
            System.err.println("Fatal transport error: " + ioexc.getMessage());
            ioexc.printStackTrace();
        } finally {


            post.releaseConnection();
        }
    }
}