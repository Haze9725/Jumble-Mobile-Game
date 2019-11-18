package davis.brian.mobileacw;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Play extends AppCompatActivity {

    GridView gridView;
    Adapter adapter;
    ArrayList<String> listPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

            gridView = findViewById(R.id.gridview);
            ImageAdapter gridAdapter = (new ImageAdapter(this));
            gridView.setAdapter(gridAdapter);
        }
    }

