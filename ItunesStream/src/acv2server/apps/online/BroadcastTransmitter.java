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
		final DatagramPacket serverResponse = new DatagramPacket(resp, resp.length);

		//Create a new thread to wait for the server response
		Thread t = new Thread(){
			@Override
			public void run()
			{
				Log.d("DEBUG", "Waiting for Server Response");

				try {
					socket.receive(serverResponse);
					Log.d("DEBUG", "Server Response thread done");
				} catch (IOException e) {
					Log.d("DEBUG", "Server Response error");
					e.printStackTrace();
				}

			}
		};


		t.start();


		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			Log.d("DEBUG", "Current Thread wait error");
			e.printStackTrace();
		}
		finally{
			socket.close();
			Log.d("DEBUG", "Socket Closed");
		}

		if(serverResponse.getAddress() != null)		
		{
			Log.d("DEBUG", "Server Responded from ip: " +serverResponse.getAddress().getCanonicalHostName());
			return serverResponse.getAddress().getCanonicalHostName();
		}
		else
			return "0.0.0.0";
	}

}
