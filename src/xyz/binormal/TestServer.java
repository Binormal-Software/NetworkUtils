package xyz.binormal;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class TestServer implements Runnable
{

	public static final int DEFAULT_PORT = 2016;
	public static final String HANDSHAKE = "12345";
	private DatagramSocket socket;
	private DatagramPacket packet;

	public static void main(String[] args){
		(new Thread(new TestServer())).start();
	}

	public void run(){

		try{

			socket = new DatagramSocket(DEFAULT_PORT);
			packet = new DatagramPacket (new byte[1], 1);

			System.out.println("Server running on port " + DEFAULT_PORT );

			while (true){

				socket.receive (packet);
				InetAddress clientAddress = packet.getAddress();
				int clientPort = packet.getPort();

				System.out.println("Received ping from: " + clientAddress + ":" + clientPort);
				packet.setData (Utils.getFingerprint(HANDSHAKE, "version 1.0", "Test Server!")); // respond to broadcast
				socket.send(packet);

			}

		}catch(SocketException e){
			System.err.println("Error binding socket.");
			e.printStackTrace();
			
		}catch(IOException e){
			System.err.println("IOException.");
			e.printStackTrace();
		}
	}
}