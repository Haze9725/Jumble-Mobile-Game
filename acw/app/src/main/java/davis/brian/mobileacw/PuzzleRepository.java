package davis.brian.mobileacw;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class PuzzleRepository {

    private static PuzzleRepository sPuzzleRepository;

    public static Context mApplicationContext;
    private VolleyIndexRetriever mRemoteIndexList;
    //private VolleyPuzzlesRetriever mRemotePuzzleList;

    //private LiveData<ArrayList<Puzzle>> mItems;
    private MediatorLiveData<ArrayList<Puzzle>> mItems;
    private LiveData<Puzzle> mSelectedItem;

    String indexUrl = "https://www.goparker.com/600096/jumble/index.json";

    private PuzzleRepository(Context pApplicationContext) {
        mApplicationContext = pApplicationContext;
        mItems = new MediatorLiveData<>();
        mRemoteIndexList = new VolleyIndexRetriever(
                indexUrl, pApplicationContext);
    }

    public static PuzzleRepository getInstance(Context pApplicationContext) {
        if (sPuzzleRepository == null) {
            sPuzzleRepository = new PuzzleRepository(pApplicationContext);
        }
        return sPuzzleRepository;
    }

    public LiveData<ArrayList<Puzzle>> getItems() {
        LiveData<ArrayList<Puzzle>> remoteData = mRemoteIndexList.getItems();
        //LiveData<ArrayList<Puzzle>> localData = loadIndexLocally("");
        mItems.addSource(remoteData, value -> mItems.setValue(value));
        //mItems.addSource(localData, value -> mItems.setValue(value));
        return mItems;
    }

    public LiveData<Puzzle> getItem(int pItemIndex) {
        LiveData<Puzzle> transformedItem = Transformations.switchMap(mItems, PuzzleIndex -> {
            MutableLiveData<Puzzle> itemData = new MutableLiveData<>();
            Puzzle puzzle = PuzzleIndex.get(pItemIndex);
            itemData.setValue(puzzle);
//            if (!loadImageLocally("puzzle_images", itemData)) {
//                loadImage("puzzle_images", itemData);
//            }
            return itemData;
        });

        mSelectedItem = transformedItem;
        return mSelectedItem;
    }

    public boolean loadImageLocally(String pFilename, MutableLiveData<Puzzle> pPuzzleData) {
        boolean loaded = false;
        ContextWrapper contextWrapper = new ContextWrapper(mApplicationContext);
        File directory = contextWrapper.getDir("puzzleImages", Context.MODE_PRIVATE);
        File file = new File(directory, pFilename);
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
                Puzzle puzzle = pPuzzleData.getValue();
                puzzle.setImage(bitmap);
                pPuzzleData.setValue(puzzle);

                fileInputStream.close();
                loaded = true;
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        } return loaded;
    }
}