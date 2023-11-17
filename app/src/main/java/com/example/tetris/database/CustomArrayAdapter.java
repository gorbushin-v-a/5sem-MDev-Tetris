package com.example.tetris.database;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tetris.shapes.R;

import java.util.ArrayList;
import java.util.Random;


public class CustomArrayAdapter extends ArrayAdapter<Users> {

    private int mResource;
    private Context mContext;

    public static class ViewHolder{

        TextView tv_username;
        TextView tv_highscore;
        TextView tv_coins;

    }

    public CustomArrayAdapter(Context context, int resource, ArrayList<Users> objects) {
        super(context, resource, objects);

        this.mResource = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View mView = convertView;
        if(mView == null){
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            mView = inflater.inflate(mResource, null);

            ViewHolder mHolder = new ViewHolder();
            mHolder.tv_username = (TextView) mView.findViewById(R.id.tv_username);
            mHolder.tv_highscore = (TextView) mView.findViewById(R.id.tv_highscore);
            mHolder.tv_coins = (TextView) mView.findViewById(R.id.tv_coins);


            mView.setTag(mHolder);
        }

        ViewHolder holderView = (ViewHolder) mView.getTag();

        Users mUser = getItem(position);



        holderView.tv_username.setText(mUser.getUsername());
        int val1 = mUser.getHighscore();
        holderView.tv_highscore.setText(Integer.toString(val1));
        int val2 = mUser.getCoins();
        holderView.tv_coins.setText(Integer.toString(val2));
        Random r = new Random();
        int i1=r.nextInt(10);
        if(i1==0){

            holderView.tv_username.setBackgroundResource(R.color.c1);
            holderView.tv_highscore.setBackgroundResource(R.color.c1);
            holderView.tv_coins.setBackgroundResource(R.color.c1);
        }
        if(i1==1){
            holderView.tv_username.setBackgroundResource(R.color.c2);
            holderView.tv_highscore.setBackgroundResource(R.color.c2);
            holderView.tv_coins.setBackgroundResource(R.color.c2);
        }
        if(i1==2){
            holderView.tv_username.setBackgroundResource(R.color.c3);
            holderView.tv_highscore.setBackgroundResource(R.color.c3);
            holderView.tv_coins.setBackgroundResource(R.color.c3);
        }
        if(i1==3){
            holderView.tv_username.setBackgroundResource(R.color.c4);
            holderView.tv_highscore.setBackgroundResource(R.color.c4);
            holderView.tv_coins.setBackgroundResource(R.color.c4);
        }
        if(i1==4){
            holderView.tv_username.setBackgroundResource(R.color.c5);
            holderView.tv_highscore.setBackgroundResource(R.color.c5);
            holderView.tv_coins.setBackgroundResource(R.color.c5);
        }
        if(i1==5){
            holderView.tv_username.setBackgroundResource(R.color.c6);
            holderView.tv_highscore.setBackgroundResource(R.color.c6);
            holderView.tv_coins.setBackgroundResource(R.color.c6);
        }
        if(i1==6){
            holderView.tv_username.setBackgroundResource(R.color.c7);
            holderView.tv_highscore.setBackgroundResource(R.color.c7);
            holderView.tv_coins.setBackgroundResource(R.color.c7);
        }
        if(i1==7){
            holderView.tv_username.setBackgroundResource(R.color.c8);
            holderView.tv_highscore.setBackgroundResource(R.color.c8);
            holderView.tv_coins.setBackgroundResource(R.color.c8);
        }
        if(i1==8){
            holderView.tv_username.setBackgroundResource(R.color.c9);
            holderView.tv_highscore.setBackgroundResource(R.color.c9);
            holderView.tv_coins.setBackgroundResource(R.color.c9);
        }
        if(i1==9){
            holderView.tv_username.setBackgroundResource(R.color.c10);
            holderView.tv_highscore.setBackgroundResource(R.color.c10);
            holderView.tv_coins.setBackgroundResource(R.color.c10);
        }



        return mView;
    }
}