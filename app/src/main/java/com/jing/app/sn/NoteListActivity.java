package com.jing.app.sn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
    }

    public void onNewNote(View view) {
        // 启动新建页面
        Intent intent = new Intent(this, EditNoteActivity.class);
        startActivity(intent);
    }

    public void onReadNote(View view) {
        // 启动新建页面
        Intent intent = new Intent(this, ReadNoteActivity.class);
        startActivity(intent);
    }
}
