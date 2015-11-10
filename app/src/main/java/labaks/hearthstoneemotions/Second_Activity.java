package labaks.hearthstoneemotions;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Second_Activity extends ListActivity {

    private int sound;
    private int chosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        chosen = intent.getIntExtra("head", 0);
//        TextView title = (TextView)getListView().findViewById(R.id.title);
//        title.setText(MainActivity.listHeroes[chosen - 1]);

        String[] listEmotions = getResources().getStringArray(R.array.listEmotions);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listEmotions);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View view, int position, long id) {
        sound = chosen * 10 + position;
        int played = MainActivity.findSound(sound);
        MainActivity.playSound(played);
    }

}
