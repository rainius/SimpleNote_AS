package com.jing.app.sn.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jing.app.sn.model.Note;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 向数据库中插入一条笔记
     * @param note 被添加到数据库的笔记对象
     * @return 插入成功后更新note的id并将note对象返回；插入失败则返回null
     */
    public Note insertNote(Note note) {

        if (note == null) {
            return note;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, note.getTitle());
        values.put(COL_CONTENT, note.getContent());
        values.put(COL_CREATE_TIME, note.getCreateTime());

        long id = db.insert(TABLE_NOTE, null, values);
        if (id <= 0) {
            return null;
        }

        note.setId(id);
        return note;
    }

    /**
     * 根据id获取对应的笔记
     * @param id 笔记id
     * @return 如果存在id对应的笔记，则创建对象并返回，否则返回null
     */
    public Note queryNoteById(long id) {
        if (id <= 0) {
            return null;
        }

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = COL_ID + "=" + id;
        Cursor cursor = db.query(TABLE_NOTE, null, selection, null, null, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    String title = cursor.getString(1);
                    String content = cursor.getString(2);
                    long createTime = cursor.getLong(3);
                    Note note = new Note(id, title, content, createTime);
                    return note;
                }
            } finally {
                cursor.close();
            }
        }

        return null;
    }

    /**
     * 获取全部笔记
     * @return 全部笔记的列表。如果没有任何笔记，则返回的列表长度为0
     */
    public List<Note> queryAllNotes() {
        List<Note> notes = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String orderBy = COL_CREATE_TIME + " DESC";
        Cursor cursor = db.query(TABLE_NOTE, null, null, null, null, null, orderBy);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    long id = cursor.getLong(0);
                    String title = cursor.getString(1);
                    String content = cursor.getString(2);
                    long createTime = cursor.getLong(3);
                    Note note = new Note(id, title, content, createTime);
                    notes.add(note);
                }
            } finally {
                cursor.close();
            }
        }

        return notes;
    }

    public void dumpNotes() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NOTE, null, null);
    }

    public void deleteNoteByTitle(String title) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = COL_TITLE + "='" + title + "'";
        db.delete(TABLE_NOTE, selection, null);
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
