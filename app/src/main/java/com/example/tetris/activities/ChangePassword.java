package com.example.tetris.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tetris.shapes.R;
import com.example.tetris.database.Users;
import com.example.tetris.database.UsersDataSource;

import java.util.List;

public class ChangePassword extends AppCompatActivity {

    private UsersDataSource datasource;
    private String username;
    private List<Users> users;
    private int contor;
    private EditText currentP;
    private EditText newP;
    private EditText confirmP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

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

        currentP = (EditText) findViewById(R.id.current_password);
        newP =(EditText) findViewById(R.id.new_password);
        confirmP=(EditText) findViewById(R.id.confirm_password);


    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.change_button:
                //Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(nextScreen);
                //break;

                String cp = currentP.getText().toString();
                String np = newP.getText().toString();
                String confP = confirmP.getText().toString();

                if(users.get(contor).getPassword().equals(cp)){
                    if (np.equals(confP)){
                        datasource.updateUsersPassword(users.get(contor),np);

                        Context context = getApplicationContext();
                        CharSequence text = "Password changed!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context,text,duration).show();
                        Intent nextScreen = new Intent(getApplicationContext(), ManageProfile.class);
                        startActivity(nextScreen);

                    }
                    else{
                        Context context = getApplicationContext();
                        CharSequence text = "New passwords do not match!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context,text,duration).show();
                        currentP.setText("");
                        newP.setText("");
                        confirmP.setText("");
                    }
                }
                else{
                    Context context = getApplicationContext();
                    CharSequence text = "Current password does not match!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                    currentP.setText("");
                    newP.setText("");
                    confirmP.setText("");
                }
                break;
        }


    }


}
