package acv2server.apps.online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class TCPClient {

	private Socket client;
	private String data;
	private Context context;

	public TCPClient( Context context ) throws UnknownHostException, IOException
	{
		this.context = context;
		this.availableIPs();
		client = new Socket("192.168.1.9", 8888);

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

	private void availableIPs()  
	{
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ip = wifiInfo.getIpAddress();

		String ipString = String.format(Locale.US,
				"%d.%d.%d.%d",
				(ip & 0xff),
				(ip >> 8 & 0xff),
				(ip >> 16 & 0xff),
				(ip >> 24 & 0xff));

		System.out.println(ipString);

	}

}
