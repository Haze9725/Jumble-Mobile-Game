package davis.brian.mobileacw;

import android.graphics.Bitmap;

import com.android.volley.VolleyError;

public interface VolleyItemImageResponse {

    void onResponse(Bitmap pImage, Puzzle pItem);

    void onError(VolleyError error, String tag);
}
