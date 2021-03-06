package com.jing.app.sn.repository;

import com.jing.app.sn.model.Note;

import java.util.ArrayList;

/**
 * Created by jing on 2018/3/4.
 */

public class TestNoteRepository implements INoteRepository {


    private static TestNoteRepository instance;

    public static TestNoteRepository getInstance() {
        if (instance == null) {
            instance = new TestNoteRepository();
        }
        return instance;
    }

    private TestNoteRepository() {

    }

    private static ArrayList<Note> notes = new ArrayList<>();

    static {
        notes.add(new Note(1, "笔记1", "笔记1正文", System.currentTimeMillis()));
        notes.add(new Note(2, "笔记2", "笔记2正文", System.currentTimeMillis()));
        notes.add(new Note(3, "笔记3", "笔记3正文", System.currentTimeMillis()));
        notes.add(new Note(4, "笔记4", "笔记4正文", System.currentTimeMillis()));
        notes.add(new Note(5, "笔记5", "笔记5正文", System.currentTimeMillis()));
        notes.add(new Note(6, "笔记6", "笔记6正文", System.currentTimeMillis()));
        notes.add(new Note(7, "笔记7", "笔记7正文", System.currentTimeMillis()));
        notes.add(new Note(8, "笔记8", "笔记8正文", System.currentTimeMillis()));
        notes.add(new Note(9, "笔记9", "笔记9正文", System.currentTimeMillis()));
        notes.add(new Note(10, "笔记10", "笔记10正文", System.currentTimeMillis()));
    }

    @Override
    public ArrayList<Note> getAllNotes() {
        return notes;
    }

    @Override
    public Note saveNote(Note note) {
        if (note != null) {
            notes.add(0, note);
        }
        return note;
    }

    @Override
    public Note getNote(long noteId) {
        for (Note note : notes) {
            if (note.getId() == noteId) {
                return note;
            }
        }

        return null;
    }
}
