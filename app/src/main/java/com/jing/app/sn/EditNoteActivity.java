package com.jing.app.sn;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class EditNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
    }

    public void onFinishEdit(View view) {
        // 处理完成操作
        Toast.makeText(this, R.string.msg_note_saved, Toast.LENGTH_SHORT).show();
        finish();   // 关闭窗口
    }

    public void onCancelEdit(View view) {
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
