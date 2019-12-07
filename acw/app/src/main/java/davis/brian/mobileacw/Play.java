package davis.brian.mobileacw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Play extends AppCompatActivity {

    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS * 4;
    private static final String TAG = "PlayActivity";

    private static String[] tileList;

    private static GestureDetectGridView mGridView;

    private static int mColumnWidth, mColumnHeight;

    public static final String up = "up";
    public static final String down = "down";
    public static final String left = "left";
    public static final String right = "right";
    public static Activity Play = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_play);
        Play = this;

        init();
        scrambleLayout();
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

            for (int i = 0; i < tileList.length; i++) {
                Button tileSection = new Button(context);

            switch(tileList[i]) {
                // First Image
                case "0":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image1);
                    break;
                case "0r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image1);
                    tileSection.setScaleY(-1);
                    break;
                case "1":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image2);
                    break;
                case "1r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image2);
                    tileSection.setScaleY(-1);
                    break;
                case "2":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image3);
                    break;
                case "2r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image3);
                    tileSection.setScaleY(-1);
                    break;
                case "3":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image4);
                    break;
                case "3r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image4);
                    tileSection.setScaleY(-1);
                    break;
                case "4":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image5);
                    break;
                case "4r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image5);
                    tileSection.setScaleY(-1);
                    break;
                case "5":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image6);
                    break;
                case "5r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image6);
                    tileSection.setScaleY(-1);
                    break;
                case "6":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image7);
                    break;
                case "6r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image7);
                    tileSection.setScaleY(-1);
                    break;
                case "7":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image8);
                    break;
                case "7r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image8);
                    tileSection.setScaleY(-1);
                    break;
                case "8":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image9);
                    break;
                case "8r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image9);
                    tileSection.setScaleY(-1);
                    break;
                case "9":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image10);
                    break;
                case "9r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image10);
                    tileSection.setScaleY(-1);
                    break;
                case "10":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image11);
                    break;
                case "10r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image11);
                    tileSection.setScaleY(-1);
                    break;
                case "11":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image12);
                    break;
                case "11r":
                    tileSection.setBackgroundResource(R.drawable.giraffe1_image12);
                    tileSection.setScaleY(-1);
                    break;
                    // Second Image
                case "12":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image1);
                    break;
                case "12r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image1);
                    tileSection.setScaleY(-1);
                    break;
                case "13":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image2);
                    break;
                case "13r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image2);
                    tileSection.setScaleY(-1);
                    break;
                case "14":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image3);
                    break;
                case "14r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image3);
                    tileSection.setScaleY(-1);
                    break;
                case "15":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image4);
                    break;
                case "15r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image4);
                    tileSection.setScaleY(-1);
                    break;
                case "16":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image5);
                    break;
                case "16r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image5);
                    tileSection.setScaleY(-1);
                    break;
                case "17":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image6);
                    break;
                case "17r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image6);
                    tileSection.setScaleY(-1);
                    break;
                case "18":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image7);
                    break;
                case "18r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image7);
                    tileSection.setScaleY(-1);
                    break;
                case "19":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image8);
                    break;
                case "19r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image8);
                    tileSection.setScaleY(-1);
                    break;
                case "20":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image9);
                    break;
                case "20r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image9);
                    tileSection.setScaleY(-1);
                    break;
                case "21":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image10);
                    break;
                case "21r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image10);
                    tileSection.setScaleY(-1);
                    break;
                case "22":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image11);
                    break;
                case "22r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image11);
                    tileSection.setScaleY(-1);
                    break;
                case "23":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image12);
                    break;
                case "23r":
                    tileSection.setBackgroundResource(R.drawable.giraffe2_image12);
                    tileSection.setScaleY(-1);
                    break;
            }

            tiles.add(tileSection);
        }

        mGridView.setAdapter(new CustomGameAdapter(tiles, mColumnWidth, mColumnHeight));

    }

    private void scrambleLayout() {
        tileList[0] = "0";
        tileList[1] = "2r";
        tileList[2] = "1";
        tileList[3] = "3";
        tileList[4] = "17";
        tileList[5] = "6";
        tileList[6] = "19";
        tileList[7] = "22r";
        tileList[8] = "20";
        tileList[9] = "21";
        tileList[10] = "23r";
        tileList[11] = "4";
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

        if (isSolved()) {
            Toast.makeText(context, "YOU WIN!", Toast.LENGTH_LONG).show();
            Play.finish();
        }
    }

    public static void onFlip(Context context, int position) {
        try {
            if (tileList[position].contains("r")) {

                tileList[position] = tileList[position].replace("r", "");
            } else {
                tileList[position] = tileList[position] + "r";
            }
            display(context);

            if (isSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_LONG).show();
                Play.finish();
            }

        } catch (NumberFormatException e) {
            Log.e(TAG, "NumberFormatException caught");
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e(TAG, "OutOfBoundsException caught");
        }
    }

    public static void onPictureSwap(Context context, int position) {
        int tilesetPosition;
        try {
            if (tileList[position].contains("r"))  {
                    tileList[position] = tileList[position].replace("r", "");
                    tilesetPosition = Integer.valueOf(tileList[position]);

                if (tilesetPosition <= 11) {
                    tilesetPosition += 12;
                    tileList[position] = String.valueOf(tilesetPosition);
                    tileList[position] = tileList[position] + "r";

                } else {
                    tilesetPosition -= 12;
                    tileList[position] = String.valueOf(tilesetPosition);
                    tileList[position] = tileList[position] + "r";
                }
            } else {
                tilesetPosition = Integer.valueOf(tileList[position]);

                if (tilesetPosition <= 11) {
                    tilesetPosition += 12;
                    tileList[position] = String.valueOf(tilesetPosition);

                } else {
                    tilesetPosition -= 12;
                    tileList[position] = String.valueOf(tilesetPosition);
                }
            }

            display(context);

            if (isSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_LONG).show();
                Play.finish();
            }

        } catch (NumberFormatException e) {
            Log.e(TAG, "NumberFormatException caught");
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e(TAG, "OutOfBoundsException caught");
            }
    }

    public static void moveTiles(Context context, String direction, int position) {

        //upper-left-corner-tile
        if (position == 0) {
            if (direction.equals(right)) {
                swap(context, position, 1);
            } else if (direction.equals(down)) {
                swap(context, position, COLUMNS);
            } else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //upper-center-tile
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(left)) {
                swap(context, position, -1);
            } else if (direction.equals(down)) {
                swap(context, position, COLUMNS);
            } else if (direction.equals(right)) {
                swap(context, position, 1);
            } else {
                Toast.makeText(context, "invalid Move", Toast.LENGTH_SHORT).show();
            }
            //upper-right-corner-tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(left)) {
                swap(context, position, -1);
            } else if (direction.equals(down)) {
                swap(context, position, COLUMNS);
            } else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //left-side-tile
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS && position % COLUMNS == 0) {
            if (direction.equals(up)) {
                swap(context, position, -COLUMNS);
            } else if (direction.equals(right)) {
                swap(context, position, 1);
            } else if (direction.equals(down)) {
                swap(context, position, COLUMNS);
            } else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //Right-side and bottom-right-corner-tiles
        } else if (position == COLUMNS * 2 - 1 || position == 4 * 3 - 1) {
            if (direction.equals(up)) {
                swap(context, position, - COLUMNS);
            } else if (direction.equals(left)) {
                swap(context, position, -1);
            } else if (direction.equals(down)) {
                //right-corner-tile
                if (position <= DIMENSIONS - COLUMNS - 1) {
                    swap(context, position, COLUMNS);
                } else {
                    Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //Bottom-left-corner-tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) {
                swap(context, position, -COLUMNS);
            } else if (direction.equals(right)) {
                swap(context, position, 1);
            } else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //Bottom-center-tile
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(up)) {
                swap(context, position, -COLUMNS);
            } else if (direction.equals(right)) {
                swap(context, position, 1);
            } else if (direction.equals(left)) {
                swap(context, position, -1);
            } else {
                Toast.makeText(context, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            //center-tile
        } else {
            if (direction.equals(up)) {
                swap(context, position, -COLUMNS);
            } else if (direction.equals(right)) {
                swap(context, position, 1);
            } else if (direction.equals(left)) {
                swap(context, position, -1);
            } else {
                swap(context, position, COLUMNS);
            }
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

    public void gameFinish(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }
}

