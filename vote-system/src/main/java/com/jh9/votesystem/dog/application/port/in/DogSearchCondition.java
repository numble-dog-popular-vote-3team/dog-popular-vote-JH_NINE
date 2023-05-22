package com.jh9.votesystem.dog.application.port.in;

public class DogSearchCondition {

    private String userCookie;
    private int pageSize;
    private long lastId;

    public DogSearchCondition(String userCookie, int pageSize, long lastId) {
        this.userCookie = userCookie;
        this.pageSize = pageSize;
        this.lastId = lastId;
    }

    public String getUserCookie() {
        return userCookie;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getLastId() {
        return lastId;
    }
}
