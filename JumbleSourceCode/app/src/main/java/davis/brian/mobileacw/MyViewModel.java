package davis.brian.mobileacw;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

import static davis.brian.mobileacw.VolleyIndexRetriever.mIndexData;

public class MyViewModel extends AndroidViewModel {
    private LiveData<ArrayList<Puzzle>> mItems;
    private LiveData<Puzzle> mSelectedItem;
    private PuzzleRepository mPuzzleRepository;
    private int mSelectedIndex;

    public MyViewModel(@NonNull Application pApplication) {

        super(pApplication);
        mPuzzleRepository = PuzzleRepository.getInstance(getApplication());
    }

    public LiveData<ArrayList<Puzzle>> getItems() {
        if (mIndexData != null) {
            return mIndexData;
        }
        if (mItems == null) {
            mItems = mPuzzleRepository.getItems();
        }
        return mItems;
    }

    public LiveData<Puzzle> getItem(int pItemIndex) {
        return mPuzzleRepository.getItem(pItemIndex);
    }

    public void selectItem(int pIndex) {
        if (pIndex != mSelectedIndex || mSelectedItem == null) {
            mSelectedIndex = pIndex;
            mSelectedItem = getItem(mSelectedIndex);
        }
    }

    public LiveData<Puzzle> getSelectedItem() {
        return mSelectedItem;
    }
}
