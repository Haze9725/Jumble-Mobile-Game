package davis.brian.mobileacw;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListPuzzleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListPuzzleFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INDEX = "index";

    private int mIndex;
    MyViewModel mViewModel;
    View mInflatedView;
    private Button selectGame;
    private Puzzle mPuzzle;

    public int getShownIndex() {
        return mIndex;
    }

    public ListPuzzleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param index Parameter 1 is the index of the content data we want to display
     * @return A new instance of fragment ListPuzzleFragment.
     */
    public static ListPuzzleFragment newInstance(int index) {
        ListPuzzleFragment fragment = new ListPuzzleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIndex = getArguments().getInt(ARG_INDEX);
        }
        mViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        mViewModel.selectItem(mIndex);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(this.getClass().getSimpleName() + " Observer", "onCreateView");

        mInflatedView = inflater.inflate(R.layout.fragment_list_puzzle, container, false);

        selectGame = (Button) mInflatedView.findViewById(R.id.selectGameButton);
        selectGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onPress(v);
            }
        });

        //Create the observer which updates the UI
        final Observer<Puzzle> itemObserver = new Observer<Puzzle>() {
            @Override
            public void onChanged(@Nullable final Puzzle puzzle) {
                mPuzzle = puzzle;
                ImageView image = (ImageView) mInflatedView.findViewById(R.id.imageView_image);
                image.setImageBitmap(puzzle.getFrontImage());
                ImageView image2 = (ImageView) mInflatedView.findViewById(R.id.imageView_image2);
                image2.setImageBitmap(puzzle.getBackImage());
            }
        };

        //Observe the LiveData, passing in this activity as the LifecycleOwner and the observer
        mViewModel.getSelectedItem().observe(this, itemObserver);
        return mInflatedView;
    }

    public void onPress(View v) {
        Intent intent = new Intent(getContext(), Play.class);
        intent.putExtra("puzzleName", mPuzzle.getPuzzleName());
        startActivity(intent);
    }

}