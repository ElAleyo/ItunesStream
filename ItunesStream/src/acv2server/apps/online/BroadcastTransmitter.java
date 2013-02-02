package acv2server.apps.online;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

/**
 * This class will send a broadcast to the network to try and connect to the an iTunesStream server 
 * @author joseacevedo
 *
 */
public class BroadcastTransmitter {
	
	private DatagramSocket socket;
	private int port;
	
	public BroadcastTransmitter(int port) throws SocketException
	{
		this.port = port;
		this.socket = new DatagramSocket();
		this.socket.setBroadcast(true);
	}
	
	/**
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public String getIpOfServer() throws UnknownHostException, IOException 
	{
		
		byte[] requestMsg = "set_connection_packet".getBytes();
		DatagramPacket sendRequestMsg = new DatagramPacket(requestMsg, requestMsg.length, InetAddress.getByName("255.255.255.255"), this.port);
		
		Log.d("DEBUG", "Send Connection Request Message");
		socket.send(sendRequestMsg);
		
		Log.d("DEBUG", "Connection Request Message sent");
		
		byte[] resp = new byte[1024];
		DatagramPacket serverResponse = new DatagramPacket(resp, resp.length);
		Log.d("DEBUG", "Waiting for Server Response");
		socket.receive(serverResponse);
		
		Log.d("DEBUG", "Server Responded from ip: " +serverResponse.getAddress().getCanonicalHostName());
		
		
		socket.close();
		return serverResponse.getAddress().getCanonicalHostName();
	}

}
