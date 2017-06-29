
public class City {
	String city, date, type;
	int temp;
	public City(String city, String date, String temp, String type) {
		this.city=city;
		this.date=date;
		this.temp=Integer.parseInt(temp);
		this.type=type;
	}
	public String toString() {
		return "You requested: " +city +"\nDatetime: "+date+"\nTemperature: "+(temp-32)*5/9+"\nClouds: "+type;
	}
}