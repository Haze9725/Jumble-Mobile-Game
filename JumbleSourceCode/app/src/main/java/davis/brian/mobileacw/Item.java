package davis.brian.mobileacw;

import org.json.JSONObject;

public class Item extends JSONObject {
    //private JSONObject mPuzzleIndex;
    private String mNecking;
    private String mPiping;
    private String mTractorFront;

    public Item(String pNecking, String pPiping, String pTractorFront) {

        setNecking(pNecking);
        setPiping(pPiping);
        setTractorFront(pTractorFront);
    }

    //public JSONObject getPuzzleIndex() { return mPuzzleIndex; }
    //public void setPuzzleIndex(JSONObject pPuzzleIndex) { mPuzzleIndex = pPuzzleIndex;}
    public String getNecking() { return mNecking; }
    public void setNecking(String pNecking) { mNecking = pNecking;}
    public String getPiping() { return mPiping; }
    public void setPiping(String pPiping) { mPiping = pPiping;}
    public String getTractorFront() { return mTractorFront; }
    public void setTractorFront(String pTractorFront) { mTractorFront = pTractorFront;}
}

