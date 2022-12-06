package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private void releaseMediaPlayer(){

        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> family = new ArrayList<Word>();

        family.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        family.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        family.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        family.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(this,family,R.color.category_family);

        ListView lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = family.get(position);
                //Toast.makeText(FamilyActivity.this, "clicked", Toast.LENGTH_SHORT).show();

                //Releasing MediaPlayer before creating the MediaPlayer as if user click the button simentineously so for that we need
                //free space for the next MediaPlayer to start releasing the resources is a good practice as it uses the less memory

                releaseMediaPlayer();

                // Creating MediaPlayer
                mediaPlayer = MediaPlayer.create(FamilyActivity.this,word.getmAudio());
                mediaPlayer.start();

                // Releasing the MediaPlayer after the completion of the audio

                mediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        });


        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("FamilyActivity","On OnStop");
        releaseMediaPlayer();
    }
}