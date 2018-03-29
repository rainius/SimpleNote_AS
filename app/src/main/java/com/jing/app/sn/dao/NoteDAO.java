package com.jing.app.sn.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.jing.app.sn.dao.Constants.*;


public class NoteDAO {

    /**
     * 采用单例模式
     */
    private static NoteDAO sInstance;

    public static NoteDAO getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NoteDAO(context);
        }
        return sInstance;
    }

    private Context context;
    private NoteDbHelper dbHelper;

    /**
     * 私有构造方法
     * @param context
     */
    private NoteDAO(Context context) {
        this.context = context;
        dbHelper = new NoteDbHelper(context, DB_NAME, null, VERSION);
    }

    NoteDbHelper getDbHelper() {
        return dbHelper;
    }

    static class NoteDbHelper extends SQLiteOpenHelper {

        public NoteDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NOTE + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_CONTENT + " TEXT, " +
                COL_CREATE_TIME + " INTEGER)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
