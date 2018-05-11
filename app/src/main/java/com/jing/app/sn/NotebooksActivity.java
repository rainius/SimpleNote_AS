package com.jing.app.sn;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jing.app.sn.dao.NoteDAO;
import com.jing.app.sn.model.Note;
import com.jing.app.sn.model.Notebook;
import com.jing.app.sn.utils.Utils;

import java.util.ArrayList;

public class NotebooksActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private NotebookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebooks);

        // 添加代码：初始化并设置RecyclerView
        mRecyclerView = findViewById(R.id.notebook_list);
        // 设定为垂直列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new NotebookAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        asyncLoadNotebooks();
    }

    private void asyncLoadNotebooks() {
        AsyncTask<Void, Void, ArrayList<Notebook>> task = new AsyncTask<Void, Void, ArrayList<Notebook>>() {
            @Override
            protected ArrayList<Notebook> doInBackground(Void... voids) {
                ArrayList<Notebook> nbs = NoteDAO.getInstance(NotebooksActivity.this.getApplicationContext()).queryAllNotebooks();
                // 在列表最前添加"全部笔记"项，id设为0
                Notebook allNotes = new Notebook(0, getString(R.string.all_notes));
                nbs.add(0, allNotes);
                return nbs;
            }

            @Override
            protected void onPostExecute(ArrayList<Notebook> notebooks) {
                mAdapter.setNotes(notebooks);
                mAdapter.notifyDataSetChanged();
            }
        };

        task.execute();
    }

    public void onNewNotebook(View view) {
        // 创建编辑框对象
        final EditText notebookNameEdit = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(R.string.new_notebook)
                .setView(notebookNameEdit)  // 将编辑框对象加入对话框
                .setNegativeButton("取消", null)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = notebookNameEdit.getEditableText().toString();
                        if (!TextUtils.isEmpty(name)) {
                            Notebook nb = new Notebook(0, name);
                            nb = NoteDAO.getInstance(NotebooksActivity.this).insertNote(nb);
                            if (nb != null) {
                                // 插入成功，刷新笔记本列表
                                asyncLoadNotebooks();
                            } else {
                                // 插入失败，提示用户
                                Toast.makeText(NotebooksActivity.this, "保存笔记本失败", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
        builder.show();
    }

    private class NotebookAdapter extends RecyclerView.Adapter<NotebookViewHolder> {

        // 笔记本列表
        private ArrayList<Notebook> noteBooks = new ArrayList<>();

        // 设置笔记本列表内容
        public void setNotes(ArrayList<Notebook> nbs) {
            // 现将原列表清空，再将传入的列表元素全部加入
            this.noteBooks.clear();
            if (nbs != null) {
                this.noteBooks.addAll(nbs);
            }
        }

        @Override
        public NotebookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 根据列表项布局文件创建视图对象
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.notebook_list_item, parent, false);
            // 基于上面的视图对象创建ViewHolder对象并返回
            NotebookViewHolder vh = new NotebookViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(NotebookViewHolder holder, int position) {
            // 取对应位置的笔记对象
            final Notebook nb = noteBooks.get(position);
            // 设置对应ViewHolder对象中各视觉元素的值
            holder.notebookName.setText(nb.getName());

            // 响应点击事件
            // 处理列表项点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelectNotebook(nb);
                }
            });
        }

        @Override
        public int getItemCount() {
            return noteBooks.size();
        }
    }

    private void onSelectNotebook(Notebook notebook) {
        // 创建一个Intent对象，用来携带要返回的参数
        Intent intent = new Intent();
        intent.putExtra("notebookId", notebook.getId());
        intent.putExtra("notebookName", notebook.getName());

        // 设置返回结果，返回码为1
        setResult(1, intent);

        //关闭页面
        finish();
    }

    private class NotebookViewHolder extends RecyclerView.ViewHolder {

        private TextView notebookName;

        public NotebookViewHolder(View itemView) {
            super(itemView);
            notebookName = itemView.findViewById(R.id.tv_nb_name);
        }

    }

}
