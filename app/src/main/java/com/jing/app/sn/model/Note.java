package com.jing.app.sn.model;

/**
 * Created by jing on 2018/3/3.
 */

public class Note {

    /**
     * 笔记唯一标识
     */
    private long id;
    /**
     * 笔记标题
     */
    private String title;
    /**
     * 笔记正文
     */
    private String content;
    /**
     * 笔记创建时间
     */
    private long createTime;

    public Note(long id, String title, String content, long createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
