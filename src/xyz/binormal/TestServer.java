package xyz.binormal;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class TestServer implements Runnable
{
	public static void main(String[] args){
		(new Thread(new TestServer())).start();
	}
	
    public static final int DEFAULT_PORT = 2016;
    private DatagramSocket socket;
    private DatagramPacket packet;

    public void run(){
        try{
            socket = new DatagramSocket(DEFAULT_PORT);
            System.out.println("Server running on port " + DEFAULT_PORT );
        }
        catch( Exception ex ){
            System.out.println("Problem creating socket on port: " + DEFAULT_PORT );
        }

        packet = new DatagramPacket (new byte[1], 1);

        while (true){
            try{
                socket.receive (packet);
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                
                System.out.println("Received ping from: " + clientAddress + ":" + clientPort);
                packet.setData (Utils.getFingerprint("12345", "version 1.0", "Test Server!")); // respond to broadcast
                socket.send (packet);
            }
            catch (IOException ie){
                ie.printStackTrace();
            }
        }
    }
}