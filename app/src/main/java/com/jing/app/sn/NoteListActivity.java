package com.jing.app.sn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jing.app.sn.model.Note;
import com.jing.app.sn.repository.INoteRepository;
import com.jing.app.sn.repository.TestNoteRepository;
import com.jing.app.sn.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NoteListActivity extends AppCompatActivity {

    private static final int LIST_VIEW = 0;
    private static final int GRID_VIEW = 1;


    private RecyclerView mRecyclerView;
    private NoteAdapter adapter;

    // 笔记数据仓库接口，并绑定到测试仓库
    private INoteRepository noteRepository = TestNoteRepository.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        mRecyclerView = findViewById(R.id.note_list);
        // 设定为垂直列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        // 创建并设置适配器
        adapter = new NoteAdapter();
        // 为适配器设定数据集
        adapter.setNotes(noteRepository.getAllNotes());

        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 1. 重新设定数据集
        adapter.setNotes(noteRepository.getAllNotes());
        // 2. 触发RecyclerView重新绘制
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_note_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_grid_view:
                onSwitchView(GRID_VIEW);
                return true;
            case R.id.menu_item_list_view:
                onSwitchView(LIST_VIEW);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onSwitchView(int view) {
        RecyclerView.LayoutManager lm = null;
        if (view == GRID_VIEW) {
            lm = new GridLayoutManager(this, 2);
        } else if (view == LIST_VIEW) {
            lm = new LinearLayoutManager(this);
        }

        if (lm != null) {
            mRecyclerView.setLayoutManager(lm);
        }
    }


    public void onNewNote(View view) {
        // 启动新建页面
        Intent intent = new Intent(this, EditNoteActivity.class);
        startActivity(intent);
    }

    private void onReadNote(long noteId) {
        // 启动阅读页面
//        Intent intent = new Intent(this, ReadNoteActivity.class);
//        startActivity(intent);
        ReadNoteActivity.actionReadNote(this, noteId);
    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

        private ArrayList<Note> notes = new ArrayList<>();

        public void setNotes(ArrayList<Note> notes) {
            // 现将原列表清空，再将传入的列表元素全部加入
            this.notes.clear();
            if (notes != null) {
                this.notes.addAll(notes);
            }
        }

        @Override
        public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // 根据列表项布局文件创建视图对象
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.note_list_item, parent, false);
            // 基于上面的视图对象创建ViewHolder对象并返回
            NoteViewHolder vh = new NoteViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(NoteViewHolder holder, int position) {
            // 取对应位置的笔记对象
            Note note = notes.get(position);
            // 设置对应ViewHolder对象中各视觉元素的值
            holder.titleView.setText(note.getTitle());
            holder.createTimeView.setText(Utils.formatTime(note.getCreateTime()));
            holder.contentView.setText(note.getContent());

            // 响应点击事件
            final long noteId = note.getId();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onReadNote(noteId);
                }
            });
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }
    }

    private class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView titleView;
        private TextView createTimeView;
        private TextView contentView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.tv_title);
            createTimeView = itemView.findViewById(R.id.tv_create_time);
            contentView = itemView.findViewById(R.id.tv_content);
        }

    }
}
