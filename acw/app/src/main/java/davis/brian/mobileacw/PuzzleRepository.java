package davis.brian.mobileacw;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

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
        LiveData<Puzzle> transformedItem = Transformations.switchMap(mItems, Puzzle -> {
            MutableLiveData<Puzzle> puzzleData = new MutableLiveData<>();
            Puzzle puzzle = Puzzle.get(pItemIndex);
            puzzleData.setValue(puzzle);
            return puzzleData;
        });

        mSelectedItem = transformedItem;
        return mSelectedItem;
    }
}