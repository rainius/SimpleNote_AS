package com.jing.app.sn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.jing.app.sn.model.Note;
import com.jing.app.sn.repository.INoteRepository;
import com.jing.app.sn.repository.NoteRepositoryFactory;
import com.jing.app.sn.repository.TestNoteRepository;
import com.jing.app.sn.utils.Utils;

public class ReadNoteActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE_ID = "extra_note_id";

    INoteRepository noteRepository;

    public static void actionReadNote(Context context, long noteId) {
        Intent intent = new Intent(context, ReadNoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, noteId);
        context.startActivity(intent);
    }


    private TextView mTimeView;
    private TextView mTitleView;
    private TextView mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_note);

        noteRepository = NoteRepositoryFactory.getNoteRepository(getApplicationContext());

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mTimeView = findViewById(R.id.tv_read_time);
        mTitleView = findViewById(R.id.tv_read_title);
        mContentView = findViewById(R.id.tv_read_content);

        Intent intent = getIntent();
        long noteId = intent.getLongExtra(EXTRA_NOTE_ID, 0);
        Note note = noteRepository.getNote(noteId);
        if (note == null) {
            finish();
            return;
        }

        mTimeView.setText(Utils.formatTime(note.getCreateTime()));
        mTitleView.setText(note.getTitle());
        mContentView.setText(note.getContent());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
