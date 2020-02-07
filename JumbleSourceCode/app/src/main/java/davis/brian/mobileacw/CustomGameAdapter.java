package davis.brian.mobileacw;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import java.util.ArrayList;

public class CustomGameAdapter extends BaseAdapter {
    private ArrayList<ImageButton> mButtons;
    private int mColumnWidth, mColumnHeight;

    public CustomGameAdapter(ArrayList<ImageButton> buttons, int columnWidth, int columnHeight) {
        mButtons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
    }

    @Override
    public int getCount() {
        return mButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return (Object) mButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageButton button;

        if (convertView == null) {
            button = mButtons.get(position);
        } else {
            button = (ImageButton) convertView;
        }

        android.widget.AbsListView.LayoutParams params
                = new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);
        button.setLayoutParams(params);

        return button;
    }
}
