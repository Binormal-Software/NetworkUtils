package xyz.binormal;

import java.net.InetAddress;

public class Connectable { // storage class for detected connections

	private InetAddress connectableAddress;
	private String name;
	private String appVersion;
	
	public Connectable(InetAddress address, String name, String appVersion){
		this.connectableAddress = address;
		this.name = name;
		this.appVersion = appVersion;
	}
	
	public String getName(){
		return this.name;
	}
	
	public InetAddress getInetAddress(){
		return this.connectableAddress;
	}
	
	public String serverVersion(){
		return this.appVersion;
	}
}
