package davis.brian.labs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class MyViewModel extends AndroidViewModel {
    private LiveData<ArrayList<Item>> mItems;
    private LiveData<Item> mSelectedItem;
    private ItemsRepository mItemRepository;
    private int mSelectedIndex;

    public MyViewModel(@NonNull Application pApplication) {

        super(pApplication);
        mItemRepository = ItemsRepository.getInstance(getApplication());
    }

    private void generateItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("First", "Link!", "01/11/19", "first blah"));
        items.add(new Item("Second", "Link!", "02/11/19", "blah blah"));
        items.add(new Item("Third", "Link!", "03/11/19", "extra blah"));
        items.add(new Item("Fourth", "Link!", "04/11/19", "i hate this blah"));
        items.add(new Item("Fifth", "Link!", "05/11/19", "does it fry, oh aye"));
        //mItems.setValue(items);
    }

    public LiveData<ArrayList<Item>> getItems() {
        if (mItems == null) {
            mItems = mItemRepository.getItems();
        }
        return mItems;
    }

    public LiveData<Item> getItem(int pItemIndex) {
        return mItemRepository.getItem(pItemIndex);
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
