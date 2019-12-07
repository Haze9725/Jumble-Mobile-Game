package davis.brian.mobileacw;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    

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
        // change here to return to previous iteration of Play.class
        Intent openPlayIntent = new Intent(getApplicationContext(), Play.class);
        startActivity(openPlayIntent);
    }
    public void openList(View view) {
        Intent openListActivity = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(openListActivity);
    }
    public void openScores(View view) {
        Intent openHighScores = new Intent(getApplicationContext(), ScoresActivity.class);
        startActivity(openHighScores);
    }
}
