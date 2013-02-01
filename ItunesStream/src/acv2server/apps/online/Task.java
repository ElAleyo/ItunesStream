package acv2server.apps.online;

import java.io.IOException;

import android.os.AsyncTask;
import android.util.Log;

public class Task extends AsyncTask<String, String , String> {

	private TCPClient s;
	private String temp;

	@Override
	protected String doInBackground(String... params) {

		temp ="crap";
		try {
			s = new TCPClient("192.168.1.9");
			temp = s.getSongList();
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
