package davis.brian.mobileacw;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Play extends AppCompatActivity {

    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS * 4;

    private static String[] tileList;

    private static GestureDetectGridView mGridView;

    private static int mColumnWidth, mColumnHeight;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE );

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_play);

        init();
        scramble();
        setDimensions();

    }
        private void setDimensions() {
            ViewTreeObserver treeObserver = mGridView.getViewTreeObserver();
            treeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int displayWidth = mGridView.getMeasuredWidth();
                    int displayHeight = mGridView.getMeasuredHeight();

                    int statusBarHeight = getStatusBarHeight(getApplicationContext());
                    int requiredHeight = displayHeight - statusBarHeight;

                    mColumnWidth = displayWidth / COLUMNS;
                    mColumnHeight = requiredHeight / COLUMNS;

                    display(getApplicationContext());
                }
            });
        }

        private int getStatusBarHeight(Context context) {
            int result = 0;
            int resourceId = context.getResources().getIdentifier("status_bar_height",
                    "dimen", "android");

            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
            return result;
        }

        private static void display(Context context) {
        ArrayList<Button> tiles = new ArrayList<>();
            Button tileSection;

        for (int i = 0; i < tileList.length; i++) {
            tileSection = new Button(context);

            if (tileList[i].equals("0"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image1);
            else if (tileList[i].equals("1"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image2);
            else if (tileList[i].equals("2"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image3);
            else if (tileList[i].equals("3"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image4);
            else if (tileList[i].equals("4"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image5);
            else if (tileList[i].equals("5"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image6);
            else if (tileList[i].equals("6"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image7);
            else if (tileList[i].equals("7"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image8);
            else if (tileList[i].equals("8"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image9);
            else if (tileList[i].equals("9"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image10);
            else if (tileList[i].equals("10"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image11);
            else if (tileList[i].equals("11"))
                tileSection.setBackgroundResource(R.drawable.giraffe1_image12);

            tiles.add(tileSection);
        }

        mGridView.setAdapter(new CustomGameAdapter(tiles, mColumnWidth, mColumnHeight));
    }

    private void scramble() {
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

        private void init() {
            mGridView = (GestureDetectGridView) findViewById(R.id.grid);
            mGridView.setNumColumns(COLUMNS);

            tileList = new String[DIMENSIONS];
            for (int i=0; i < DIMENSIONS; i++) {
                tileList[i] = String.valueOf(i);
            }
        }

    private static void swap(Context context, int currentPosition, int swap) {
        String newPosition = tileList[currentPosition + swap];
        tileList[currentPosition + swap] = tileList[currentPosition];
        tileList[currentPosition] = newPosition;
        display(context);

        if (isSolved()) Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
    }

    public static void moveTiles(Context context, String direction, int position) {

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else if (direction.equals(down)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 2 - 1 || position == COLUMNS * 3 - 1) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                swap(context, position, COLUMNS);
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) swap(context, position, -COLUMNS);
            else if (direction.equals(left)) swap(context, position, -1);
            else if (direction.equals(right)) swap(context, position, 1);
            else swap(context, position, COLUMNS);
        }
    }

    private static boolean isSolved() {
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))) {
                solved = true;
            } else {
                solved = false;
                break;
            }
        }

        return solved;
    }

}

