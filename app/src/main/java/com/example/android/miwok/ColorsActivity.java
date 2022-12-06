package com.example.android.miwok;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private void releaseMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    MediaPlayer.OnCompletionListener mComplrtion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> color = new ArrayList<Word>();

        color.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        color.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        color.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        color.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        color.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        color.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        color.add(new Word("dusty yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        color.add(new Word("mustard yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordAdapter adapter = new WordAdapter(this,color,R.color.category_colors);

        ListView lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = color.get(position);

                // Releasing MediaPlayer before the Creations of MediaPlayer so that if user click the many buttons continuously
                // So for next MediaPlayer we have free Resources of MediaPlayer

                releaseMediaPlayer();


                mediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getmAudio());
                mediaPlayer.start();

                // After completions of the audio file we need to release the mediaplayer so that it do not use the memory
                mediaPlayer.setOnCompletionListener(mComplrtion);

            }
        });

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
