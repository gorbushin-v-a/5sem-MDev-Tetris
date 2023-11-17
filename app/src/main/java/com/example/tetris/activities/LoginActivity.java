package com.example.tetris.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tetris.shapes.R;
import com.example.tetris.database.SessionManagement;
import com.example.tetris.database.Users;
import com.example.tetris.database.UsersDataSource;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private UsersDataSource datasource;
    private EditText username;
    private EditText password;
    private TextView textview;
    private List<Users> users;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        datasource = new UsersDataSource(this);
        datasource.open();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        users = datasource.getAllUsers();
        session = new SessionManagement(getApplicationContext());

        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Glotona_Black_font-FFP.ttf");
        TextView textview = (TextView) findViewById(R.id.tv_title);
        textview.setTypeface(tf);

    }



    public void onClick(View view){
        switch(view.getId()){
            case R.id.sign_in_button:
                String u = username.getText().toString();
                String p = password.getText().toString();


                int ok=0;
                for(int i=0;i<users.size();i++) {
                    if (users.get(i).getUsername().equals(u)) { //verifica daca username exista in baza de date
                        if(users.get(i).getPassword().equals(p)) {
                            ok = 1;
                            break;
                        }
                    }
                }

                if(ok==0)
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Login unsuccessful";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                    password.setText("");
                }
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Login OK";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();

                    session.createLoginSession(u,p);
                    Intent nextScreen = new Intent(getApplicationContext(), Homepage.class);
                    nextScreen.putExtra("name", u);
                    startActivity(nextScreen);
                    //finish();
                }

                break;

            case R.id.register_button:
                Intent nextScreen = new Intent(getApplicationContext(), RegisterAccount.class);
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
