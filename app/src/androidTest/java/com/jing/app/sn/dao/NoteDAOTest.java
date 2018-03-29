package com.jing.app.sn.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

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

}
