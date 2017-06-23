package pageScraper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class Extraction extends Thread {
	private String html, site;
	public Extraction(String html,String site){
		this.html=html;
		this.site=site;
	}
	@Override
	public void run() {
		Document document = Jsoup.parse(html);
		ConnectionToSQL connectionToSQL = new ConnectionToSQL(site, getJson(site, extractLinks(document)).toString(), getJson("photos", extractPhotos(document)).toString());
		connectionToSQL.start();
		try {
	      connectionToSQL.join();
	    } catch (InterruptedException e) {
	      System.err.println("Not ended thread!");
	    }
	}
	
	private ArrayList<String> extractLinks(Document document) {
		ArrayList<String> links = new ArrayList<String>();
		links =  (ArrayList<String>) document.select("a").stream().parallel().map(p->p.attr("href")).collect(Collectors.toList()).
				stream().parallel().filter(p->p.contains("http")).map(p->p.toString()).collect(Collectors.toList());
		return links;
    }
	private ArrayList<String> extractPhotos(Document document) {
		ArrayList<String> links = new ArrayList<String>();
		links = (ArrayList<String>) document.select("img").stream().parallel().map(p->p.attr("src")).collect(Collectors.toList());
		return links;
	}
	private JSONObject getJson(String name, ArrayList<String> links) {
		JSONObject myJsonObject = new JSONObject();
		JSONArray linksJSON =new JSONArray(links);
		myJsonObject.put(name, linksJSON);
		return myJsonObject;
	}
	
}
