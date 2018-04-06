package com.jing.app.sn.repository;

import android.content.Context;

import com.jing.app.sn.dao.NoteDAO;
import com.jing.app.sn.model.Note;

import java.util.ArrayList;

/**
 * Created by jing on 2018/4/3.
 */

class NoteRepository2 implements INoteRepository {

    private static NoteRepository2 sInstance;

    public static NoteRepository2 getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NoteRepository2(context);
        }
        return sInstance;
    }


    private Context context;
    private NoteRepository2(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<Note> getAllNotes() {
        return (ArrayList<Note>) NoteDAO.getInstance(context).queryAllNotes();
    }

    @Override
    public Note saveNote(Note note) {
        Note n = NoteDAO.getInstance(context).insertNote(note);
        return n;
    }

    @Override
    public Note getNote(long noteId) {
        return NoteDAO.getInstance(context).queryNoteById(noteId);
    }
}
