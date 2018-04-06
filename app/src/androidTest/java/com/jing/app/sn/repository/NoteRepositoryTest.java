package com.jing.app.sn.repository;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.jing.app.sn.dao.NoteDAO;
import com.jing.app.sn.model.Note;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by jing on 2018/4/6.
 */

@RunWith(AndroidJUnit4.class)
public class NoteRepositoryTest {

    private Context context = InstrumentationRegistry.getTargetContext();
    private INoteRepository noteRepo = NoteRepository.getInstance(context);

    @Test
    public void testSaveNote() {
        Note note = new Note(0, "testSaveNote", "Test 1", System.currentTimeMillis());
        Note result = noteRepo.saveNote(note);
        assertNotNull(result);
        // 清除测试数据
        NoteDAO.getInstance(context).deleteNoteByTitle("testSaveNote");
    }

    @Test
    public void testGetAllNotes() {
        Note note = new Note(0, "testGetAllNotes", "Test 1", System.currentTimeMillis());
        noteRepo.saveNote(note);
        noteRepo.saveNote(note);
        noteRepo.saveNote(note);

        ArrayList<Note> notes = noteRepo.getAllNotes();
        assertNotNull(notes);
        assertTrue(notes.size() >= 3);
        // 清除测试数据
        NoteDAO.getInstance(context).deleteNoteByTitle("testGetAllNotes");
    }

    @Test
    public void testGetNote() {

        // 查询不存在的记录应当返回null
        assertNull(noteRepo.getNote(Long.MAX_VALUE));

        Note note = new Note(0, "testGetNote", "Test 1", System.currentTimeMillis());
        // 已保存的笔记对象
        Note savedNote = noteRepo.saveNote(note);
        long id = savedNote.getId();

        // 根据id查询对应记录，应当不为null
        Note result = noteRepo.getNote(id);
        assertNotNull(result);

        // 清除测试数据
        NoteDAO.getInstance(context).deleteNoteByTitle("testGetNote");
    }
}
