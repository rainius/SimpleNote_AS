package com.jing.app.sn.repository;

import android.content.Context;

import com.jing.app.sn.dao.NoteDAO;
import com.jing.app.sn.model.Note;

import java.util.ArrayList;

/**
 * Created by jing on 2018/4/5.
 */

public class NoteRepository implements INoteRepository {

    private Context context;

    // 私有构造方法
    private NoteRepository(Context context) {
        this.context = context;
    }

    private  static NoteRepository sInstance;

    //获取单例对象
    public static NoteRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NoteRepository(context);
        }
        return sInstance;
    }

    @Override
    public ArrayList<Note> getAllNotes() {
        return (ArrayList<Note>) NoteDAO.getInstance(context).queryAllNotes();
    }

    @Override
    public Note saveNote(Note note) {
        return NoteDAO.getInstance(context).insertNote(note);
    }

    @Override
    public Note getNote(long noteId) {
        return NoteDAO.getInstance(context).queryNoteById(noteId);
    }
}
