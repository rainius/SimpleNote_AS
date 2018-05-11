package com.jing.app.sn;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
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
    private FrameLayout mLoadingView;

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
        mLoadingView = findViewById(R.id.loading_view);

        LoadNoteTask task = new LoadNoteTask();
        task.execute();
    }


    private class LoadNoteTask extends AsyncTask<Void, Void, Note> {

        @Override
        protected void onPreExecute() {
            // 异步处理开始前显示加载视图
            mLoadingView.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Note note) {
            // 异步处理结束后关闭加载视图
            mLoadingView.setVisibility(View.GONE);
            // 如果获取到的笔记对象是null，则退出阅读页面
            if (note == null) {
                finish();
                return;
            }
            // 用加载得到的note填充页面内容
            mTimeView.setText(Utils.formatTime(note.getCreateTime()));
            mTitleView.setText(note.getTitle());
            mContentView.setText(note.getContent());
        }

        @Override
        protected Note doInBackground(Void... voids) {
            // 为了方便查看效果添加延时处理，将来要去掉
            try { Thread.sleep(1000); } catch (InterruptedException e) { }

            Intent intent = getIntent();
            long noteId = intent.getLongExtra(EXTRA_NOTE_ID, 0);
            return noteRepository.getNote(noteId);
        }
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
