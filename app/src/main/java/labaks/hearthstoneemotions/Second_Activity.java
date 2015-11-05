package labaks.hearthstoneemotions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Second_Activity extends ActionBarActivity {

    private int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String[] listEmotions = loadListEmotions();

        ListView secondList = (ListView) findViewById(R.id.secondListView);
        secondList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listEmotions));
        secondList.setTextFilterEnabled(true);

        secondList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = getIntent();
                sound = intent.getIntExtra("head", 0) * 10 + position;
                int played = MainActivity.findSound(sound);
                MainActivity.playSound(played);
            }
        });
    }

    private String[] loadListEmotions() {
        return new String[]{getResources().getString(R.string.start), getResources().getString(R.string.thanks), getResources().getString(R.string.sorry), getResources().getString(R.string.well_played),
                getResources().getString(R.string.oops), getResources().getString(R.string.greetings), getResources().getString(R.string.threaten)};
    }
}
