package com.jing.app.sn.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.jing.app.sn.model.Note;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class NoteDAOTest {

    @Test
    public void testCreateDatabase() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        NoteDAO dao = NoteDAO.getInstance(context);
        NoteDAO.NoteDbHelper dbHelper = dao.getDbHelper();

        // 取只读的数据库对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // 检查数据库对象是否获取成功
        assertNotNull(db);
        // 对note表进行一次查询
        Cursor cursor = db.query(Constants.TABLE_NOTE, null, null, null, null, null, null);
        // 检查查询操作是否成功
        assertNotNull(cursor);
    }

    @Test
    public void testInsertNote() {
        clearDb();

        Context context = InstrumentationRegistry.getTargetContext();
        NoteDAO dao = NoteDAO.getInstance(context);
        //NoteDAO.NoteDbHelper dbHelper = dao.getDbHelper();
        Note note = new Note(0, "test 1", "This is a test.", System.currentTimeMillis());
        Note note1 = dao.insertNote(note);
        assertTrue(note.getId() > 0);
    }

    @Test
    public void testQueryAllNotes() {

        clearDb();

        Context context = InstrumentationRegistry.getTargetContext();
        NoteDAO dao = NoteDAO.getInstance(context);
        //NoteDAO.NoteDbHelper dbHelper = dao.getDbHelper();
        Note note1 = new Note(0, "test 1", "This is a test.", System.currentTimeMillis());
        note1 = dao.insertNote(note1);
        Note note2 = new Note(0, "test 2", "This is a test.", System.currentTimeMillis());
        note2 = dao.insertNote(note2);
        Note note3 = new Note(0, "test 3", "This is a test.", System.currentTimeMillis());
        note3 = dao.insertNote(note3);

        List<Note> notes = dao.queryAllNotes();
        assertEquals(3, notes.size());
        assertEquals(notes.get(2).getTitle(), note1.getTitle());
        assertEquals(notes.get(1).getTitle(), note2.getTitle());
        assertEquals(notes.get(0).getTitle(), note3.getTitle());
    }


    @Test
    public void testQueryNoteById() {

        clearDb();

        Context context = InstrumentationRegistry.getTargetContext();
        NoteDAO dao = NoteDAO.getInstance(context);
        //NoteDAO.NoteDbHelper dbHelper = dao.getDbHelper();
        Note note1 = new Note(0, "test 1", "This is a test.", System.currentTimeMillis());
        note1 = dao.insertNote(note1);
        Note note2 = new Note(0, "test 2", "This is a test.", System.currentTimeMillis());
        note2 = dao.insertNote(note2);
        Note note3 = new Note(0, "test 3", "This is a test.", System.currentTimeMillis());
        note3 = dao.insertNote(note3);

        Note note = dao.queryNoteById(note1.getId());
        assertNotNull(note);
    }

    private void clearDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        NoteDAO dao = NoteDAO.getInstance(context);
        NoteDAO.NoteDbHelper dbHelper = dao.getDbHelper();
        dbHelper.getWritableDatabase().delete(Constants.TABLE_NOTE, null, null);
    }

}
