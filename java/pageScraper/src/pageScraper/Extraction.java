package pageScraper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class Extraction extends Thread {
	private String html, name;
	private Site site;
	public Extraction(String html,String name){
		this.html=html;
		this.name=name;
	}
	@Override
	public void run() {
		Document document = Jsoup.parse(html);
		site = new Site(name,getJson("links", extractLinks(document)).toString(),getJson("photos", extractPhotos(document)).toString());
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
	public Site getSite( ) {
		return this.site;
  }
}
