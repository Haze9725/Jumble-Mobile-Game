package davis.brian.mobileacw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PuzzleAdapter extends ArrayAdapter<Puzzle> {
    private Context mContext;
    private List<Puzzle> mPuzzleList;

    public PuzzleAdapter(@NonNull Context pContext, ArrayList<Puzzle> pList){
        super(pContext, 0, pList);
        mContext = pContext;
        mPuzzleList = pList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listPuzzle = convertView;
        if (listPuzzle == null) {
            listPuzzle = LayoutInflater.from(mContext).inflate(R.layout.puzzle_layout, parent, false);
        }
        Puzzle currentPuzzle = mPuzzleList.get(position);

        TextView name = (TextView) listPuzzle.findViewById(R.id.textView_title);
        name.setText(currentPuzzle.getPuzzleName());

        TextView scoreText = (TextView) listPuzzle.findViewById(R.id.textView_score);
        scoreText.setText(currentPuzzle.getHighScoreText());

        TextView release = (TextView) listPuzzle.findViewById(R.id.textView_info);
        release.setText(String.valueOf(currentPuzzle.getHighScore()));

        return listPuzzle;
    }
}
