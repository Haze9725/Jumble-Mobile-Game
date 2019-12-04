package davis.brian.labs;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;

public interface VolleyItemImageResponse {

    void onResponse(Bitmap pImage, Item pItem);

    void onError(VolleyError error, String tag);
}
