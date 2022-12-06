package com.example.android.miwok;


import android.app.Activity;
import android.media.Image;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;


// now when we try to run our app this crash away becz by default this class expects that the provided resource
// id reference a single textView so that why the list_item_1(android layout) works on this app As the
// Whole layout consisted of Single TextView
// So in order to display more complicated list item that can pertain more than a Single texView
// we need to overwrite and provide our own implementation of getView() method
//**** ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item,words);

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorValue;

    public WordAdapter(Activity context, ArrayList<Word> words,int colorRId){
        super(context,0,words);//here we are using constructor of Super class i.e ArrayAdapter
        // We can use constructor of Super class in Sub class

        mColorValue = colorRId;

    }


    @Override
    public View getView(int position, android.view.View convertView, ViewGroup parent) {
        View listItemView = convertView; // listItemView is currently referring the root LinerLayout for the list_item layout
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());


        //set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);

        // find the color that the resource id map to

        int color = ContextCompat.getColor(getContext(), mColorValue);

        // Set the background color of the text container view
        textContainer.setBackgroundColor(color);


//        if (currentWord.hasImage()) {
//            ImageView startImageView = (ImageView) listItemView.findViewById(R.id.img_view);
//            startImageView.setImageResource(currentWord.getImage());
//
//            return listItemView;
//
//        } else {
//            return listItemView;
//        }

//   Other Way to do the above thing
        ImageView startIV = (ImageView) listItemView.findViewById(R.id.img_view);

//
//
        if (currentWord.hasImage()) {
            startIV.setImageResource(currentWord.getImage());

            startIV.setVisibility(View.VISIBLE);


        } else {
            startIV.setVisibility(View.GONE);
        }
        return listItemView;
    }
}

