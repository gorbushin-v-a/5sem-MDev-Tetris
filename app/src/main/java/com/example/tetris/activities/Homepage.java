package com.example.tetris.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tetris.shapes.R;
import com.example.tetris.database.SessionManagement;
import com.example.tetris.database.Users;
import com.example.tetris.database.UsersDataSource;

import java.util.HashMap;
import java.util.List;

public class Homepage extends AppCompatActivity {


    private TextView tv;
    private TextView tvCoins;
    private TextView tvHighscore;
    private TextView tvLives;
    SessionManagement session;
    int score;
    int number_of_coins;
    private UsersDataSource datasource;
    private String username;
    private int contor;
    private List<Users> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        datasource = new UsersDataSource(this);
        datasource.open();

        session = new SessionManagement(getApplicationContext());
        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();
        username = user.get(SessionManagement.KEY_USERNAME);

        score = getIntent().getIntExtra("score",0);
        users = datasource.getAllUsers();
        contor=0;
        for (int i=0;i<users.size();i++) {
            if (users.get(i).getUsername().equals(username)) {
                number_of_coins = (int) Math.floor(score / 10);
                int val = users.get(i).getCoins();
                val = val + number_of_coins;
                //users.get(i).setCoins(val);
                datasource.updateUsersCoins(users.get(i),val);

                int val2=users.get(i).getHighscore();
                if(score>val2) {
                    datasource.updateUsersHighscore(users.get(i), score);
                }
                contor=i;
                break;
            }
        }
        if(score > 0){
            Intent refresh = new Intent(this, Homepage.class);
            startActivity(refresh);//Start the same Activity
            finish();
        }

        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Glotona_Black_font-FFP.ttf");
        Typeface tf2 = Typeface.createFromAsset(getAssets(),
                "fonts/ROCK_KAPAK_2.ttf");
        Typeface tf3 = Typeface.createFromAsset(getAssets(),
                "fonts/CookieMonster.ttf");

        tv=(TextView) findViewById(R.id.welcome_user);
        tv.setText("Welcome, "+username+"!");


        tv.setTypeface(tf);

        tvHighscore=(TextView) findViewById(R.id.tv_highscore);
        tvHighscore.setText("Your highscore: "+Integer.toString(users.get(contor).getHighscore()));
        tvHighscore.setTypeface(tf2);

        tvCoins=(TextView) findViewById(R.id.number_of_coins);
        tvCoins.setText("Coins: "+Integer.toString(users.get(contor).getCoins()));
        tvCoins.setTypeface(tf2);

        tvLives=(TextView) findViewById(R.id.tv_lives);
        tvLives.setText(Integer.toString(users.get(contor).getLives())+"o");
        tvLives.setTypeface(tf3);







        //Context context = getApplicationContext();
        //String text = "New score: "+Integer.toString(score);
        //int duration = Toast.LENGTH_SHORT;
        //Toast.makeText(context,text,duration).show();

    }

    public void onClick(View view){
        switch(view.getId()){

            case R.id.play_tetris_button:
                int nr_of_lives=users.get(contor).getLives();
                if(nr_of_lives == 0){
                    Context context = getApplicationContext();
                    CharSequence text = "Not enough lives - buy more!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                }
                else {
                    datasource.updateUsersLives(users.get(contor), nr_of_lives - 1);
                    Intent nextScreen = new Intent(getApplicationContext(), Tetris.class);
                    startActivity(nextScreen);
                }
                break;

            case R.id.hall_of_fame_button:

                Intent nextScreen1 = new Intent(getApplicationContext(), Custom.class);
                startActivity(nextScreen1);
                break;

            case R.id.options_button:
                Intent nextScreen2 = new Intent(getApplicationContext(), ManageProfile.class);
                nextScreen2.putExtra("name", username);
                startActivity(nextScreen2);
                break;

            case R.id.log_out_button:
                session.logoutUser();
                break;

            case R.id.vk:
                Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getResources().getString(R.string.recommendation_subject));
                String res = " "+String.valueOf(users.get(contor).getHighscore());
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, getResources().getString(R.string.recommendation_body)+res);
                startActivity(emailIntent);
                break;
        }
    }
}
