package labaks.hearthstoneemotions;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends ListActivity {

    public static String[] listHeroes;
    public static int[] backs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listHeroes = getResources().getStringArray(R.array.listHeroes);
        loadBackgrounds();

        firstPageArrayAdapter adapter = new firstPageArrayAdapter(this, listHeroes, backs);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Second_Activity.class);
        intent.putExtra("head", position);
        startActivity(intent);
    }

    private void loadBackgrounds() {
        backs = new int[9];
        for (int i = 0; i < 9; i++) {
            backs[i] = getBaseContext().getResources().getIdentifier("back_" + Integer.toString(i + 1), "drawable", getBaseContext().getPackageName());
        }
    }
}
