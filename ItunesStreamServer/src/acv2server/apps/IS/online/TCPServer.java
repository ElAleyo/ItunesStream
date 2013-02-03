package acv2server.apps.IS.online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import acv2server.apps.IS.main.ItunesLibrary;

/**
 * This class will manage sending responding on the requests from the phone 
 * @author joseacevedo
 *
 */
public class TCPServer {

	private Socket server;
	private ItunesLibrary il;
	private ServerSocket welcomeSocket;
	private int port;
	private BroadcastReceiver br;

	public TCPServer(ItunesLibrary il, int port) throws UnknownHostException, IOException
	{
		br = new BroadcastReceiver(port);
		//If a connection is made then proceed to establish TCP Connection
		if(br.connectionAttempt())
		{
			this.il = il;
			this.port = port;
			welcomeSocket = new ServerSocket(this.port);
			server = welcomeSocket.accept();
		}
		else{
			System.out.println("No Broadcast Message received");
		}
	}



	public void start() throws IOException
	{
		DataInputStream inFromClient = new DataInputStream(server.getInputStream());

		//wait for client to request something
		String line = inFromClient.readUTF(); 

		if(line.equals("songlist_request"))
		{
			System.out.println("Song List Request Received");

			StringBuffer sb = new StringBuffer("");
			for(String str : il.getSongList())
				sb.append(str+"<delim>");

			Socket aux = new Socket( server.getInetAddress() , this.port);
			DataOutputStream outFromServer = new DataOutputStream(aux.getOutputStream());
			outFromServer.writeUTF(sb.toString());
			aux.close();
		}
	}

	public void close() throws IOException
	{
		server.close();
		welcomeSocket.close();
	}
	
	public void finalize() throws IOException
	{
		System.out.println("Garbage collector close attempt");
		this.close();
	}
}

