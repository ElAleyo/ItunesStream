package acv2server.apps.IS.online;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * This class will listen for an UDPpacket to establish a TCP connection later on with an android app.
 * @author joseacevedo
 *
 */
public class BroadcastReceiver {
	
	
	private DatagramSocket socket;
	
	/**
	 * @throws SocketException 
	 * 
	 */
	public BroadcastReceiver(int port ) throws SocketException
	{
		socket = new DatagramSocket(port);
	}
	
	/**
	 * 
	 * @return true if an TCP client was found
	 */
	public boolean connectionAttempt()
	{
		byte[] dataReceived = new byte[1024];
		DatagramPacket messageFromClient = new DatagramPacket(dataReceived, dataReceived.length);
		
		try {
			System.out.println("Preparing to connect to device...");
			socket.receive(messageFromClient);
			String clientMsgString = new String(messageFromClient.getData());
			System.out.println("Message: -" + clientMsgString +"- Received from " +messageFromClient.getAddress()+":"+messageFromClient.getPort());
			if("set_connection_packet".equals(clientMsgString.trim()))
			{
				byte[] dataToSend = "empty msg".getBytes();
				DatagramPacket sendServerIP = new DatagramPacket(dataToSend, dataToSend.length, messageFromClient.getAddress(), messageFromClient.getPort());
				System.out.println("Sending response message...");
				socket.send(sendServerIP);
				System.out.println("Response Message sent ");
			}
			else{
				System.out.println("Unknown message: " + clientMsgString);
			}
		} catch (IOException e) {

			System.out.println("IO Exception");
			e.printStackTrace();
			return false;
		}
		finally{
			if(this.socket != null)
			{
				this.socket.close();
				System.out.println("Socket closed");
			}
		}
		
		return true;
	}
	
	public void finalize()
	{
		System.out.println("Garbage Collector setup message socket close Attempt");
		this.socket.close();
	}
}
