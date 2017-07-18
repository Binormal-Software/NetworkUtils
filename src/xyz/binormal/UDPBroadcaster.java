package xyz.binormal;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Class used to broadcast a UDP packet to all addresses in a specific range.</p>
 * 
 * @author Ryan Rodriguez
 * 
 */
public class UDPBroadcaster{
	
	private String handshakeMessage;
	
	private int socketTimeout = 5000;
    private InetAddress host;
    private DatagramSocket socket;
    private DatagramPacket packet;
    
    public UDPBroadcaster(String handshakeMessage){
    	this.handshakeMessage = handshakeMessage;
    }
    
    public List<Connectable> findAddresses(int port, InetAddress hostAddress, int timeout) throws IOException{
    	socketTimeout = timeout;
    	return findAddresses(port, hostAddress);
    }
    
    /**
     * 
     * @param port
     * @param hostAddress
     * @return address found by scan
     * @throws IOException
     */
    public List<Connectable> findAddresses(int port, InetAddress hostAddress) throws IOException{

    	String hostPrefix = hostAddress.getHostAddress();
    	hostPrefix = hostPrefix.substring(0, hostPrefix.lastIndexOf('.'));

    	host = InetAddress.getByName(hostPrefix + ".255");
    	socket = new DatagramSocket (null);
    	socket.setSoTimeout(socketTimeout);
    	
    	packet = new DatagramPacket (new byte[36], 0, host, port);
    	socket.send (packet);
    	
    	packet.setLength(36);
    	
    	List<Connectable> addressList = new ArrayList<Connectable>();
    	
    	for(int i = 0; i < 5; i++){
    		try{
    			socket.receive(packet);
    			String[] packetData = new String(packet.getData()).split(":");
    			
    			if(!packetData[0].equals(this.handshakeMessage))
    				throw new IllegalArgumentException();
    			
    			addressList.add(new Connectable(packet.getAddress(), packetData[2], packetData[1]));
    			System.out.println("Got reply from " + packet.getAddress() + "!");
    			System.out.println("Server Name: " + packetData[2] + " | App version: " + packetData[1]);
    		}catch(IOException ioe){
    			System.out.println("No reply that time...");
    		}catch(IllegalArgumentException iae){
    			System.out.println("Recieved a malformed response from " + packet.getAddress() + ", could it be an outdated version?");
    		}
    	}
    	
    	socket.close();

    	return addressList;

    }
 
}
