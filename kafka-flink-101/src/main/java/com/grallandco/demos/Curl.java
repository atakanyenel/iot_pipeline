
package com.grallandco.demos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.io.OutputStreamWriter;

public class Curl {

  public void sendToES(String message) {

    try {

    String url = "http://localhost:9200/temperature/doc/1?pretty";

    URL obj = new URL(url);
    HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true);

    conn.setRequestMethod("PUT");

    String data =  "{\"temperature\":\""+message+"\"}";
    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
    out.write(data);
    System.out.println("sent!!");
    out.close();

    new InputStreamReader(conn.getInputStream());   

    } catch (Exception e) {
    e.printStackTrace();
    }

  }

}
