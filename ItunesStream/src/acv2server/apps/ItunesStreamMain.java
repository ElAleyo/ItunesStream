package acv2server.apps;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ItunesStreamMain extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itunes_stream_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_itunes_stream_main, menu);
        return true;
    }
}
