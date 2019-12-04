package davis.brian.labs;

import android.content.Context;
import android.graphics.Bitmap;
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
import java.util.Collection;
import java.util.Collections;

public class VolleyItemListRetriever implements VolleyJSONObjectResponse,
        VolleyItemImageResponse {

    private String mUrl;
    private MutableLiveData<ArrayList<Item>> mItemsData;
    private ArrayList<Item> mItems;
    private RequestQueue mQueue;

    public VolleyItemListRetriever(String pUrl, Context pContext) {
        mUrl = pUrl;
        mQueue = Volley.newRequestQueue(pContext);
    }

    public LiveData<ArrayList<Item>> getItems() {
        mItemsData = new MutableLiveData<ArrayList<Item>>();
        CustomJSONObjectRequest request = new CustomJSONObjectRequest(Request.Method.GET,
                mUrl, null, "ItemListJSON", this);
        mQueue.add(request.getJSONObjectRequest());
        return mItemsData;
    }

    @Override
    public void onResponse(Bitmap pImage, Item pItem) {
        Log.i("VolleyItemListRetriever", "Image retrieved for:" + pItem.getTitle());
        pItem.setImage(pImage);
        mItemsData.setValue(mItems);
    }

//    @Override
//    public void onError(VolleyError pError, Item pItem) {
//    }

    @Override
    public void onResponse(JSONObject pObject, String pTag) {
        Log.i("VolleyItemListRetriever", pTag);
        mItems = parseJSONResponse(pObject);
        mItemsData.setValue(mItems);
    }

    @Override
    public void onError(VolleyError pError, String pTag) {
        Log.e("VolleyItemListRetriever", pTag);
    }

    private ArrayList<Item> parseJSONResponse(JSONObject pResponse) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            JSONArray itemsArray = pResponse.getJSONArray("items");
            for (int i=0; i < itemsArray.length(); i++) {
                JSONObject itemObject = itemsArray.getJSONObject(i);
                Item item = parseJSONItem(itemObject);
                items.add(item);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        Collections.sort(items, Collections.reverseOrder());
        return items;
    }

    private Item parseJSONItem(JSONObject pItemObject) throws org.json.JSONException {
        String title = pItemObject.getString("title");
        String link = pItemObject.getString("link");
        String date = pItemObject.getString("pubDate");
        String description = pItemObject.getString("description");
        String imageUrl = pItemObject.getString("image");

        Item item = new Item(title, link, date, description, imageUrl);
        CustomItemImageRequest itemImageRequest = new CustomItemImageRequest(
                item.getImageUrl(),
                item,
                this);
        mQueue.add(itemImageRequest.getImageRequest());
        return item;
    }
}
