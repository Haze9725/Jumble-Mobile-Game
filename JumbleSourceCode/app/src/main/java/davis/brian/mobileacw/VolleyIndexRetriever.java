package davis.brian.mobileacw;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyIndexRetriever implements VolleyJSONObjectResponse{

    private String mUrl;
    public static MutableLiveData<ArrayList<Puzzle>> mIndexData;
    public static ArrayList<Puzzle> mIndexItems;
    public static RequestQueue mQueue;
    public static ArrayList<Puzzle> puzzles = new ArrayList<>();

    public VolleyIndexRetriever(String pUrl, Context pContext) {
        mUrl = pUrl;
        mQueue = Volley.newRequestQueue(pContext);
    }

    public LiveData<ArrayList<Puzzle>> getItems() {
        //Cache cache = new DiskBasedCache(getCacheDir());
        mIndexData = new MutableLiveData<ArrayList<Puzzle>>();
        CustomJSONObjectRequest request = new CustomJSONObjectRequest(Request.Method.GET,
                mUrl, null, "IndexListJSON", this);
        mQueue.add(request.getJSONObjectRequest());
        return mIndexData;
    }

    @Override
    public void onResponse(JSONObject pObject, String pTag) {
        Log.i("VolleyIndexRetriever", pTag);
        mIndexItems = parseJSONResponse(pObject);
        mIndexData.setValue(mIndexItems);
    }

    @Override
    public void onError(VolleyError pError, String pTag) {
        Log.e("VolleyIndexRetriever", pTag);
    }

    private ArrayList<Puzzle> parseJSONResponse(JSONObject pResponse) {
        //ArrayList<Puzzle> puzzles = new ArrayList<>();
        try {
            JSONArray puzzleArray = pResponse.getJSONArray("PuzzleIndex");
            for (int i=0; i < puzzleArray.length(); i++) {
                String puzzleIndex = puzzleArray.getString(i);
                Puzzle puzzleObject = new Puzzle(puzzleIndex);
                VolleyPuzzlesRetriever request = new VolleyPuzzlesRetriever(
                        puzzleObject);
                request.getItems();
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return puzzles;
    }
}
