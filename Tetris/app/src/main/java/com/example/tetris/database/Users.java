package com.example.tetris.database;

/**
 * Created by Iulian on 4/30/2017.
 */

public class Users {
    private long id;
    private String username;
    private String password;
    private String e_mail;
    private int coins;
    private int color1;
    private int color2;
    private int color3;
    private int color4;
    private int color5;
    private int highscore;
    private int lives;

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id=id;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String pw){
        this.password=pw;
    }

    public String getE_mail(){
        return e_mail;
    }

    public void setE_mail(String e_mail){
        this.e_mail=e_mail;
    }

    public int getCoins(){
        return coins;
    }

    public void setCoins(int coins){
        this.coins=coins;
    }

    public int getColor1(){
        return color1;
    }

    public void setColor1(int color){
        this.color1=color;
    }

    public int getColor2(){
        return color2;
    }

    public void setColor2(int color){
        this.color2=color;
    }

    public int getColor3(){
        return color3;
    }

    public void setColor3(int color){
        this.color3=color;
    }

    public int getColor4(){
        return color4;
    }

    public void setColor4(int color){
        this.color4=color;
    }

    public int getColor5(){
        return color5;
    }

    public void setColor5(int color){
        this.color5=color;
    }

    public int getHighscore(){
        return highscore;
    }

    public void setHighscore(int highscore){
        this.highscore=highscore;
    }

    public int getLives(){
        return lives;
    }

    public void setLives(int lives){
        this.lives=lives;
    }

    @Override
    public String toString() {
        return username;
    }



}
