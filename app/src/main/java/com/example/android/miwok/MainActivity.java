/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // This is One way to create OnclickListener

        // Here we create OnclickListener for Number
//        NumberClickListener clickListener = new NumberClickListener();
//
//        TextView viewNumber = (TextView) findViewById(R.id.numbers);
//
//        viewNumber.setOnClickListener(clickListener);

        // This is for Number
        TextView viewNumber = (TextView) findViewById(R.id.numbers);

        viewNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast message
                Toast.makeText(v.getContext(), "Opening number Page", Toast.LENGTH_SHORT).show();
                //Intent creation for Activity
                Intent numberintent = new Intent(MainActivity.this, NumberActivity.class);
                startActivity(numberintent);
            }
        });


        // Other way to Create the OnClickListener
        // This is for Family


        TextView viewFamily = (TextView) findViewById(R.id.family);

        viewFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Opening Family page", Toast.LENGTH_SHORT).show();
                //Intent creation
                Intent familyintent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyintent);
            }
        });

        // This is for Colors page

        TextView viewColor = (TextView) findViewById(R.id.colors);
        viewColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Opening Colors page", Toast.LENGTH_SHORT).show();
                Intent colorintent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorintent);
            }
        });
//
        // This is for Phrases Page

        TextView viewPhrase = (TextView) findViewById(R.id.phrases);

        viewPhrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Opening Phrase page", Toast.LENGTH_SHORT).show();
                //Creating Intent
                Intent phraseintent =  new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(phraseintent);
            }
        });
    }

//    public void gotoColorsActivity(View view){
//        Intent intent = new Intent(this,ColorsActivity.class);
//        startActivity(intent);
//    }
    }
