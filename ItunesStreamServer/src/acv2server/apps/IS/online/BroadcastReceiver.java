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
	private static final int CLIENT_WAIT_TIME = 5000;
	private boolean serverResponded = false;
	private Object myMonitor;

	/**
	 * @throws SocketException 
	 * 
	 */
	public BroadcastReceiver(int port , Object monitor) throws SocketException
	{
		socket = new DatagramSocket(port);
		myMonitor = monitor;
	}

	/**
	 * 
	 * @return true if an TCP client was found
	 */
	public boolean connectionAttempt()
	{

		try {
			System.out.println("Waiting "+ CLIENT_WAIT_TIME/1000 + "'s for client broadcast message" );
			//Thread.sleep(CLIENT_WAIT_TIME);

			synchronized (myMonitor) {
				myMonitor.wait(CLIENT_WAIT_TIME);		
			}
		} catch (InterruptedException e) {
			System.out.println("Sleep interrupted, Close Connection");
			e.printStackTrace();
			return false;
		}

		return serverResponded;
	}

	public void finalize()
	{
		System.out.println("Garbage Collector setup message socket close Attempt");
		this.socket.close();
	}

	public void tryConnection()
	{
		try {
			byte[] dataReceived = new byte[1024];
			DatagramPacket messageFromClient = new DatagramPacket(dataReceived, dataReceived.length);

			System.out.println("Preparing to connect to device...");
			//This blocking operation stops the Thread until a message is received
			socket.receive(messageFromClient);

			String clientMsgString = new String(messageFromClient.getData());
			System.out.println("Message: -" + clientMsgString +"- Received from " +messageFromClient.getAddress()+":"+messageFromClient.getPort());
			if("set_connection_packet".equals(clientMsgString.trim()))
			{
				byte[] dataToSend = "dummy response msg".getBytes();
				DatagramPacket sendServerIP = new DatagramPacket(dataToSend, dataToSend.length, messageFromClient.getAddress(), messageFromClient.getPort());
				System.out.println("Sending response message...");
				socket.send(sendServerIP);
				System.out.println("Response Message sent ");
				serverResponded = true;
				synchronized (myMonitor) {
					myMonitor.notify();					
				}
			}
			else{
				System.out.println("Unknown message: " + clientMsgString);
			}

		} catch (IOException e) {
			System.out.println("Receiving message IOException");
			e.printStackTrace();
		}
		finally{
			if(socket != null)
			{
				socket.close();
				System.out.println("Socket closed");
			}
		}


	}


}
