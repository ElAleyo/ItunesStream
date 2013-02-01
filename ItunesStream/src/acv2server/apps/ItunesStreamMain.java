package acv2server.apps;

import acv2server.apps.online.Task;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ItunesStreamMain extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itunes_stream_main);
        
        AsyncTask<String, String, String> t = new Task(this.getApplicationContext());
        t.execute("none");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_itunes_stream_main, menu);
        return true;
    }
}
