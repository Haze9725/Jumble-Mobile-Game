package davis.brian.labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;

import java.util.Random;

import android.widget.TextView;
import android.widget.Toast;

public class CoinTossActivity extends AppCompatActivity {

    private String GetCoinToss() {
        Random random = new Random();
        if(random.nextBoolean()) {
            return getString(R.string.coinTossResult1);
        }
        return getString(R.string.coinTossResult2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_toss);
        Log.i("Activity Lifecycle", "onCreate");

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("ExtraName");
        Toast.makeText(getApplicationContext(),
                "This is the extra string that we passed in: " + name,
                Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Activity Lifecycle", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Activity Lifecycle", "onResume");
        TextView coinTossView = (TextView) findViewById(R.id.coinTossView);

        String result = GetCoinToss();
        coinTossView.setText(result);
    }
    @Override
    public void finish() {
        Intent data = new Intent();
        TextView coinTossView = (TextView) findViewById(R.id.coinTossView);
        String responseString = coinTossView.getText().toString();
        data.putExtra("ResponseString", responseString);
        setResult(RESULT_OK, data);
        super.finish();
    }
}


