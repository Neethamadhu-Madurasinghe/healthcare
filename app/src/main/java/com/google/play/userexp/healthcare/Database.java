package com.google.play.userexp.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static Database sInstance;

//    Make this a singleton object
    public static synchronized Database getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new Database(context, "healthcare", null, 1);
        }
        return sInstance;
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user(username text, email text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("user", null, cv);
        db.close();
    }

    public boolean login(String username, String password) {
        boolean result = false;
        String param[] = new String[2];
        param[0] = username;
        param[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM user WHERE username=? AND password=?", param);

        if(c.moveToFirst()) {
            result = true;
        }
        return result;
    }
}
