package com.example.tetris.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tetris.shapes.R;
import com.example.tetris.database.SessionManagement;
import com.example.tetris.database.Users;
import com.example.tetris.database.UsersDataSource;

import java.util.List;

public class ManageProfile extends AppCompatActivity {

    private UsersDataSource datasource;
    private String username;
    private List<Users> users;
    private int contor;
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_profile);
        session = new SessionManagement(getApplicationContext());

        datasource = new UsersDataSource(this);
        datasource.open();

        username=getIntent().getStringExtra("name");
        users = datasource.getAllUsers();
        contor=0;
        for (int i=0;i<users.size();i++)
        {
            if (users.get(i).getUsername().equals(username)){
                contor=i;
                break;
            }
        }

    }

    public void onClick(View view){
        switch(view.getId()){

            case R.id.buy_lives_button:
                int number_of_coins = users.get(contor).getCoins();
                if(number_of_coins < 10){
                    Context context = getApplicationContext();
                    CharSequence text = "Not enough coins";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                }
                else{
                    datasource.updateUsersCoins(users.get(contor),number_of_coins-10);
                    int number_of_lives=users.get(contor).getLives();
                    datasource.updateUsersLives(users.get(contor), number_of_lives +1);
                    Context context = getApplicationContext();
                    CharSequence text = "+1 life";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                    Intent nextScreen = new Intent(getApplicationContext(), ManageProfile.class);
                    startActivity(nextScreen);
                }
                break;

            case R.id.back_button:
                Intent nextScreen1 = new Intent(getApplicationContext(), Homepage.class);
                startActivity(nextScreen1);
                break;

            case R.id.delete_account_button:
                datasource.deleteUsers(users.get(contor));
                session.logoutUser();
                break;

            case R.id.change_password_button:
                Intent nextScreen2 = new Intent(getApplicationContext(), ChangePassword.class);
                nextScreen2.putExtra("name", username);
                startActivity(nextScreen2);

        }


    }


}
