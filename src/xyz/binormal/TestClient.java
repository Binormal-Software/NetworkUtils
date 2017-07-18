package xyz.binormal;
import java.net.SocketTimeoutException;

class TestClient {

	
	
	public static void main(String[] args) {
		
		UDPBroadcaster bcast = new UDPBroadcaster("12345");
		
		try {
			
			InterfaceLocator il = new InterfaceLocator();
			
			System.out.println("Found address " + il.getHostAddress().getHostAddress());
			
			System.out.println("SERVER LOCATED AT: " +
					bcast.findAddresses(2016, il.getHostAddress(), 2000));
			
		} catch(SocketTimeoutException e){
			System.err.println("Failed to locate server. (No response.) Request timed out.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
