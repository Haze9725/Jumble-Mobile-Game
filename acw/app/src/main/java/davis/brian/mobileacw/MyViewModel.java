package davis.brian.mobileacw;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public class MyViewModel extends AndroidViewModel {
    private LiveData<ArrayList<Item>> mItems;
    private LiveData<Item> mSelectedItem;
    private PuzzleRepository mPuzzleRepository;
    private int mSelectedIndex;

    public MyViewModel(@NonNull Application pApplication) {

        super(pApplication);
        mPuzzleRepository = PuzzleRepository.getInstance(getApplication());
    }

    public LiveData<ArrayList<Item>> getItems() {
        if (mItems == null) {
            mItems = mPuzzleRepository.getItems();
        }
        return mItems;
    }

    public LiveData<Item> getItem(int pItemIndex) {
        return mPuzzleRepository.getItem(pItemIndex);
    }

    public void selectItem(int pIndex) {
        if (pIndex != mSelectedIndex || mSelectedItem == null) {
            mSelectedIndex = pIndex;
            mSelectedItem = getItem(mSelectedIndex);
        }

        //Item selectedItem = mItems.getValue().get(mSelectedIndex);
        //mSelectedItem = new MutableLiveData<Item>();
        //mSelectedItem.setValue(selectedItem);
    }

    public LiveData<Item> getSelectedItem() {
        //if (mSelectedItem == null) {
        //    mSelectedItem = new MutableLiveData<Item>();
        //    selectItem(mSelectedIndex);
        //}
        return mSelectedItem;
    }
}