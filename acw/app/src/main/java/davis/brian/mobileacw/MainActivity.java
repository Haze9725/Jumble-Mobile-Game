package davis.brian.mobileacw;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView imageGrid;
    private ArrayList<Bitmap> bitmaplist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AcceptSSLCerts.accept();

        Log.i("Activity Lifecycle","onCreate");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Activity Lifecycle","onPause");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Activity Lifecycle","onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Activity Lifecycle", "onResume");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Activity Lifecycle", "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Activity Lifecycle", "onDestroy");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Activity Lifecycle", "onRestart");
    }

    public void openAbout(View view) {
        Intent openAboutIntent = new Intent(getApplicationContext(), About.class);
        startActivity(openAboutIntent);
    }
    public void openPlay(View view) {
        Intent openPlayIntent = new Intent(getApplicationContext(), Play.class);
        startActivity(openPlayIntent);
    }
    public void openList(View view) {
        Intent openListActivity = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(openListActivity);
    }
}
