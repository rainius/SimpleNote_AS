package com.jing.app.sn;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jing.app.sn.model.Note;
import com.jing.app.sn.repository.INoteRepository;
import com.jing.app.sn.repository.TestNoteRepository;

public class EditNoteActivity extends AppCompatActivity {

    private EditText mTitleEdit;
    private EditText mContentEdit;

    INoteRepository noteRepository = TestNoteRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mTitleEdit = (EditText) findViewById(R.id.edit_title);
        mContentEdit = (EditText) findViewById(R.id.edit_content);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_item_finish:
                onFinishEdit();
                return true;
            case android.R.id.home:
                onCancelEdit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_edit_note1, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onCancelEdit();
//                return true;
//            case R.id.menu_item_finish:
//                onFinishEdit();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void onFinishEdit() {

        // 1. 生成id
        long id = noteRepository.getAllNotes().size() + 1;

        // 2. 从编辑区获取标题和内容字符串
        String title = mTitleEdit.getEditableText().toString();
        String content = mContentEdit.getEditableText().toString();

        // 3. 创建笔记对象
        Note note = new Note(id, title, content, System.currentTimeMillis());

        // 4. 存储笔记
        noteRepository.saveNote(note);

        Toast.makeText(this, R.string.msg_note_saved, Toast.LENGTH_SHORT).show();
        finish();   // 关闭窗口
    }

    private void onCancelEdit() {
        // 创建AlertDialog.Builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置对话框标题
        builder.setTitle("警告");
        // 设置警告内容
        builder.setMessage("是否放弃当前笔记？");
        // "确认"按钮操作
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();   // 关闭对话框
                finish();
            }
        });
        // "取消"按钮操作
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show(); // 显示对话框
    }
}
