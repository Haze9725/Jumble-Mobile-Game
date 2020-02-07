package davis.brian.mobileacw;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static davis.brian.mobileacw.VolleyIndexRetriever.mIndexData;
import static davis.brian.mobileacw.VolleyIndexRetriever.mIndexItems;
import static davis.brian.mobileacw.VolleyIndexRetriever.mQueue;
import static davis.brian.mobileacw.VolleyIndexRetriever.puzzles;
import static java.lang.Integer.parseInt;

public class VolleyLayoutRetriever implements VolleyJSONObjectResponse,
        VolleyItemImageResponse {

    private String mUrl;
    private Puzzle mPuzzle;
    private Integer count = 1;
    private String pictureUrl = "https://www.goparker.com/600096/jumble/images/";
    public Bitmap[] puzzleImageSetFront = new Bitmap[12];
    public Bitmap[] puzzleImageSetBack = new Bitmap[12];
    boolean allImagesSet1 = false;
    boolean allImagesSet2 = false;
    private int indexValue = 0;
    private int p = 0;


    public VolleyLayoutRetriever(Puzzle puzzle) {
        mUrl = "https://www.goparker.com/600096/jumble/layouts/" + puzzle.getLayoutJson();
        mPuzzle = puzzle;
    }

    public LiveData<ArrayList<Puzzle>> getItems() {
        //Cache cache = new DiskBasedCache(getCacheDir());
        CustomJSONObjectRequest request = new CustomJSONObjectRequest(Request.Method.GET,
                mUrl, null, "LayoutJSON", this);
        mQueue.add(request.getJSONObjectRequest());
        return mIndexData;
    }

    @Override
    public void onResponse(Bitmap pImage, Puzzle pPuzzle) {
        Log.i("VolleyLayoutRetriever", "Image retrieved for:" + pPuzzle.getPuzzleName());

            pPuzzle.setImage(pImage);
            // Get the complete first set of 12 images
            if (indexValue <= 11) {
                puzzleImageSetFront[indexValue] = pImage;
                mPuzzle.setPuzzleImageSet1(puzzleImageSetFront);
            }
            // Get the complete second set of 12 images
            if (indexValue >= 12)
            {   if (indexValue <=23 ){

                puzzleImageSetBack[p] = pImage;
                mPuzzle.setPuzzleImageSet2(puzzleImageSetBack);
                p++;
                }
            }

            indexValue++;
            mPuzzle.setIndexValue(indexValue);

            // Get the back image and assign it
            if (mPuzzle.getIndexValue() == 25){
                mPuzzle.setBackImage(pImage);
            }

            // Get the front image and assign it
            if (mPuzzle.getIndexValue() == 26) {
                mPuzzle.setFrontImage(pImage);
                indexValue = 0;
                //p = 0;
                mPuzzle.setIndexValue(indexValue);
            }
            mIndexData.setValue(mIndexItems);

    }

    @Override
    public void onResponse(JSONObject pObject, String pTag) {
        Log.i("VolleyLayoutRetriever", pTag);
        mIndexItems = parseJSONResponse(pObject);
        mIndexData.setValue(mIndexItems);

    }

    @Override
    public void onError(VolleyError pError, String pTag) {
        Log.e("VolleyLayoutRetriever", pTag);
    }

    private ArrayList<Puzzle> parseJSONResponse(JSONObject pResponse) {
        try {
            String layoutRows = pResponse.getString("rows");
            mPuzzle.setLayoutRows(parseInt(layoutRows));

            String layoutColumns = pResponse.getString("columns");
            mPuzzle.setLayoutColumns(parseInt(layoutColumns));

            JSONArray layoutArray = pResponse.getJSONArray("layout");
            String[][] layout = new String[4][3];
            for (int i = 0; i < layout.length; i++) {
                for (int j = 0; j < layout[i].length; j++) {
                    layout[i][j] = layoutArray.getJSONArray(i).getString(j);
                }

                mPuzzle.setLayoutStart(layout);
            }
            puzzles.add(mPuzzle);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getImages();
        getCompleteImages();
        return puzzles;
    }

    public void getImages() {
        String[] pictureSet = mPuzzle.getPictureSet();

        for (int k = 1; k <= 12; k++) {
            CustomPuzzleImageRequest imageRequest1 = new CustomPuzzleImageRequest(
                    mPuzzle.getImageUrl() + pictureSet[0] + "/" + count + ".png",
                    mPuzzle,
                    this);
            mQueue.add(imageRequest1.getImageRequest());
            Log.i("PictureSet 1", "image retrieved: " + count);
            if (count == 12) {
                allImagesSet1 = true;
                break;
            } else {
                count++;
            }
        }
        count = 1;

        for (int m = 1; m <= 12; m++) {
            CustomPuzzleImageRequest imageRequest2 = new CustomPuzzleImageRequest(
                    mPuzzle.getImageUrl() + pictureSet[1] + "/" + count + ".png",
                    mPuzzle,
                    this);
            mQueue.add(imageRequest2.getImageRequest());
            Log.i("PictureSet 2", "image retrieved: " + count);

            if (count == 12) {
                allImagesSet2 = true;
                break;
            } else {
                count++;
            }
        }
    }
        public void getCompleteImages() {
            String[] pictureSet = mPuzzle.getPictureSet();

            CustomPuzzleImageRequest imageRequest4 = new CustomPuzzleImageRequest(
                    mPuzzle.getImageUrl() + pictureSet[1] + "/" + "complete.png",
                    mPuzzle,
                    this);
            mQueue.add(imageRequest4.getImageRequest());
            Log.i("VolleyLayoutRetriever", "got complete back image");

            CustomPuzzleImageRequest imageRequest3 = new CustomPuzzleImageRequest(
                    mPuzzle.getImageUrl() + pictureSet[0] + "/" + "complete.png",
                    mPuzzle,
                    this);
            mQueue.add(imageRequest3.getImageRequest());
            Log.i("VolleyLayoutRetriever", "got complete front image");
    }
}
