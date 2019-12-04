package davis.brian.mobileacw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

import static davis.brian.mobileacw.PuzzleRepository.mApplicationContext;


public class ImageAdapter extends BaseAdapter {

    private String[] puzzleGame;

    public ImageAdapter(Context pContext) {
        mApplicationContext = pContext;

        try {

            puzzleGame = pContext.getAssets().list("giraffe 1");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return puzzleGame.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img;

        if (convertView == null) {

            img = new ImageView(mApplicationContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            img.setLayoutParams(new GridView.LayoutParams(params));
            img.setScaleType(ImageView.ScaleType.CENTER);
            img.setPadding(8, 8, 8, 8);

        } else {

            img = (ImageView) convertView;
        }
        try {

            InputStream ims = mApplicationContext.getAssets().open("giraffe 1/" + puzzleGame[position]);

            //Drawable d = Drawable.createFromStream(ims, null);
            Bitmap bitmap = BitmapFactory.decodeStream(ims);
            img.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}