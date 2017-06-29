import java.io.IOException;
import org.json.JSONObject;
import okhttp3.*;

public class GetWeather extends Thread {
	private String city,json;
	public GetWeather(String city) {
		this.city=city;
	}
	@Override
	public synchronized void start() {
		StringBuilder mySite = null;
		mySite = new StringBuilder("https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+city+"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
		json=sendGet(mySite.toString());
	}
	
	public City getCity() {
		return extractJson(json);
	}
	@Override
	public void run() {}	
	
	// HTTP GET request using OKHTTP
		private String sendGet(String url){
			try {
				return run(url);
			} catch (IOException e) {
				e.printStackTrace();
				return "Request to "+ url+" failed!";
			}
		}
	
	private	String run(String url) throws IOException {
		
		OkHttpClient client = new OkHttpClient();
		  Request request = new Request.Builder()
		      .url(url)
		      .build();
		  try(Response response = client.newCall(request).execute()){
			  return response.body().string();
		  }
	}
	
	private City extractJson(String json){
		JSONObject response = new JSONObject(json);
		JSONObject condition = response.getJSONObject("query").getJSONObject("results")
				.getJSONObject("channel").getJSONObject("item").getJSONObject("condition");
		return new City(city, condition.getString("date"), condition.getString("temp"), condition.getString("text"));
	}
}