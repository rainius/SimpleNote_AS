package com.jing.app.sn.repository;

import com.jing.app.sn.model.Note;

import java.util.ArrayList;

/**
 * Created by jing on 2018/3/4.
 */

public interface INoteRepository {
    ArrayList<Note> getAllNotes();

    /**
     * 保存笔记对象
     * @param note 被保存的笔记对象
     * @return 保存成功则返回true，失败返回false
     */
    boolean saveNote(Note note);

    /**
     * 从存储中获取指定的笔记对象
     * @param noteId
     * @return
     */
    Note getNote(long noteId);
}
