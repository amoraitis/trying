package pageScraper;

import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class Site {
	
	private String site;
	private List<String> links, photos;
	public Site(String name,String links,String photos) {
		this.site = name;
		setLinks(links);
		setPhotos(photos);
	}
	
	public void setLinks(String linkArray) {
		JSONObject linksJSON = new JSONObject(linkArray);
		links = linksJSON.getJSONArray(site).toList().stream().map(p->p.toString()).collect(Collectors.toList());
	}

  public List<String> getLinks() {
    return links;
  }
  
  public void setPhotos(String photosArray) {
	JSONObject photosObject = new JSONObject(photosArray);
	photos = photosObject.getJSONArray("photos").toList().stream().map(p->p.toString()).collect(Collectors.toList());
  }
  public List<String> getPhotos() {
	  return photos;
  }
}
