import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;

import com.maxmind.geoip.*;

public class GetLocation extends Thread {
	private final static File currentFilePath = new File(GetLocation.class.getProtectionDomain()
			.getCodeSource().getLocation().getPath());
	
	private static String DBFOLDER = currentFilePath.getParentFile().getParentFile()+ File.separator+"db"+File.separator;
	
	private String city;
	public GetLocation() {}
	
	@Override
	public synchronized void start() {		 
		 LookupService lookupService=null;
		 String ip="";
		try {
			Enumeration<InetAddress> ene = null;
			Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
			while(e.hasMoreElements())
			{
			    NetworkInterface n = (NetworkInterface) e.nextElement();
			    ene = n.getInetAddresses();			    
			}
			Collections.list(ene).parallelStream().map(p->p.getHostAddress()).forEach(System.out::println);
		    ip = (String)Collections.list(ene).stream().parallel().
		    		filter(p->!p.getHostAddress().startsWith("127") && !p.getHostAddress().startsWith("192")).
		    		map(p->p.getHostAddress().toString()).reduce((p,q)->p).get();
		}catch (SocketException e1) {
			e1.printStackTrace();
		}
		try {
			File file = new File(DBFOLDER+"GeoLiteCity.dat");
			lookupService = new LookupService(file, LookupService.GEOIP_MEMORY_CACHE);
			Location location = lookupService.getLocation(ip);
			System.out.println(file.getPath()+"\n"+location.city);
			
			this.setCity(location.city);
		}catch (UnknownHostException e) {
			e.getMessage();
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
