package pageScraper;

import java.io.*;
import java.net.*;

public class SiteDownloader extends Thread{
	private static String site;
	private volatile String html;
	public SiteDownloader(String site){
		this.site=site;
	}
	
	@Override
	public synchronized void run() {
		URL mySite = null;
		StringBuilder html = new StringBuilder("null");
		try {
	      mySite = new URL(site);
	      site= getSiteCorrect();
	    } catch (MalformedURLException e) {
	    	System.err.println("Bad URL!");
	    }
		try {
	      HttpURLConnection httpConn = (HttpURLConnection) mySite.openConnection();
	      httpConn.setRequestMethod("GET");
	      BufferedReader reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
	      String line; html.delete(0, 4);
	      while((line=reader.readLine())!=null){
	    	  html.append(line);
	      }
	      reader.close();
		} 
		catch (IOException e) {
	      System.err.println("Cannot get Content(I/O exc)");
	    }
		if(!html.equals("null")) {
			this.setHtml(html);
		}
	}
	
	public String getSite() {
		return this.site;
	}
	
	public String getHtml() {
		return this.html;
	}
	
	public void setHtml(StringBuilder html) {
		this.html=html.toString();
	}
	
	private String getSiteCorrect() {
		System.out.println(site);
		String[] site_dot = site.split(".");
		System.out.println(site.contains(".")+"\t" + site_dot.length);
		for(String e:site_dot){
			System.out.println(e);
		}
		//site = site_dot[0];
		return site;
	}
  }