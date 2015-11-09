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
        Intent intent = getIntent();
        final int chosen = intent.getIntExtra("head", 0);
        getSupportActionBar().setTitle(MainActivity.listHeroes[chosen - 1]);

        String[] listEmotions = getResources().getStringArray(R.array.listEmotions);

        ListView secondList = (ListView) findViewById(R.id.secondListView);
        secondList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listEmotions));
        secondList.setTextFilterEnabled(true);

        secondList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                sound = chosen * 10 + position;
                int played = MainActivity.findSound(sound);
                MainActivity.playSound(played);
            }
        });
    }

}
