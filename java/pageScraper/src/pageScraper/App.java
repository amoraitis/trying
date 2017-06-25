package pageScraper;
public class App extends Thread{
	public App() {
	}

  public static synchronized void main(String[] args) throws InterruptedException {
	  Site site =null;
	  App app = new App();
	  app.start();
	  String siteName = "https://www.aueb.gr/";
	  
	  ConnectionToSQL connectionToSQL = new ConnectionToSQL(siteName);
	  connectionToSQL.start();
	  app.sleep(2000);
	  site = connectionToSQL.getSite();	  
	  if(site==null){
		  connectionToSQL.sleep(2000);
		  SiteDownloader siteDownloader = new SiteDownloader(siteName);
		  siteDownloader.start();
		  siteDownloader.join();
		  siteDownloader.interrupt();
		  Extraction extraction = new Extraction(siteDownloader.getHtml(), siteName);
		  extraction.start();
		  extraction.join();
		  site=extraction.getSite(); 
		  connectionToSQL.setLinksPhotos(extraction.getSite().getLinksAsString(), extraction.getSite().getPhotosAsString());
		  connectionToSQL.run();
		  connectionToSQL.join();
	  }
	  System.out.println(site.toString());
		  
  }
 }

