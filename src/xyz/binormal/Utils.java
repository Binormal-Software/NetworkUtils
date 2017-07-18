package xyz.binormal;

public class Utils {

	public static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    System.out.println("Reading...");
	    return s.hasNext() ? s.next() : "";
	}
	
	public static byte[] getFingerprint(String handshakeMessage, String appVersion, String serverName){
		return (handshakeMessage + ":" + appVersion + ":" + serverName).getBytes();
	}
}
