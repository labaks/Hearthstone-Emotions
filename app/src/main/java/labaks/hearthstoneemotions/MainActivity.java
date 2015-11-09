package labaks.hearthstoneemotions;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    ArrayList<Integer> ids = new ArrayList<>();
    public static Sound[] sounds;
    public static int soundAmount = 63;
    private AssetManager mAssetManager;
    private static SoundPool mSoundPool;
    private static int mStreamID;
    public static String[] listHeroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAssetManager = getAssets();
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);

        listHeroes = getResources().getStringArray(R.array.listHeroes);

        generateId();

        sounds = new Sound[soundAmount];
        for (int i = 0; i < soundAmount; i++) {
            sounds[i] = new Sound();
            sounds[i].id = ids.get(i);
            sounds[i].soundName = Integer.toString(sounds[i].id) + ".mp3";
            sounds[i].soundId = loadSound(sounds[i].soundName);
        }

        ListView mainList = (ListView) findViewById(R.id.mainListView);
        mainList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listHeroes));
        mainList.setTextFilterEnabled(true);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Second_Activity.class);

                intent.putExtra("head", position + 1);
                startActivity(intent);
            }
        });
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

    public static int findSound(int sound) {
        int played = 0;
        for (int i = 0; i < MainActivity.soundAmount; i++) {
            if (MainActivity.sounds[i].id == sound) {
                played = MainActivity.sounds[i].soundId;
            }
        }
        return played;
    }

    public static int playSound(int sound) {
        if (sound > 0) {
            mStreamID = MainActivity.mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }

    private void generateId() {
        int heroesAmount = 9;
        int emotionsAmount = 7;
        for (int i = 10; i <= heroesAmount * 10; i += 10) {
            for (int j = 0; j < emotionsAmount; j++) {
                ids.add(i + j);
            }
        }
    }
}
