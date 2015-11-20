package labaks.hearthstoneemotions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class firstPageArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] heroes;
    private final int[] backs;

    public firstPageArrayAdapter(Context context, String[] heroes, int[] backs) {
        super(context, R.layout.first_page_row_layout, heroes);
        this.context = context;
        this.heroes = heroes;
        this.backs = backs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.first_page_row_layout, parent, false);
        TextView label = (TextView) rowView.findViewById(R.id.label);
        label.setText(heroes[position]);
        label.setBackgroundDrawable(context.getResources().getDrawable(backs[position]));
        return rowView;
    }

}
