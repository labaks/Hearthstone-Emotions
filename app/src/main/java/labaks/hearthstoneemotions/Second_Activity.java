package labaks.hearthstoneemotions;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Second_Activity extends ListActivity {

    private Sound[] sounds;
    private AssetManager mAssetManager;
    private SoundPool mSoundPool;
    private int mStreamID;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int chosen = intent.getIntExtra("head", 0) + 1;
//        TextView title = (TextView)getListView().findViewById(R.id.title);
//        title.setText(MainActivity.listHeroes[chosen - 1]);

        String[] listEmotions = getResources().getStringArray(R.array.listEmotions);
        String[] extendedEmotions = setExtEmotionsList(chosen);

        mAssetManager = getAssets();
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);

        int soundAmount = 7;
        sounds = new Sound[soundAmount];
        for (int i = 0; i < soundAmount; i++) {
            sounds[i] = new Sound();
            sounds[i].soundName = Integer.toString(chosen) + Integer.toString(i) + ".mp3";
            sounds[i].fileId = loadSound(sounds[i].soundName);
        }

        listView = getListView();
        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.listview_header, listView, false);
        TextView title = (TextView)header.findViewById(R.id.title);
        title.setText(MainActivity.listHeroes[chosen - 1]);
        listView.addHeaderView(header, null, false);

        myArrayAdapter adapter = new myArrayAdapter(this, listEmotions, extendedEmotions);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View view, int position, long id) {
        int played = sounds[position].fileId;
        playSound(played);
    }

    private String[] setExtEmotionsList(int chosenHero) {
        String[] extEmotions = getResources().getStringArray(
                getBaseContext().getResources().getIdentifier("hero_" + chosenHero, "array", getBaseContext().getPackageName()));
        return extEmotions;
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_sound), Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    private int playSound(int sound) {
        if (sound > 0) {
            mStreamID = mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }

}
