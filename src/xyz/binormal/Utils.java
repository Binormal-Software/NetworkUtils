package xyz.binormal;

import java.util.Scanner;

public class Utils {

	public static String convertStreamToString(java.io.InputStream is) {
		
		try(Scanner scanner = new Scanner(is)){
			scanner.useDelimiter("\\A");
			
		    String output = scanner.hasNext() ? scanner.next() : "";
		    scanner.close();
		    
		    return output;
		}
		
	}
	
	public static byte[] getFingerprint(String handshakeMessage, String appVersion, String serverName){
		return (handshakeMessage + ":" + appVersion + ":" + serverName).getBytes();
	}
}
