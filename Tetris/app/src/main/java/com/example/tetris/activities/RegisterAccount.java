package com.example.tetris.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tetris.shapes.R;
import com.example.tetris.database.Users;
import com.example.tetris.database.UsersDataSource;

public class RegisterAccount extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText confirmPassword;
    private EditText email;
    private UsersDataSource datasource;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        email = (EditText) findViewById(R.id.email);
        datasource = new UsersDataSource(this);
        datasource.open();



        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/Glotona_Black_font-FFP.ttf");
        TextView textview = (TextView) findViewById(R.id.tv_registration_title);
        textview.setTypeface(tf);

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.register_button:

                String u = username.getText().toString();
                String p = password.getText().toString();
                String cp = confirmPassword.getText().toString();
                String e = email.getText().toString();

                int ok=0;
                if (p.equals(cp))
                    ok+=1;
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Passwords don't match!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                    password.setText("");
                    confirmPassword.setText("");
                }
                if (p.length()>=6)
                    ok+=1;
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Short password!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                    password.setText("");
                    confirmPassword.setText("");
                }
                if (u.length()>=6)
                    ok+=1;
                else
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Short name!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context,text,duration).show();
                    password.setText("");
                    confirmPassword.setText("");
                }
                Users user=null;
                if(ok==3) {
                    user = datasource.createUser(u, p, e, 0, 0, 0, 0, 0, 0, 0, 5);
                    if(user == null) {
                        Context context = getApplicationContext();
                        CharSequence text = "Username already exists!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context,text,duration).show();


                    }
                    else
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Success!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context,text,duration).show();

                        Intent nextScreen = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(nextScreen);
                    }
                }
        }


    }
}
