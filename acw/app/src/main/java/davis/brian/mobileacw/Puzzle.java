package davis.brian.mobileacw;

import android.graphics.Bitmap;

public class Puzzle implements Comparable<Puzzle> {

    private String mPuzzleIndex;
    private String mPuzzleName;
    private String[] mPictureSet = new String[2];
    private String mLayoutJson;
    private int mLayoutRows;
    private int mLayoutColumns;
    private String[][] mLayoutStart;
    private String mImageUrl = "https://www.goparker.com/600096/jumble/images/";
    private Bitmap mImage;
    private Bitmap[] mPuzzleImageSet1 = new Bitmap[12];
    private Bitmap[] mPuzzleImageSet2 = new Bitmap[12];
    private Bitmap mFrontImage;
    private Bitmap mBackImage;
    private int mIndexValue = 0;

    // layout for the index
    public Puzzle(String pPuzzleIndex) {
        setPuzzleIndex(pPuzzleIndex);
    }

    // Layout for the puzzle
    public Puzzle(String pPuzzleName, String[] pPictureSet, String pLayoutJson) {
        setPuzzleName(pPuzzleName);
        setPictureSet(pPictureSet);
        setLayoutJson(pLayoutJson);
    }
    // Layout for the layout retrieval
    public Puzzle(int pLayoutRows, int pLayoutColumns, String[][] pLayoutStart) {
        setLayoutRows(pLayoutRows);
        setLayoutColumns(pLayoutColumns);
        setLayoutStart(pLayoutStart);
    }

    public int getIndexValue() { return mIndexValue; }
    public void setIndexValue(int pIndexValue) { mIndexValue = pIndexValue;}

    public int getLayoutRows() { return mLayoutRows; }
    public void setLayoutRows(int pLayoutRows) { mLayoutRows = pLayoutRows;}
    public int getLayoutColumns() {return mLayoutColumns; }
    public void setLayoutColumns(int pLayoutColumns) { mLayoutColumns = pLayoutColumns;}
    public String[][] getLayoutStart() { return mLayoutStart; }
    public void setLayoutStart(String[][] pLayoutStart) {mLayoutStart = pLayoutStart; }

    public Bitmap getFrontImage() { return mFrontImage; }
    public void setFrontImage(Bitmap pFrontImage) { mFrontImage = pFrontImage; }
    public Bitmap getBackImage() { return mBackImage; }
    public void setBackImage(Bitmap pBackImage) { mBackImage = pBackImage; }

    public Bitmap getImage() { return mImage; }
    public void setImage(Bitmap pImage) { mImage = pImage; }
    public String getImageUrl() { return mImageUrl; }
    public void setImageUrl(String pImageUrl) { mImageUrl = pImageUrl; }
    public Bitmap[] getPuzzleImageSet1() { return mPuzzleImageSet1; }
    public void setPuzzleImageSet1(Bitmap[] pPuzzleImageSet1) { mPuzzleImageSet1 = pPuzzleImageSet1; }
    public Bitmap[] getPuzzleImageSet2() { return mPuzzleImageSet2; }
    public void setPuzzleImageSet2(Bitmap[] pPuzzleImageSet2) { mPuzzleImageSet2 = pPuzzleImageSet2; }

    public String getPuzzleName() { return mPuzzleName; }
    public void setPuzzleName(String pPuzzleName) { mPuzzleName = pPuzzleName;}
    public String[] getPictureSet() { return mPictureSet; }
    public void setPictureSet(String[] pPictureSet) { mPictureSet = pPictureSet;}
    public String getLayoutJson() { return mLayoutJson; }
    public void setLayoutJson(String pLayoutJson) { mLayoutJson = pLayoutJson;}

    public String getPuzzleIndex() { return mPuzzleIndex; }
    public void setPuzzleIndex(String pPuzzleIndex) { mPuzzleIndex = pPuzzleIndex;}

    @Override
    public int compareTo(Puzzle o) {
        return this.getPuzzleIndex().compareTo(o.getPuzzleIndex());
    }

}

