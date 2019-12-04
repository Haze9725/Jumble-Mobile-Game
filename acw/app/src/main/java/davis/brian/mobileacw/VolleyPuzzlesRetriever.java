package davis.brian.mobileacw;

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

public class VolleyPuzzlesRetriever implements VolleyJSONObjectResponse{

    private String mUrl;
    private Puzzle mPuzzle;

    public VolleyPuzzlesRetriever(Puzzle puzzle) {
        mUrl = "https://www.goparker.com/600096/jumble/puzzles/" + puzzle.getPuzzleIndex();
        mPuzzle = puzzle;
    }

    public LiveData<ArrayList<Puzzle>> getItems() {
        //Cache cache = new DiskBasedCache(getCacheDir());
        CustomJSONObjectRequest request = new CustomJSONObjectRequest(Request.Method.GET,
                mUrl, null, "PuzzleListJSON", this);
        mQueue.add(request.getJSONObjectRequest());
        return mIndexData;
    }

    @Override
    public void onResponse(JSONObject pObject, String pTag) {
        Log.i("VolleyPuzzlesRetriever", pTag);
        mIndexItems = parseJSONResponse(pObject);
        mIndexData.setValue(mIndexItems);
    }

    @Override
    public void onError(VolleyError pError, String pTag) {
        Log.e("VolleyPuzzlesRetriever", pTag);
    }

    private ArrayList<Puzzle> parseJSONResponse(JSONObject pResponse) {
        //ArrayList<Puzzle> puzzles = new ArrayList<>();
        try {
            mPuzzle.setPuzzleName(pResponse.getString("name"));
            mPuzzle.setLayoutJson(pResponse.getString("layout"));

                JSONArray pictureSetArray = pResponse.getJSONArray("PictureSet");
                String[] pictureSet = new String[pictureSetArray.length()];
                for (int i=0; i < pictureSetArray.length(); i++) {
                    pictureSet[i] = pictureSetArray.getString(i);

                    mPuzzle.setPictureSet(pictureSet);
                }

                VolleyLayoutRetriever request = new VolleyLayoutRetriever(
                        mPuzzle);
                request.getItems();

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return puzzles;
    }
}