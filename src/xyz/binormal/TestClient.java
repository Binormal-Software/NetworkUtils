package xyz.binormal;
import java.net.SocketTimeoutException;

class TestClient {
	
	public static final int DEFAULT_PORT = 2016;
	public static final String HANDSHAKE = "12345";
	
	public static void main(String[] args) {
		
		try(UDPBroadcaster bcast = new UDPBroadcaster(HANDSHAKE)){
			InterfaceLocator il = new InterfaceLocator();
			
			bcast.findAddresses(DEFAULT_PORT, il.getHostAddress(), 2000);
			
		} catch(SocketTimeoutException e){
			System.err.println("Failed to locate server. (No response.) Request timed out.");
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
