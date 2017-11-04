
package com.grallandco.demos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.Proxy;
import java.net.InetSocketAddress;
import java.io.OutputStreamWriter;

public class Curl {

	private String url;
  Curl(String url)
  {
	this.url=url;
	System.out.println(url);
  }



  public void sendToES(String message) {

    try {

    //String url = "http://localhost:9200/temperature/doc/?pretty";

    URL obj = new URL(this.url);
    HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

    conn.setRequestProperty("Content-Type", "application/json");
    conn.setDoOutput(true);

    conn.setRequestMethod("PUT");

    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
    out.write(message);
    System.out.println("sent to Elasticsearch!!");
    out.close();

    new InputStreamReader(conn.getInputStream());   

    } catch (Exception e) {
    e.printStackTrace();
    }

  }

}
