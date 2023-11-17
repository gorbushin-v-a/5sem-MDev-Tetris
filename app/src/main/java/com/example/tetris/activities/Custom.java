package com.example.tetris.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.tetris.database.CustomArrayAdapter;
import com.example.tetris.shapes.R;
import com.example.tetris.database.Users;
import com.example.tetris.database.UsersDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Custom extends AppCompatActivity {

    private ListView lv_test;
    private Button btn_add_element;

    private ArrayList<String> mElementsList;
    private ArrayAdapter<String> mAdapter;

    private ArrayList<Users> mListCustom;
    private CustomArrayAdapter mCustomArrayAdapter;

    UsersDataSource datasource;

    private int countNewElements = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        datasource = new UsersDataSource(this);
        datasource.open();

        btn_add_element = (Button) findViewById(R.id.return_home_button);

        lv_test = (ListView) findViewById(R.id.lv_test);

        setUpCustomList();

    }

    private void setUpCustomList(){

        mListCustom = new ArrayList<>();
        List<Users> users = datasource.getAllUsers();
        Users aux;

        int ok=0;
        while(ok==0)
        {
            ok=1;
            for(int i=0;i<users.size()-1;i++)
            {
                if(users.get(i).getHighscore() < users.get(i+1).getHighscore())
                {
                    Collections.swap(users, i, i+1);
                    ok=0;
                }
            }
        }

        for(int i=0;i<users.size();i++){
            mListCustom.add(users.get(i));
        }




        mCustomArrayAdapter = new CustomArrayAdapter(this, R.layout.item_user, mListCustom);
        lv_test.setAdapter(mCustomArrayAdapter);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.return_home_button:
                Intent nextScreen = new Intent(getApplicationContext(), Homepage.class);
                startActivity(nextScreen);
                break;
        }


    }






}