package com.jing.app.sn.repository;

import android.content.Context;

import com.jing.app.sn.BuildConfig;

/**
 * Created by jing on 2018/4/6.
 */

public class NoteRepositoryFactory {
    public static INoteRepository getNoteRepository(Context context) {

        switch (BuildConfig.USE_TEST_REPO) {
            case "test":
                return TestNoteRepository.getInstance();
            case "database":
            default:
                return NoteRepository.getInstance(context);
        }
    }
}

