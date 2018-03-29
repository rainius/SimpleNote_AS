package com.jing.app.sn.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jing.app.sn.model.Note;

import java.util.ArrayList;
import java.util.List;

import static com.jing.app.sn.db.NoteDbConstants.*;

/**
 * Created by jing on 2018/3/22.
 */

public class NoteDao {

    private static NoteDao sInstance;

    public static final NoteDao getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NoteDao(context);
        }

        return sInstance;
    }

    private NoteDbHelper dbHelper;
    private Context context;

    private NoteDao(Context context) {
        this.context = context;
        dbHelper = new NoteDbHelper(context, NoteDbConstants.DB_FILE, null, NoteDbConstants.VERSION);
    }

    public List<Note> queryAllNotes() {

        ArrayList<Note> notes = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTE, null, null, null, null, null, COL_ID + " DESC");
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(COL_TITLE));
                    String content = cursor.getString(cursor.getColumnIndexOrThrow(COL_CONTENT));
                    long createTime = cursor.getLong(cursor.getColumnIndexOrThrow(COL_CREATE_TIME));
                    Note note = new Note(id, title, content, createTime);
                    notes.add(note);
                }
            } finally {
                cursor.close();
            }
        }

        return notes;
    }

    public Note queryNoteById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = NoteDbConstants.COL_ID + "=" + id;
        Cursor cursor = db.query(TABLE_NOTE, null, selection, null, null, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(COL_TITLE));
                    String content = cursor.getString(cursor.getColumnIndexOrThrow(COL_CONTENT));
                    long createTime = cursor.getLong(cursor.getColumnIndexOrThrow(COL_CREATE_TIME));
                    Note note = new Note(id, title, content, createTime);
                }
            } finally {
                cursor.close();
            }
        }
        return null;
    }

    public long insertNote(Note note) {
        if (note == null) {
            return 0;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NoteDbConstants.COL_TITLE, note.getTitle());
        values.put(NoteDbConstants.COL_CONTENT, note.getContent());
        values.put(NoteDbConstants.COL_CREATE_TIME, note.getCreateTime());
        long id = db.insert(TABLE_NOTE, null, values);
        return id;
    }
}
