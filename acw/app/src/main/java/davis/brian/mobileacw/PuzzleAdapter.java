package davis.brian.mobileacw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PuzzleAdapter extends ArrayAdapter<Puzzle> {
    private Context mContext;
    private List<Puzzle> mItemList;

    public PuzzleAdapter(@NonNull Context pContext, ArrayList<Puzzle> pList){
        super(pContext, 0, pList);
        mContext = pContext;
        mItemList = pList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.puzzle_layout, parent, false);
        }
        Puzzle currentItem = mItemList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_title);
        name.setText(currentItem.getPuzzleName());

        TextView release = (TextView) listItem.findViewById(R.id.textView_info);
        release.setText(currentItem.getPuzzleIndex());

        ImageView image = (ImageView) listItem.findViewById(R.id.imageView_puzzleImage);
        image.setImageBitmap(currentItem.getFrontImage());

        return listItem;
    }
}
