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
	public TCPClient(String ip ) throws UnknownHostException, IOException
	{
		client = new Socket(ip, 8888);
	}


	public String getSongList() throws IOException
	{
		DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
		String req = "songlist_request";
		outToServer.writeUTF(req);
		Log.d("DEBUG", "Sent request");

		ServerSocket auxS = new ServerSocket(8888); 
		Socket aux = auxS.accept();
		
		DataInputStream inFromServer = new DataInputStream(aux.getInputStream());
		data = inFromServer.readUTF();

		auxS.close();
		aux.close();
		
		this.client.close();
		Log.d("DEBUG", "Closed");
		return data;
	}
}
