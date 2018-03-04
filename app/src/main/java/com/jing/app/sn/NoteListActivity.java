package com.jing.app.sn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jing.app.sn.model.Note;
import com.jing.app.sn.repository.INoteRepository;
import com.jing.app.sn.repository.TestNoteRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NoteListActivity extends AppCompatActivity {

    // 笔记数据仓库接口，并绑定到测试仓库
    private INoteRepository noteRepository = new TestNoteRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        RecyclerView recyclerView = findViewById(R.id.note_list);
        // 设定为垂直列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // 创建并设置适配器
        NoteAdapter adapter = new NoteAdapter();
        // 为适配器设定数据集
        adapter.setNotes(noteRepository.getAllNotes());

        recyclerView.setAdapter(adapter);
    }

    public void onNewNote(View view) {
        // 启动新建页面
        Intent intent = new Intent(this, EditNoteActivity.class);
        startActivity(intent);
    }

    private void onReadNote() {
        // 启动阅读页面
        Intent intent = new Intent(this, ReadNoteActivity.class);
        startActivity(intent);
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
            holder.createTimeView.setText(formatTime(note.getCreateTime()));
            holder.contentView.setText(note.getContent());
        }

        @Override
        public int getItemCount() {
            return notes.size();
        }
    }

    private String formatTime(long time) {
        // 按照给定的格式串（"yyyy/MM/dd"）转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(new Date(time));
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

            // 响应点击列表项事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onReadNote();
                }
            });
        }

    }
}
