package pageScraper;

import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class Site {
	
	private String site;
	private String linksAsString, photosAsString;
	private List<String> links, photos;
	public Site(String name,String links,String photos) {
		this.site = name;
		setLinks(links);
		setPhotos(photos);
		linksAsString=links; photosAsString=photos;
	}
	
	public void setLinks(String linkArray) {
		JSONObject linksJSON = new JSONObject(linkArray);
		links = linksJSON.getJSONArray("links").toList().stream().map(p->p.toString()).collect(Collectors.toList());
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
  
  /**
 * @return the linksAsString
 */
  public String getLinksAsString() {
	return linksAsString;
  }
	
	/**
	 * @return the photosAsString
	 */
	public String getPhotosAsString() {
		return photosAsString;
	}

@Override
	public String toString() {
	  StringBuilder linksString = new StringBuilder(), photosString=new StringBuilder();
	  getLinks().stream().map(p->p+"\n\t").forEach(linksString::append); getPhotos().stream().map(p->p+"\n\t").forEach(photosString::append);
	  return this.site+ "\nLinks: \n" + linksString+"\nPhotos: \n"+ photosString; 
	}
}
