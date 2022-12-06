package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class NumberActivity extends AppCompatActivity {

    // At initial i am calling and creating the ArrayList outside the method but inside class
    // So when i use add method to call then it shows an error cannot resolve symbol add
    // **** Java cannot resolve method calls if the method call does not call from any method. ***

    //creating mediaPlayer object

    private MediaPlayer mediaPlayer;

    private void releaseMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        // This is way to create ArrayList of Object type String But now we are going to create a ArrayList of object Word type
        //******************************************************************************************
        //ArrayList<String> words = new ArrayList<String>(Arrays.asList("one","two","three","four","five","six","seven","eight","nine","ten"));
        //******************************************************************************************

        // Now creating a ArrayList of Object Type Word

        final ArrayList<Word> words = new ArrayList<Word>();




        // Now adding words to ArrayList using add method

        // ** This is a one way of doing this
        Word w = new Word("one","lutti",R.drawable.number_one,R.raw.number_one);
        words.add(w);

        // ** another way to do same
        words.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("thre" +
                "e","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo’e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na’aacha",R.drawable.number_ten,R.raw.number_ten));





        // As we know the difference between ArrayAdapter(Recycle View) and simple layouts so for this app
        // we are going to use the ArrayAdapter for every particular page so to minimize the memory use

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        // Context Which refers to the number activity ** reason we need Context is because we are creating views **
        // Since NumberActivity is a context so we specify the word this to refer to the NumberActivity
        // <!-- ********************************************************************************* -->
        // **** As ArrayAdapter can only take ne list(Object) at a time as its parameter but we know we want to show
        // **** List of meow word(ArrayList) and list of english word(ArrayList)
        // **** So now this time we are going to create our custom class and create an object of that class and
        // **** Pass it into the argument of ArrayAdapter
        // **** Also here we need to change the simple_list_items_1 layout to our custom layout as it layout is
        // **** not kind of that we want
        // <!-- ********************************************************************************** -->
        //ArrayAdapter <String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,words);

        // now again creating ArrayAdapter

        // now when we try to run our app this crash away becz by default this class expects that the provided resource
        // id reference a single textView so that why the list_item_1(android layout) works on this app As the
        // Whole layout consisted of Single TextView
        // So in order to display more complicated list item that can pertain more than a Single texView
        // we need to overwrite and provide our own implementation of getView() method
       //**** ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item,words);

        WordAdapter adapter = new WordAdapter(this, words,R.color.category_numbers);


        ListView lv = (ListView)  findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);

                releaseMediaPlayer();

                mediaPlayer = MediaPlayer.create(NumberActivity.this,word.getmAudio());
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.

        ListView listView = (ListView) findViewById(R.id.list);

//        GridView gridView = (GridView)  findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.

        listView.setAdapter(adapter);// setAdapter(ListAdapter adapter) as it take input parameter as an object type
        // ListAdapter but we are providing ArrayAdapter ** the hint is the ListAdapter is public interface but for it we want
        // to provide implementation for method
        // So the ArrayAdapter extends from BaseAdapter and BaseAdapter is a abstract class and it implements the ListAdapter interface

//        *****
//        In this piece of code we are creating the children layout in LinerLayout using the java code
//
//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//
//        for(int index = 0;index< words.size();index++){
//            TextView wordView = new TextView(this); *** Creating Object of TextView for NumberActivity
//            wordView.setText(words.get(index));
//            rootView.addView(wordView); ** Adding the TextView in LinerLayout as Children
//        }
//        *****
//
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}

