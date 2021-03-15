package com.example.tetris.activities;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.tetris.shapes.R;
import com.example.tetris.database.Users;
import com.example.tetris.database.UsersDataSource;

public class MainActivity extends ListActivity {


    private UsersDataSource datasource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new UsersDataSource(this);
        datasource.open();

        List<Users> users = datasource.getAllUsers();


        ArrayAdapter<Users> adapter = new ArrayAdapter<Users>(this,
                android.R.layout.simple_list_item_1, users);
        setListAdapter(adapter);


    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.return_home_button:
                Intent nextScreen = new Intent(getApplicationContext(), Homepage.class);
                startActivity(nextScreen);
                break;
        }


    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}