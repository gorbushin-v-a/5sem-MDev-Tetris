package com.example.tetris.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class UsersDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {
            MySQLiteHelper.ID,
            MySQLiteHelper.USERNAME,
            MySQLiteHelper.PASSWORD,
            MySQLiteHelper.E_MAIL,
            MySQLiteHelper.COINS,
            MySQLiteHelper.COLOR1,
            MySQLiteHelper.COLOR2,
            MySQLiteHelper.COLOR3,
            MySQLiteHelper.COLOR4,
            MySQLiteHelper.COLOR5,
            MySQLiteHelper.HIGHSCORE,
            MySQLiteHelper.LIVES

    };

    public UsersDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Users createUser(String username, String password, String e_mail, int coins, int color1, int color2, int color3, int color4, int color5, int highscore, int lives) {

        List<Users> u = getAllUsers();
        int ok=1;
        for(int i=0;i<u.size();i++) {
            if (u.get(i).getUsername().equals(username)) { //verifica daca username exista in baza de date
                ok = 0;
                break;
            }
        }

        if(ok==1) {
            ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.USERNAME, username);
            values.put(MySQLiteHelper.PASSWORD, password);
            values.put(MySQLiteHelper.E_MAIL, e_mail);
            values.put(MySQLiteHelper.COINS, coins);
            values.put(MySQLiteHelper.COLOR1, color1);
            values.put(MySQLiteHelper.COLOR2, color2);
            values.put(MySQLiteHelper.COLOR3, color3);
            values.put(MySQLiteHelper.COLOR4, color4);
            values.put(MySQLiteHelper.COLOR5, color5);
            values.put(MySQLiteHelper.HIGHSCORE, highscore);
            values.put(MySQLiteHelper.LIVES, lives);

            long insertId = database.insert(MySQLiteHelper.TABLE_NAME, null,
                    values);
            Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                    allColumns, MySQLiteHelper.ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            Users newUsers = cursorToUsers(cursor);
            cursor.close();
            return newUsers;
        }
        else
            return null;
    }

    public void deleteUsers(Users user) {
        long id = user.getId();
        System.out.println("User deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_NAME, MySQLiteHelper.ID
                + " = " + id, null);
    }

    public void updateUsersCoins(Users user, int nr) {
        ContentValues v= new ContentValues();
        v.put(MySQLiteHelper.COINS,nr);
        long id = user.getId();
        //database.update(MySQLiteHelper.TABLE_NAME,v,)

        database.execSQL("UPDATE " +MySQLiteHelper.TABLE_NAME+" SET coins ="+Integer.toString(nr)+" WHERE _id="+id+"");
    }

    public void updateUsersHighscore(Users user, int nr) {
        ContentValues v= new ContentValues();
        v.put(MySQLiteHelper.HIGHSCORE,nr);
        long id = user.getId();
        //database.update(MySQLiteHelper.TABLE_NAME,v,)

        database.execSQL("UPDATE " +MySQLiteHelper.TABLE_NAME+" SET highscore ="+Integer.toString(nr)+" WHERE _id="+id+"");
    }

    public void updateUsersLives(Users user, int nr) {
        ContentValues v= new ContentValues();
        v.put(MySQLiteHelper.LIVES,nr);
        long id = user.getId();
        //database.update(MySQLiteHelper.TABLE_NAME,v,)

        database.execSQL("UPDATE " +MySQLiteHelper.TABLE_NAME+" SET lives ="+Integer.toString(nr)+" WHERE _id="+id+"");
    }

    public void updateUsersPassword(Users user, String pw) {
        ContentValues v= new ContentValues();
        v.put(MySQLiteHelper.LIVES,pw);
        long id = user.getId();
        //database.update(MySQLiteHelper.TABLE_NAME,v,)

        database.execSQL("UPDATE " +MySQLiteHelper.TABLE_NAME+" SET password ='"+pw+"' WHERE _id="+id+"");
    }

    public List<Users> getAllUsers() {
        List<Users> users = new ArrayList<Users>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Users user = cursorToUsers(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }

    private Users cursorToUsers(Cursor cursor) {
        Users user = new Users();
        user.setId(cursor.getLong(0));
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setE_mail(cursor.getString(3));
        user.setCoins(cursor.getInt(4));
        user.setColor1(cursor.getInt(5));
        user.setColor2(cursor.getInt(6));
        user.setColor3(cursor.getInt(7));
        user.setColor4(cursor.getInt(8));
        user.setColor5(cursor.getInt(9));
        user.setHighscore(cursor.getInt(10));
        user.setLives(cursor.getInt(11));
        return user;
    }
}
