package davis.brian.mobileacw;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class CustomPuzzleImageRequest implements Response.Listener<Bitmap>,
        Response.ErrorListener {

    private VolleyItemImageResponse mVolleyPuzzleImageResponse;
    private Puzzle mPuzzle;
    private ImageRequest mImageRequest;
    //private int mIndexValue;


    public CustomPuzzleImageRequest(String pUrl,
                                    Puzzle pPuzzle,
                                    VolleyItemImageResponse pVolleyPuzzleImageResponse) {
        mVolleyPuzzleImageResponse = pVolleyPuzzleImageResponse;
        mPuzzle = pPuzzle;
        mImageRequest = new ImageRequest(
                pUrl,
                this,
                0,
                0,
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.RGB_565,
                this);

    }

    @Override
    public void onErrorResponse(VolleyError pError) {
        mVolleyPuzzleImageResponse.onError(pError, mPuzzle.getPuzzleName());
    }

    @Override
    public void onResponse(Bitmap pResponse) {
        mVolleyPuzzleImageResponse.onResponse(pResponse, mPuzzle);
    }

    public ImageRequest getImageRequest() { return mImageRequest; }
}
