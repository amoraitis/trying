/**
 * author: amoraitis
 * created: 17/6/2017
 */

package checkingPorts;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class PortsAvailable {	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String ip,port;
		System.out.println("Enter ipt to check: ");
		ip = scanner.nextLine();
		if(ip.equals("")){
			System.out.println("You must specify the host!!");
			return;
		}
		System.out.println("Enter the port range (Ex 20-80): ");
		port = scanner.nextLine();
		if(port.equals("")){
			System.out.println("You must specify a port range!!");
			return;
		}
		String[] ports = port.split("-");
		//To make the execution faster, use threads and distribute the connections
		for(int i=Integer.parseInt(ports[0]); i<Integer.parseInt(ports[1])+1; i++){
			if(connect(ip, i))
				System.out.println(i+": available.");
		}		
	}
	
	private static boolean connect(String ip, int port) {
		Socket requestSocket = null;
        try {              
            requestSocket = new Socket(ip,port);
            System.out.println("\t" + port + " is not available");
            return false;
        } catch (IOException e) {
        	return true;
        }finally {
            try {
                requestSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (NullPointerException e2) {
            	// TODO: handle exception
            }
        }
	}
}
