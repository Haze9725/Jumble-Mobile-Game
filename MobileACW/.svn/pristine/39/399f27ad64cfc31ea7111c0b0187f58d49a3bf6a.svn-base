package davis.brian.mobileacw;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

public class MyListFragment extends ListFragment {
    int mCurCheckPosition = 0;
    boolean mSingleActivity;
    MyViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);

        //Create the observer which updates the UI
        final Observer<List<Item>> itemObserver = new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable final List<Item> PuzzleIndex) {
                ItemAdapter itemAdapter = new ItemAdapter(getActivity(), mViewModel.getItems().getValue());
                setListAdapter(itemAdapter);
            }
        };
        //Observe the LiveData, passing in this activity as the LifecycleOwner and the observer
        mViewModel.getItems().observe(this, itemObserver);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //setListAdapter(new ArrayAdapter<String>(getActivity(),
        //        android.R.layout.simple_list_item_activated_1,
        //        DummyData.DATA_HEADINGS));

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        View contentFrame = getActivity().findViewById(R.id.content);
        mSingleActivity = contentFrame != null
                && contentFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            //Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mSingleActivity) {
            showContent(mCurCheckPosition);
        } else {
            getListView().setItemChecked(mCurCheckPosition, true);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("curChoice", mCurCheckPosition);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mViewModel.selectItem(position);
        showContent(position);
    }

    void showContent(int index) {
        mCurCheckPosition = index;

        if (mSingleActivity) {
            getListView().setItemChecked(index, true);

            //check what fragment is currently shown, replace if needed
            ListItemFragment content = (ListItemFragment) getFragmentManager()
                    .findFragmentById(R.id.content);
            if (content == null || content.getShownIndex() != index) {
                //make new fragment to show the selection
                content = ListItemFragment.newInstance(index);

                //Execute a transaction, replacing any existing fragment
                // with this one inside the frame
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content, content);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            //Create an intent for starting the DetailsActivity
            Intent intent = new Intent();

            //explicitly set the activity context and class
            //associated with the intent (context, class)
            intent.setClass(getActivity(), ItemActivity.class);

            //pass the current position
            intent.putExtra("index", index);

            startActivity(intent);
        }
    }
}