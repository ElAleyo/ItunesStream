package acv2server.apps.IS.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This class will manage sending responding on the requests from the phone 
 * @author joseacevedo
 *
 */
public class TCPServer {

	private Socket server;
	private ItunesLibrary il;
	private ServerSocket welcomeSocket;

	public TCPServer(ItunesLibrary il) throws UnknownHostException, IOException
	{
		this.il = il;
		welcomeSocket = new ServerSocket(8888);
		server = welcomeSocket.accept();
	}



	public void start() throws IOException
	{
		DataInputStream inFromClient = new DataInputStream(server.getInputStream());
		
		//wait for client to request something
		String line = inFromClient.readUTF(); 

		if(line.equals("songlist_request"))
		{
			System.out.println("Song request");

			StringBuffer sb = new StringBuffer("");
			for(String str : il.getSongList())
				sb.append(str+"<delim>");

			Socket aux = new Socket( server.getInetAddress() , 8888);
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
}

