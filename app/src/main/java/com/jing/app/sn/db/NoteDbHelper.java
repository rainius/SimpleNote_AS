package com.jing.app.sn.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.jing.app.sn.db.NoteDbConstants.COL_CONTENT;
import static com.jing.app.sn.db.NoteDbConstants.COL_CREATE_TIME;
import static com.jing.app.sn.db.NoteDbConstants.COL_ID;
import static com.jing.app.sn.db.NoteDbConstants.COL_TITLE;
import static com.jing.app.sn.db.NoteDbConstants.TABLE_NOTE;

/**
 * Created by jing on 2018/3/20.
 */

public class NoteDbHelper extends SQLiteOpenHelper {


    public NoteDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}


//    public NoteDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " + TABLE_NOTE + "(" +
//                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COL_TITLE + " TEXT, " +
//                COL_CONTENT + " TEXT, " +
//                COL_CREATE_TIME + " INTEGER)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//}
