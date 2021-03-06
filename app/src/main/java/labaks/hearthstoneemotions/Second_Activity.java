package labaks.hearthstoneemotions;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Second_Activity extends ListActivity {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private Sound[] sounds;
    private AssetManager mAssetManager;
    private SoundPool mSoundPool;
    private int mStreamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int chosen = intent.getIntExtra("head", 0) + 1;

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

        ListView listView = getListView();
        LayoutInflater inflater = getLayoutInflater();
        View header = inflater.inflate(R.layout.listview_header, listView, false);
        TextView title = (TextView) header.findViewById(R.id.title);
        title.setText(MainActivity.listHeroes[chosen - 1]);
        title.setBackgroundDrawable(getResources().getDrawable(MainActivity.backs[chosen - 1]));
        listView.addHeaderView(header, null, false);
        listView.setBackgroundColor(Color.parseColor("#433123"));

        listView.setOnTouchListener(new View.OnTouchListener() {
            public GestureDetector gestureDetector = new GestureDetector(new GestureListener());

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }

            final class GestureListener extends GestureDetector.SimpleOnGestureListener {

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            finish();
                        }
                    }
                    return true;
                }
            }
        });

        myArrayAdapter adapter = new myArrayAdapter(this, listEmotions, extendedEmotions);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View view, int position, long id) {
        int played = sounds[position - 1].fileId;
        playSound(played);
    }

    private String[] setExtEmotionsList(int chosenHero) {
        return getResources().getStringArray(
                getBaseContext().getResources().getIdentifier("hero_" + chosenHero, "array", getBaseContext().getPackageName()));
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
