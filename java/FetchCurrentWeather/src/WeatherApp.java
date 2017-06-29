import java.util.Scanner;

public class WeatherApp {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String city = s.nextLine();
		GetWeather getWeather=new GetWeather(city);
		getWeather.start();
		try {
			getWeather.join();
		} catch (InterruptedException e) {
			e.getMessage();
		}
		System.out.println(getWeather.getCity());
	}
}