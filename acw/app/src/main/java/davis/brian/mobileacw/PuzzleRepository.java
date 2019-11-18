package davis.brian.mobileacw;

import android.content.Context;
import android.content.ContextWrapper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PuzzleRepository {
    private static PuzzleRepository sPuzzleRepository;

    private Context mApplicationContext;

    private LiveData<ArrayList<Item>> mItems;
    private LiveData<Item> mSelectedItem;

    private PuzzleRepository(Context pApplicationContext) {
        this.mApplicationContext = pApplicationContext;
    }

    public static PuzzleRepository getInstance(Context pApplicationContext) {
        if (sPuzzleRepository == null) {
            sPuzzleRepository = new PuzzleRepository(pApplicationContext);
        }
        return sPuzzleRepository;
    }

    public LiveData<ArrayList<Item>> loadItemsFromJSON() {
        RequestQueue queue = Volley.newRequestQueue(mApplicationContext);
        String url = "https://www.goparker.com/600096/jumble/index.json";
        final MutableLiveData<ArrayList<Item>> mutableItems = new MutableLiveData<>();

        // Request a jsonObject response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<Item> PuzzleIndex = parseJSONResponse(response);
                        mutableItems.setValue(PuzzleIndex);
                        mItems = mutableItems;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorResponse = "That didn't work!";
                    }
                });

        //Add the request to the RequestQueue
        queue.add(jsonObjectRequest);

        return mutableItems;
    }

    private ArrayList<Item> parseJSONResponse(JSONObject pResponse) {
        ArrayList<Item> puzzles = new ArrayList<>();
        try {
            JSONArray puzzleArray = pResponse.getJSONArray("PuzzleIndex");
            for (int i=0; i < puzzleArray.length(); i++) {
                String puzzleObject = puzzleArray.getString(i);
                Item puzzle = parseJSONItem(puzzleObject);
                puzzles.add(puzzle);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return puzzles;
    }
    private Item parseJSONItem(String pPuzzleObject) throws org.json.JSONException {

        //String necking = pPuzzleObject("necking.json");
        //String piping = pPuzzleObject.getString("piping.json");
        //String tractorFront = pPuzzleObject.getString("tractorFront.json");

        Item puzzle = new Item(pPuzzleObject, pPuzzleObject, pPuzzleObject);
        return puzzle;
    }

    public LiveData<ArrayList<Item>> getItems() {
        if(mItems==null) {
            mItems = loadItemsFromJSON();
        }
        return mItems;
    }

    public LiveData<Item> getItem(int pItemIndex) {

        LiveData<Item> transformedItem = Transformations.switchMap(mItems, PuzzleIndex -> {
            MutableLiveData<Item> itemData = new MutableLiveData<>();
            Item puzzle = PuzzleIndex.get(pItemIndex);
            itemData.setValue(puzzle);
            return itemData;
        });

        mSelectedItem = transformedItem;
        return mSelectedItem;
    }


    public void saveIndexLocally(JSONObject pIndexObject, String pFilename) {
        ContextWrapper contextWrapper = new ContextWrapper(mApplicationContext);
        OutputStreamWriter
    }
}