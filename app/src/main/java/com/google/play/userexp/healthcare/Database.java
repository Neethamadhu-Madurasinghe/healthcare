package com.google.play.userexp.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.Executor;

public class Database extends SQLiteOpenHelper {
    private static Database sInstance;
    private final Executor executor;
    private final Handler handler;

//    Make this a singleton object
    public static synchronized Database getInstance(Context context, Executor executor, Handler handler) {
        if(sInstance == null) {
            sInstance = new Database(context, "healthcare", null, 1, executor, handler);
        }
        return sInstance;
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, Executor executor, Handler handler) {
        super(context, name, factory, version);
        this.executor = executor;
        this.handler = handler;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user(username text, email text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String username, String email, String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ContentValues cv = new ContentValues();
                cv.put("username", username);
                cv.put("email", email);
                cv.put("password", password);

                SQLiteDatabase db = getWritableDatabase();
                db.insert("user", null, cv);
                db.close();
            }
        });
    }

    public void login(String username, String password, DatabaseResponse<Boolean> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.i("Database", "Database: Starting login process");
                boolean result = false;
                String param[] = new String[2];
                param[0] = username;
                param[1] = password;
                SQLiteDatabase db = getReadableDatabase();
                Cursor c = db.rawQuery("SELECT * FROM user WHERE username=? AND password=?", param);

                if(c.moveToFirst()) {
                    result = true;
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Log.i("Database", "Database: Finishing login process");
                db.close();

//              We need a final variable to be passed into post method
                final boolean finalResult = result;

//              Calling the callback inside the main thread again
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onComplete(finalResult);
                    }
                });
            }
        });
    }

}

interface DatabaseResponse<T> {
    void onComplete(T result);
}
