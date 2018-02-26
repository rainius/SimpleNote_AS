package com.jing.app.sn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
    }

    public void onEditFinish(View view) {
        // 提醒用户，然后关闭页面
        Toast.makeText(this, "笔记未保存", Toast.LENGTH_SHORT).show();
        finish();
    }

}
