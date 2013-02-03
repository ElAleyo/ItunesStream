package acv2server.apps.online;

import java.io.IOException;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

public class Task extends AsyncTask<String, String , String> {

	private TCPClient tcpClient;
	private String temp;
	private SharedPreferences prefs;
	
	public Task(SharedPreferences prefs)
	{
		this.prefs = prefs;
	}
	
	@Override
	protected String doInBackground(String... params) {

		temp ="crap";
		try {
			Integer port = prefs.getInt("com_port", 8888);
			Integer responseWaitTime = prefs.getInt("response_wait_time", 5000); 
			tcpClient = new TCPClient( port , responseWaitTime);
			if(tcpClient.connectionReady())
				temp = tcpClient.getSongList();
			
			else
				Log.d("DEBUG", "Connection not ready");
		} catch (IOException e) {

			Log.d("DEBUG", "Exploto: ");
			e.printStackTrace();
		}

		return null;
	}

	protected void onPostExecute(String result) {

		//TODO Update UI		
		if(temp == null)
			Log.d("DEBUG", "Error");
		else
			Log.d("DEBUG", temp);

	}

}
