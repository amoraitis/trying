package pageScraper;

public class App {

  public static void main(String[] args) {
	  String site = "https://github.com/";
	  /*SiteDownloader mySiteDownloader = new SiteDownloader(site);
	  mySiteDownloader.start();
	  try {
		  mySiteDownloader.join();
		  Extraction extraction = new Extraction(mySiteDownloader.getHtml(), mySiteDownloader.getSite());
		  extraction.start();
	  } catch (InterruptedException e){
		  e.getCause();
	  }*/
	  ConnectionToSQL connectionToSQL = new ConnectionToSQL(site);
	  connectionToSQL.start();
  }
}
