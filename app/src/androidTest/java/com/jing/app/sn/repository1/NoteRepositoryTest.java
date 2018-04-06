package com.jing.app.sn.repository1;
//
//import android.content.Context;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.runner.AndroidJUnit4;
//
//import com.jing.app.sn.dao.NoteDAO;
//import com.jing.app.sn.model.Note;
//import com.jing.app.sn.repository.NoteRepository2;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.ArrayList;
//
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertNotNull;
//import static junit.framework.Assert.assertNull;
//import static junit.framework.Assert.assertTrue;
//
///**
// * Created by jing on 2018/4/3.
// */
//@RunWith(AndroidJUnit4.class)
//public class NoteRepositoryTest {
//
//    private Context context = InstrumentationRegistry.getTargetContext();
//
//    @Test
//    public void testSaveNote() {
//        dumpDb();
//        Note note = new Note(0, "test note", "A test", System.currentTimeMillis());
//        NoteRepository2 repo = NoteRepository2.getInstance(context);
//        boolean result = repo.saveNote(note);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testGetAllNotes() {
//        dumpDb();
//        NoteRepository2 repo = NoteRepository2.getInstance(context);
//        repo.saveNote(new Note(0, "test", "This is a test.", System.currentTimeMillis()));
//        repo.saveNote(new Note(0, "test", "This is a test.", System.currentTimeMillis()));
//        repo.saveNote(new Note(0, "test", "This is a test.", System.currentTimeMillis()));
//
//        ArrayList<Note> notes = repo.getAllNotes();
//        assertEquals(3, notes.size());
//    }
//
//    @Test
//    public void testGetNote() {
//        dumpDb();
//        NoteRepository2 repo = NoteRepository2.getInstance(context);
//        Note note = new Note(0, "test", "This is a test.", System.currentTimeMillis());
//        repo.saveNote(note);
//
//        Note nt = repo.getNote(note.getId());
//        assertNotNull(nt);
//
//        assertNull(repo.getNote(1000));
//    }
//
//    private void dumpDb() {
//        NoteDAO.getInstance(context).dumpNotes();
//    }
//
//}
