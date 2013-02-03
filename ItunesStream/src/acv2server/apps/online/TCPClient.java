package acv2server.apps.online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;

public class TCPClient {

	private Socket client;
	private String data;
	private int port ;
	
	public TCPClient( int port , int responseWaitTime) throws UnknownHostException, IOException
	{
		this.port = port;
		BroadcastTransmitter bt = new BroadcastTransmitter(this.port, responseWaitTime);
		String ip = bt.getIpOfServer();
		client = new Socket(ip, this.port);

	}


	public String getSongList() throws IOException
	{
		DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
		String req = "songlist_request";
		outToServer.writeUTF(req);
		Log.d("DEBUG", "Sent Song List Request");

		ServerSocket auxS = new ServerSocket(this.port); 
		Socket aux = auxS.accept();

		DataInputStream inFromServer = new DataInputStream(aux.getInputStream());
		data = inFromServer.readUTF();

		auxS.close();
		aux.close();

		this.client.close();
		Log.d("DEBUG", "Client Socket Closed");
		return data;
	}

	/**
	 * 
	 * @return True if the client is connected to the server 
	 */
	public boolean connectionReady()
	{
		return client.isConnected();
	}
	
}
