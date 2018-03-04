package com.jing.app.sn.repository;

import com.jing.app.sn.model.Note;

import java.util.ArrayList;

/**
 * Created by jing on 2018/3/4.
 */

public interface INoteRepository {
    ArrayList<Note> getAllNotes();
}
