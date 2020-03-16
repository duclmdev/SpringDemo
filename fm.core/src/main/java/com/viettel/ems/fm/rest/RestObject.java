package com.viettel.ems.fm.rest;

public class RestObject {

    private final long id;
    private final String content;

    public RestObject(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
