package com.adamin.demo.entity;

/**
 * @Classname Box
 * @Description TODO
 * @Date 2022/4/10 21:21
 * @Created by Adam(https://www.lixiaopeng.top)
 */
public class Box {
    private Integer id;
    private Integer uid;
    private Integer type;
    private String content;
    private Integer receiverUid;

    public Box(Integer id, Integer uid, Integer type, String content, Integer receiverUid) {
        this.id = id;
        this.uid = uid;
        this.type = type;
        this.content = content;
        this.receiverUid = receiverUid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReceiverUid() {
        return receiverUid;
    }

    public void setReceiverUid(Integer receiverUid) {
        this.receiverUid = receiverUid;
    }
}
