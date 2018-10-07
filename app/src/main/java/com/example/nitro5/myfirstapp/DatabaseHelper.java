package com.example.nitro5.myfirstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/** pin numbers storage **/
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userpin.db";
    public static final String TABLE_NAME = "users_pin";
    public static final String COL_1 = "id";
    public static final String COL_2 = "email";
    public static final String COL_3 = "pin_numbers";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,email TEXT,pin_numbers INTEGER NOT NULL )"); /**execute string variable when called**/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2, user.getEmail());
        values.put(COL_3, user.getPin());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String email,String pin_numbers ){
    String [] columns = {
            COL_1

    };

    SQLiteDatabase db = this.getWritableDatabase();
 String selection = COL_2 + "=?";
 String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_NAME,
                columns,selection,selectionArgs,
                null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
                if(cursorCount > 0){
                    return true;
                }

                return  false;
}

}

