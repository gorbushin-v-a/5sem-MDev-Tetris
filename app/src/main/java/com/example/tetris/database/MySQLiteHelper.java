package com.example.tetris.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "users1";
    public static final String ID = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String E_MAIL = "e_mail";
    public static final String COINS = "coins";
    public static final String COLOR1 = "color1";
    public static final String COLOR2 = "color2";
    public static final String COLOR3 = "color3";
    public static final String COLOR4 = "color4";
    public static final String COLOR5 = "color5";
    public static final String HIGHSCORE = "highscore";
    public static final String LIVES = "lives";


    private static final String DATABASE_NAME = "users1.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( " + ID + " integer primary key autoincrement, "
            + USERNAME + " text not null, "
            + PASSWORD + " text not null, "
            + E_MAIL + " e_mail not null, "
            + COINS + " integer not null, "
            + COLOR1 + " integer not null, "
            + COLOR2 + " integer not null, "
            + COLOR3 + " integer not null, "
            + COLOR4 + " integer not null, "
            + COLOR5 + " integer not null, "
            + HIGHSCORE + " integer not null, "
            + LIVES + " integer not null "
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
