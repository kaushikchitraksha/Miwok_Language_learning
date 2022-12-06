package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    private void releaseMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();

            mediaPlayer = null;
        }
    }

    MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> phrase = new ArrayList<Word>();

        phrase.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        phrase.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        phrase.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        phrase.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        phrase.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        phrase.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        phrase.add(new Word("Yes, I’m coming","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        phrase.add(new Word("I’m coming","әәnәm",R.raw.phrase_im_coming));
        phrase.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        phrase.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

        WordAdapter adapter = new WordAdapter(this,phrase,R.color.category_phrases);

        ListView lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = phrase.get(position);

                releaseMediaPlayer();;

                mediaPlayer = MediaPlayer.create(PhrasesActivity.this,word.getmAudio());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(mCompletion);

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