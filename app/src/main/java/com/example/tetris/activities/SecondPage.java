package com.example.tetris.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tetris.shapes.R;

public class SecondPage extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
    }

        public void onClick(View view){
           switch(view.getId()){
               case R.id.change:
                   Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                   startActivity(nextScreen);
                   break;
           }


    }
}