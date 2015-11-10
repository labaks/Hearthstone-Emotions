package labaks.hearthstoneemotions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class myArrayAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] emotions, extendedEmotions;

    public myArrayAdapter(Context context, String[] emotions, String[] extendedEmotions) {
        super(context, R.layout.rowlayout, emotions);
        this.context = context;
        this.emotions = emotions;
        this.extendedEmotions = extendedEmotions;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView label = (TextView)rowView.findViewById(R.id.label);
        TextView subItem = (TextView)rowView.findViewById(R.id.subLabel);
        label.setText(emotions[position]);
        subItem.setText(extendedEmotions[position]);
        return rowView;
    }
}
